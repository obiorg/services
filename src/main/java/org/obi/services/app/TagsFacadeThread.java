package org.obi.services.app;

import static java.lang.Thread.sleep;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.obi.services.entities.machines.Machines;
import org.obi.services.entities.persistence.Persistence;
import org.obi.services.entities.tags.Tags;
import org.obi.services.sessions.tags.TagsFacade;
import org.obi.services.util.DateUtil;
import org.obi.services.util.Settings;
import org.obi.services.util.Util;
import org.obi.services.listener.thread.SystemThreadListener;
import org.obi.services.sessions.persistence.PersStandardFacade;
import org.obi.services.sessions.persistence.PersistenceFacade;

/**
 * Tags Facade Thread :
 *
 * The main purpose is to get decentralize operation that operating with the
 * database in order to reduce the time occupation.
 * <p>
 * In order to start the thread you should use normaly start
 * {@link TagsFacadeThread#start()} as all thread system.
 *
 * Note : main loop thread is depend on {@link  TagsFacadeThread#requestKill}
 * which is by default not kill. This loop will run until method
 * {@link TagsCollectorThread#kill()} is called. When finaly killed the
 * following even will be emitted
 * {@link TagsFacadeThreadListener#onProcessingStopThread(java.lang.Thread)}
 *
 *
 * @author r.hendrick
 */
public class TagsFacadeThread extends Thread {

    // Allow to stop process run
    private Boolean requestStop = false;
    private boolean requestKill = false;
    private boolean running = false;

    /**
     * Collection of tags to process for update
     */
    private List<Tags> tagsUpdating = new ArrayList<>();
    /**
     * Collection of tags to receive for updating
     */
    private List<Tags> tagsPendingForUpdate = new ArrayList<>();

    /**
     * Tags thtat will be analyse for persistence
     */
    private List<Tags> tagsPersistence = new ArrayList<>();

    /**
     * Allow to add tags to list stack to be update in the system
     *
     * @param tagsToUpdate
     */
    public void addNewTags(List<Tags> tagsToUpdate) {
        tagsPendingForUpdate.addAll(tagsToUpdate);
    }

    /**
     * Collection of tags to process for update
     */
    private List<Persistence> persistenceUpdating = new ArrayList<>();
    /**
     * Collection of tags to receive for updating
     */
    private List<Persistence> persistencePendingForUpdate = new ArrayList<>();

    /**
     * Allow to add tags to list stack to be update in the system
     *
     * @param tagsToUpdate
     */
    public void addNewPersistence(List<Persistence> persistenceToUpdate) {
        persistencePendingForUpdate.addAll(persistenceToUpdate);
    }

    /**
     * Array list which contain all the TagsFacadeThreadListener listeners that
     * should receive event from client class
     */
    private ArrayList<SystemThreadListener> systemThreadListeners = new ArrayList<>();

    /**
     * Allow to add listener to the list of event listener
     *
     * @param _tagsCollectorThreadListeners a class which will listen to service
     * event
     */
    public void addClientListener(SystemThreadListener _tagsFacadeThreadListeners) {
        this.systemThreadListeners.add(_tagsFacadeThreadListeners);
    }

    /**
     * Allow to remove listener to the list of event listener
     *
     * @param _tagsCollectorThreadListeners a class which will listen to service
     * event
     */
    public void removeClientListener(SystemThreadListener _tagsFacadeThreadListeners) {
        this.systemThreadListeners.remove(_tagsFacadeThreadListeners);
    }

//    private static TagsFacadeThread INSTANCE;
//
//    public static TagsFacadeThread getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new TagsFacadeThread();
//        }
//        return INSTANCE;
//    }
    /**
     * Creates new form
     */
    public TagsFacadeThread() {

    }

    /**
     * request stop main loop
     */
    public void doStop() {
        requestStop = true;
    }

    public void doRelease() {
        requestStop = false;
    }

    public void kill() {
        requestKill = true;
    }

