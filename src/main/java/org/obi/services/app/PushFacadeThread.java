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
import org.obi.services.entities.persistence.Persistence;
import org.obi.services.entities.tags.Tags;
import org.obi.services.sessions.tags.TagsFacade;
import org.obi.services.util.DateUtil;
import org.obi.services.util.Settings;
import org.obi.services.util.Util;
import org.obi.services.listener.thread.SystemThreadListener;
import org.obi.services.sessions.persistence.PersStandardFacade;

/**
 * Tags Facade Thread :
 *
 * The main purpose is to get decentralize operation that operating with the
 * database in order to reduce the time occupation.
 * <p>
 * In order to start the thread you should use normaly start
 * {@link PushFacadeThread#start()} as all thread system.
 *
 * Note : main loop thread is depend on {@link  PushFacadeThread#requestKill}
 * which is by default not kill. This loop will run until method
 * {@link TagsCollectorThread#kill()} is called. When finaly killed the
 * following even will be emitted
 * {@link TagsFacadeThreadListener#onProcessingStopThread(java.lang.Thread)}
 *
 *
 * @author r.hendrick
 */
public class PushFacadeThread extends Thread {

    // Allow to stop process run
    private Boolean requestStop = false;
    private boolean requestKill = false;
    private boolean running = false;

    private boolean NO_TAG_TO_UPDATE = false;
    private boolean NO_TAG_TO_PERSIST = false;

    /**
     * Collection of tags to process for update This will collect value from
     * tagsPendingForUpdate then update than clear value after update.
     */
    private List<Tags> tagsToUpdate = new ArrayList<>();

    /**
     * Collection of tags received while updating is processing. Will be delete
     * after being collect by tagsUpdating
     */
    private List<Tags> tagsPendingForUpdate = new ArrayList<>();

    /**
     * Tags persistence will save value in database. Value will be collect from
     * @link{TagsFacadeThread#tagsToUpdate} after updating. Then in next different process will try to persist it.
     */
    private List<Tags> tagsToPersit = new ArrayList<>();

    /**
     * Allow to add tags to list stack to be update in the system
     *
     * @param tagsToUpdate
     */
    public void addNewTags(List<Tags> tagsToUpdate) {
        tagsPendingForUpdate.addAll(tagsToUpdate);
    }

    /**
     * Allow to add only one tags at a time to list stack to be update in
     * database
     *
     * @param tagToUpdate a tag with correct value to fill the database
     */
    public void addNewTag(Tags tagToUpdate) {
        tagsPendingForUpdate.add(tagToUpdate);
    }

    /**
     * Collection of tags to process for update
     */
    private List<Persistence> persistenceActive = new ArrayList<>();
    /**
     * Collection of persistence config for tag saving received. Waiting to be
     * perist. Will update before processeing persistence
     */
    private List<Persistence> persistencePendingToActivate = new ArrayList<>();

    /**
     * Allow to add tags to list stack to be update in the system
     *
     * @param tagsToUpdate
     */
    public void addNewPersistence(List<Persistence> persistenceToUpdate) {
//        persistencePendingToActivate.clear();
        persistencePendingToActivate.addAll(persistenceToUpdate);
//        Util.out(Util.errLine() + getClass().getSimpleName() + " T(" + getName() + ") >> addNewPersistence >> persistencePendingToActivate size = " + persistencePendingToActivate.size());
//        for (Persistence p : persistencePendingToActivate) {
//            System.out.print("P(" + p.getId() + ",>>" + p.getTag().getId() + ")/");
//        }
//        System.out.println("");
//        Util.out(Util.errLine() + getClass().getSimpleName() + " T(" + getName() + ") >> addNewPersistence >> persistenceActive size = " + persistenceActive.size());
//        for (Persistence p : persistenceActive) {
//            System.out.print("P(" + p.getId() + ",>>" + p.getTag().getId() + ")/");
//        }
//        System.out.println("");

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
    public void addClientListener(SystemThreadListener systemThreadListener) {
        this.systemThreadListeners.add(systemThreadListener);
    }

    /**
     * Allow to remove listener to the list of event listener
     *
     * @param _tagsCollectorThreadListeners a class which will listen to service
     * event
     */
    public void removeClientListener(SystemThreadListener systemThreadListener) {
        this.systemThreadListeners.remove(systemThreadListener);
    }

//    private static PushFacadeThread INSTANCE;
//
//    public static PushFacadeThread getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new PushFacadeThread();
//        }
//        return INSTANCE;
//    }
    /**
     * Creates new form
     */
    public PushFacadeThread(String name) {
        super(name);
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

            /**
             * If new value are pending to being update they will be put in
             * active update tags before clearing the pending list
             */
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
                // add new pending to the list
                tagsToUpdate.addAll(tagsPendingForUpdate);
                tagsPendingForUpdate.clear(); // clear pending list
            }

            // SUB PROCESS LOOP
            while (!requestStop & !requestKill & !wait & !tagsToUpdate.isEmpty()) {
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
                 * ************************************************************
                 * Check if connection for the tagsFacade still exit. update
                 * value of the tags beeing changed.
                 */
                persistTagsUpdate(tagsFacade, gmtIndex);

                /**
                 * ************************************************************
                 * Check if connection for the persistenceFacade still exit.
                 * Persitence rule will be update if change was detected before
                 * to proceed
                 */
                persistStandard(persStandardFacade, gmtIndex);

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

    /**
     * Check if connection for the tagsFacade still exit. update value of the
     * tags beeing changed.
     *
     * @param tagsFacade facade to processed over database
     * @param gmtIndex gmt to being used
     */
    private void persistTagsUpdate(TagsFacade tagsFacade, Integer gmtIndex) {
        try {
            // Initialize check communication with database
            if (tagsFacade.isConnectionOn()) {
                // System thread indicate infos on actual status
                for (int i = 0; i < systemThreadListeners.size(); i++) {
                    systemThreadListeners.get(i).onSubProcessActivityState(this, true);
                    systemThreadListeners.get(i).onErrorCollection(this,
                            DateUtil.localDTFFZoneId(gmtIndex)
                            + " : sql connection ok !");
                }

                /**
                 * Update instant data in table tags. Then put value in the
                 * persistence list before clearing actual value
                 *
                 */
                try {
                    if (!tagsToUpdate.isEmpty()) {
                        tagsFacade.updateValue(tagsToUpdate);
                        tagsToPersit.addAll(tagsToUpdate);      // indicate new tags can be persit
                        tagsToUpdate.clear();                   // Empty the list of tags to update
                        NO_TAG_TO_UPDATE = false;
                    } else {
                        if (!NO_TAG_TO_UPDATE) {
                            Util.out(Util.errLine() + getClass().getSimpleName()
                                    + " >> on updateValue >> NO TAG TO UPDATE !");
                            NO_TAG_TO_UPDATE = true;
                        }
                    }
                } catch (SQLException ex) {
                    Util.out(Util.errLine() + getClass().getSimpleName()
                            + " >> on updateValue >> " + ex.getLocalizedMessage());
                    Logger.getLogger(PushFacadeThread.class.getName()).log(Level.SEVERE, null, ex);
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
                    + " : onFacadePushPersistence >> " + ex.getLocalizedMessage());
        }

    }

    /**
     * Persistence Standard : id the method to save data in the way of siemens
     * or zenon. Meaning, tag is as row and value are following.
     * <p>
     * Check if connection for the persistenceFacade still exit. Persitence rule
     * will be update if change was detected before to proceed
     *
     * @param persStandardFacade facade to processed over database
     * @param gmtIndex gmt to beeing used
     */
    private void persistStandard(PersStandardFacade persStandardFacade, Integer gmtIndex) {

        String methodName = "pers_standard";

        if (tagsToPersit.isEmpty()) {
            if (!NO_TAG_TO_PERSIST) {
                Util.out(Util.errLine()
                        + getClass().getSimpleName()
                        + " T(" + getName()
                        + ") persistStandard >> Error no tags to be perssit tagsToPersist size = " + tagsToPersit.size());
                NO_TAG_TO_PERSIST = true;
            }
            return;
        } else {
            NO_TAG_TO_PERSIST = false;
        }
        /**
         * ************************************************************
         * Check if connection for the persistenceFacade still exit. Persitence
         * rule will be update if change was detected before to proceed
         */
        try {
            // Check persistence connection to database
            if (persStandardFacade.isConnectionOn()) {
                // Indicate successful connection to user
                for (int i = 0; i < systemThreadListeners.size(); i++) {
                    systemThreadListeners.get(i).onSubProcessActivityState(this, true);
                    systemThreadListeners.get(i).onErrorCollection(this,
                            DateUtil.localDTFFZoneId(gmtIndex)
                            + " : sql connection ok for " + persStandardFacade.getClass().getSimpleName() + " !");
                }

//                        try {
                // check if active persistence list and pending as changed
//                if (!persistenceActive.equals(persistencePendingToActivate)) {
//                    persistenceActive.clear();
                persistenceActive.addAll(persistencePendingToActivate);
//                    Util.out(Util.errLine() + getName() + ":" + getClass().getSimpleName() + " - " + methodName + " >> update persistence rule detected ! ");
//                    for (Persistence p : persistenceActive) {
//                        Util.out("P(" + p.getId() + ", " + p.getTag().getId() + ")/");
//                    }
//                    Util.out("");
//                } else {
//                    Util.out(Util.errLine() + getClass().getSimpleName() + " T(" + getName() + ") >> persistStandard >> Persistence activae already equal to persistencePendingToActivate");
//                    Util.out(Util.errLine() + getClass().getSimpleName() + " T(" + getName() + ") >> persistStandard >> persistencePendingToActivate size = " + persistencePendingToActivate.size());
//                    for (Persistence p : persistencePendingToActivate) {
//                        System.out.print("P(" + p.getId() + ",>>" + p.getTag().getId() + ")/");
//                    }
//                    System.out.println("");
//                    Util.out(Util.errLine() + getClass().getSimpleName() + " T(" + getName() + ") >> persistStandard >> persistenceActive size = " + persistenceActive.size());
//                    for (Persistence p : persistenceActive) {
//                        System.out.print("P(" + p.getId() + ",>>" + p.getTag().getId() + ")/");
//                    }
//                    System.out.println("");
//                }

                /**
                 * With tagsToPersit which corresponding to updated tags now
                 * check if it need to be persist and do so. After what list is
                 * cleared
                 */
                if (!tagsToPersit.isEmpty()) {
//                    if (tagsToPersit.get(0).getMachine().getId() == 1) {
//                        Util.out(Util.errLine() + getClass().getSimpleName() + " T(" + getName() + ") persistStandard >> find tagsToPersist for table 2 with size = " + tagsToPersit.size());
//                        for(Tags t : tagsToPersit){
//                            Util.out(Util.errLine() + getClass().getSimpleName() + " T(" + getName() + ")  persistStandard >> tags: " + t);
//                        }
//                        Util.out(Util.errLine() + getClass().getSimpleName() + " T(" + getName() + ") persistStandard >> while persistence equal size = " + persistenceActive.size());
//                        for(Persistence p : persistenceActive){
//                            Util.out(Util.errLine() + getClass().getSimpleName() + " T(" + getName() + ")  persistStandard >> tags: " + p);
//                        }
//                    }
                }
                Boolean r = persStandardFacade.pushValue(persistenceActive, tagsToPersit);
                tagsToPersit.clear();
//                            if (r) {
//                                Util.out(Util.errLine() + getClass().getSimpleName()
//                                        + " : persistence operate with success ! ");
//                            }else{
//                                Util.out(Util.errLine() + getClass().getSimpleName()
//                                        + " : persistence operate with bad count ! ");
//                            }
//                        } catch (SQLException ex) {
//                            Util.out(Util.errLine() + getClass().getSimpleName()
//                                    + " >> Eror on persStandard pushValue >> " + ex.getLocalizedMessage());
//                            Logger.getLogger(PushFacadeThread.class.getName()).log(Level.SEVERE, null, ex);
//                        }

            } else { // Error on connection sql
                for (int i = 0; i < systemThreadListeners.size(); i++) {
                    systemThreadListeners.get(i).onSubProcessActivityState(this, false);
                    systemThreadListeners.get(i).onErrorCollection(this,
                            DateUtil.localDTFFZoneId(gmtIndex)
                            + " : connection to sql " + persStandardFacade.getClass().getSimpleName() + " produce error !");
                    Util.out(Util.errLine() + getClass().getSimpleName() + " T(" + getName() + ") >> error on connection get from statement SQL");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FetchFacadeThread.class.getName()).log(Level.SEVERE, null, ex);
            Util.out(Util.errLine() + getClass().getSimpleName()
                    + " : onFacadeFetchPersistence >> " + ex.getLocalizedMessage());
        }
    }

}