    /**
     * Check if processus is running mean is processing but not yet kill
     *
     * @return
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Main loop of the thread data collector
     */
    @Override
    public void run() {
        String methodName = getClass().getSimpleName() + " : run() >> ";

        // Start parent thread
        super.run();
        Util.out(Util.errLine() + methodName + "Thead TagsFacade : started with review of connection each 1s");

        // Prepare working element
        TagsFacade tagsFacade = new TagsFacade(); //TagsFacade.getInstance();
        boolean firstTimeInProcessing = true;   //< Inidcate run loop go back at first in main processing loop
        Integer gmtIndex = Integer.valueOf(Settings.read(Settings.CONFIG, Settings.GMT).toString());
        Integer companyId = Integer.valueOf(Settings.read(Settings.CONFIG, Settings.COMPANY).toString());

        // Prepare persistence pusher
        PersStandardFacade persStandardFacade = new PersStandardFacade();

        /**
         * START MAIN THREAD LOOP will stop when requestKill is receive by
         * {@link TagsCollectorThread#kill()} or straight requestKill = true.
         */
        LocalDateTime dtMachineChanged = LocalDateTime.now(ZoneId.of(DateUtil.zoneIdOf(gmtIndex)));
        while (!requestKill) {
            /**
             * processCycleStamp allow to reduce processing analysis over olding
             * time waiting
             */
            long processCycleStamp = Instant.now().toEpochMilli(); // allow firstime play

            // Inform main thread only once it reach this point after execution of subproces
            if (firstTimeInProcessing) {
                for (int i = 0; i < systemThreadListeners.size(); i++) {
                    systemThreadListeners.get(i).onProcessingThread(this);
                    systemThreadListeners.get(i).onErrorCollection(this,
                            DateUtil.localDTFFZoneId(gmtIndex)
                            + " : Start processing TagsFacadeThread...");
                    Util.out(Util.errLine() + methodName + DateUtil.localDTFFZoneId(gmtIndex)
                            + " : Start processing TagsFacadeThread...");
                }
                firstTimeInProcessing = false;
            }

            // Check available tags pending for updating
            Boolean wait = false;

            if (tagsPendingForUpdate.isEmpty()) {
                wait = true;
                // Inform liteners about number off collection count and error
                for (int i = 0; i < systemThreadListeners.size(); i++) {
                    systemThreadListeners.get(i).onCollectionCount(this, 0);
                    systemThreadListeners.get(i).onErrorCollection(this,
                            DateUtil.localDTFFZoneId(gmtIndex)
                            + " : No tags to update, will run in \"wait\" mode !");
                }
            } else {
                tagsUpdating.addAll(tagsPendingForUpdate);
                tagsPendingForUpdate.clear();
            }

            // SUB PROCESS LOOP
            while (!requestStop & !requestKill & !wait & !tagsUpdating.isEmpty()) {
                /**
                 * subProcessCycleStamp allow to reduce processing analysis over
                 * connection mistakes and database access
                 */
                long subProcessCycleStamp = Instant.now().toEpochMilli(); // allow firstime play

                // Inform sub thread only once it reach this after exuction of subprocess 
                if (running == false) {
                    for (int i = 0; i < systemThreadListeners.size(); i++) {
                        systemThreadListeners.get(i).onProcessingSubThread(this);
                    }
                    running = true;
                }

                /**
                 * Check if connection for the façade exist or try to
                 * instanciate
                 */
                try {
                    if (tagsFacade.isConnectionOn()) {
                        for (int i = 0; i < systemThreadListeners.size(); i++) {
                            systemThreadListeners.get(i).onSubProcessActivityState(this, true);
                            systemThreadListeners.get(i).onErrorCollection(this,
                                    DateUtil.localDTFFZoneId(gmtIndex)
                                    + " : sql connection ok !");
                        }

                        try {
                            tagsFacade.updateValue(tagsUpdating);
                            tagsPersistence.addAll(tagsUpdating);
                            tagsUpdating.clear();
                        } catch (SQLException ex) {
                            Util.out(Util.errLine() + getClass().getSimpleName()
                                    + " >> on updateValue >> " + ex.getLocalizedMessage());
                            Logger.getLogger(TagsFacadeThread.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else { // Error on connection sql
                        for (int i = 0; i < systemThreadListeners.size(); i++) {
                            systemThreadListeners.get(i).onSubProcessActivityState(this, false);
                            systemThreadListeners.get(i).onErrorCollection(this,
                                    DateUtil.localDTFFZoneId(gmtIndex)
                                    + " : connection to sql produce error !");

                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FetchFacadeThread.class.getName()).log(Level.SEVERE, null, ex);
                    Util.out(Util.errLine() + getClass().getSimpleName()
                            + " : onFacadeFetchPersistence >> " + ex.getLocalizedMessage());
                }

                /**
                 * Check if connection for the persistenceFacade still existor
                 * instanciate it
                 */
                try {
                    if (persStandardFacade.isConnectionOn()) {
                        for (int i = 0; i < systemThreadListeners.size(); i++) {
                            systemThreadListeners.get(i).onSubProcessActivityState(this, true);
                            systemThreadListeners.get(i).onErrorCollection(this,
                                    DateUtil.localDTFFZoneId(gmtIndex)
                                    + " : sql connection ok for " + persStandardFacade.getClass().getSimpleName() + " !");
                        }

                        try {
                            if (!persistenceUpdating.equals(persistencePendingForUpdate)) {
                                persistenceUpdating.clear();
                                persistenceUpdating.addAll(persistencePendingForUpdate);
                            }
                            Boolean r = persStandardFacade.pushValue(persistenceUpdating, tagsPersistence);
                            if (r) {
                                Util.out(Util.errLine() + getClass().getSimpleName()
                                        + " : persistence operate with success ! ");
                            }else{
                                Util.out(Util.errLine() + getClass().getSimpleName()
                                        + " : persistence operate with bad count ! ");
                            }
                        } catch (SQLException ex) {
                            Util.out(Util.errLine() + getClass().getSimpleName()
                                    + " >> on persStandard pushValue >> " + ex.getLocalizedMessage());
                            Logger.getLogger(TagsFacadeThread.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else { // Error on connection sql
                        for (int i = 0; i < systemThreadListeners.size(); i++) {
                            systemThreadListeners.get(i).onSubProcessActivityState(this, false);
                            systemThreadListeners.get(i).onErrorCollection(this,
                                    DateUtil.localDTFFZoneId(gmtIndex)
                                    + " : connection to sql " + persStandardFacade.getClass().getSimpleName() + " produce error !");

                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FetchFacadeThread.class.getName()).log(Level.SEVERE, null, ex);
                    Util.out(Util.errLine() + getClass().getSimpleName()
                            + " : onFacadeFetchPersistence >> " + ex.getLocalizedMessage());
                }

                /**
                 * Manage reading time of data tags in the machine not less than
                 * 1s
                 */
                long defaultDelay = 1000;
                long delay = Instant.now().toEpochMilli() - subProcessCycleStamp;
                // Inform on execution delay
                for (int i = 0; i < systemThreadListeners.size(); i++) {
                    systemThreadListeners.get(i).onProcessingSubCycleTime(this, delay);
                }
                // Sleep remaining time
                if (delay < defaultDelay) {
                    long d = defaultDelay - delay;
                    try {
                        sleep(d);
                    } catch (InterruptedException ex) {
                        Util.out(Util.errLine() + methodName + " >> \"Sub Processing Loop\" unable to proced minimum delay !\n" + ex.getLocalizedMessage());
                        Logger.getLogger(ManagerControllerThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

            // sub process stop running
            running = false; //!< indicate end of processus running

            /**
             * Manage initiation of new request reading Default preset time is
             * 1s
             */
            long processDelay = 1000;
            long delay = Instant.now().toEpochMilli() - processCycleStamp;
            // Inform on execution delay
            for (int i = 0; i < systemThreadListeners.size(); i++) {
                systemThreadListeners.get(i).onProcessingCycleTime(this, delay);
            }
            // Sleep remaining delay
            if (delay < processDelay) {
                long d = processDelay - delay;
                try {
                    sleep(d);
                } catch (InterruptedException ex) {
                    Util.out(Util.errLine() + methodName + " >> \"Processing Loop\" unable to proced minimum delay !\n" + ex.getLocalizedMessage());
                    Logger.getLogger(ManagerControllerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        /**
         * Will kill tags collector controller : inform all client
         */
        for (int i = 0; i < systemThreadListeners.size(); i++) {
            systemThreadListeners.get(i).onProcessingStopThread(this);
        }

        Util.out(Util.errLine() + methodName + " Terminate tag collector Controller Thread");
    }

}
