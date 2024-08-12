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
import org.obi.services.sessions.persistence.PersistenceFacade;
import org.obi.services.sessions.tags.TagsFacade;
import org.obi.services.util.DateUtil;
import org.obi.services.util.Settings;
import org.obi.services.util.Util;
import org.obi.services.listener.thread.SystemThreadListener;
import org.obi.services.listener.thread.FetchThreadListener;

/**
 * Tags Facade Thread :
 *
 * The main purpose is to get decentralize operation that operating with the
 * database in order to reduce the time occupation.
 * <p>
 * In order to start the thread you should use normaly start
 * {@link TagsFacadeThread#start()} as all thread system.
 *
 * Note : main loop thread is depend on {@link  FetchFacadeThread#requestKill}
 * which is by default not kill. This loop will run until method
 * {@link FetchFacadeThread#kill()} is called. When finaly killed the following
 * even will be emitted
 * {@link TagsFacadeThreadListener#onProcessingStopThread(java.lang.Thread)}
 *
 *
 * @author r.hendrick
 */
public class FetchFacadeThread extends Thread {

    // Allow to stop process run
    private Boolean requestStop = false;
    private boolean requestKill = false;
    private boolean running = false;

    /**
     * Main machine
     */
    private Machines machine;

    /**
     * Collection of tags to process for update
     */
    private List<Tags> tagsActive = new ArrayList<>();
    /**
     * Collection of tags to receive for updating
     */
    private List<Tags> tagsCollect = new ArrayList<>();

    /**
     * Collection of persistence to process for update
     */
    private List<Persistence> persistenceActive = new ArrayList<>();
    /**
     * Collection of persistence to receive for updating
     */
    private List<Persistence> persistenceCollect = new ArrayList<>();

    /**
     * Array list which contain all the TagsFacadeThreadListener listeners that
     * should receive event from client class
     */
    private ArrayList<SystemThreadListener> systemThreadListeners = new ArrayList<>();

    private ArrayList<FetchThreadListener> fetchThreadListeners = new ArrayList<>();

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

    /**
     * Allow to add listener to the list of event listener
     *
     * @param _tagsCollectorThreadListeners a class which will listen to service
     * event
     */
    public void addClientFetchListener(FetchThreadListener fetchThreadListener) {
        this.fetchThreadListeners.add(fetchThreadListener);
        Util.out(Util.errLine() + " " + getClass().getSimpleName() + " addClientListener on machine : " + machine.getName() + " >> client now at = " + fetchThreadListeners.size());
    }

    /**
     * Allow to remove listener to the list of event listener
     *
     * @param _tagsCollectorThreadListeners a class which will listen to service
     * event
     */
    public void removeClientFetchListener(FetchThreadListener _fetchThreadListeners) {
        this.fetchThreadListeners.remove(_fetchThreadListeners);
        Util.out(Util.errLine() + " " + getClass().getSimpleName() + " removeClientListener on machine : " + machine.getName() + " >> client now at = " + fetchThreadListeners.size());
    }

    /**
     * Creates new form
     */
    public FetchFacadeThread(Machines machine) {
        this.machine = machine;
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

        /**
         * Instantiate facade that will deal with value that needs to be persist
         * in database.
         */
        PersistenceFacade persistenceFacade = new PersistenceFacade();

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
                            + " : Start processing FetchFacadeThread...");
                    Util.out(Util.errLine() + methodName + DateUtil.localDTFFZoneId(gmtIndex)
                            + " : Start processing FetchFacadeThread...");
                }
                firstTimeInProcessing = false;
            }

            // SUB PROCESS LOOP
            while (!requestStop & !requestKill) {
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

                // Collect tags
                fetchTags(tagsFacade, companyId, gmtIndex);

                // Collect persistence
                fetchPersistence(persistenceFacade, gmtIndex);

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
        for (int i = 0;
                i < systemThreadListeners.size();
                i++) {
            systemThreadListeners.get(i).onProcessingStopThread(this);
        }

        Util.out(Util.errLine() + methodName + " Terminate tag collector Controller Thread");
    }

    /**
     * Fetch Tags
     *
     * @param tagsFacade
     * @param gmtIndex
     */
    private void fetchTags(TagsFacade tagsFacade, Integer companyId, Integer gmtIndex) {
        /**
         * Check if connection for the TAGSFACADE exist or try to instanciate
         */
        try {
            if (tagsFacade.isConnectionOn()) {
                for (int i = 0; i < systemThreadListeners.size(); i++) {
                    systemThreadListeners.get(i).onSubProcessActivityState(this, true);
                    systemThreadListeners.get(i).onErrorCollection(this,
                            DateUtil.localDTFFZoneId(gmtIndex)
                            + " : sql connection ok for " + tagsFacade.getClass().getSimpleName() + " !");
                }

                tagsCollect = tagsFacade.findActiveByCompanyAndMachine(companyId, machine.getId());
                if (!tagsCollect.equals(tagsActive)) {
                    tagsActive.clear();
                    tagsActive.addAll(tagsCollect);
                    for (FetchThreadListener fetchThreadListeners : fetchThreadListeners) {
                        fetchThreadListeners.onNewTags(tagsActive);
                    }
                }
            } else { // Error on connection sql
                for (int i = 0; i < systemThreadListeners.size(); i++) {
                    systemThreadListeners.get(i).onSubProcessActivityState(this, false);
                    systemThreadListeners.get(i).onErrorCollection(this,
                            DateUtil.localDTFFZoneId(gmtIndex)
                            + " : connection to sql " + tagsFacade.getClass().getSimpleName() + " produce error !");

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FetchFacadeThread.class.getName()).log(Level.SEVERE, null, ex);
            Util.out(Util.errLine() + getClass().getSimpleName()
                    + " : onFacadeFetchTags >> " + ex.getLocalizedMessage());
        }
    }

    /**
     * Check for new persistence content.
     *
     * @param persistenceFacade facade jdbc to access database
     * @param gmtIndex time to be use
     */
    private void fetchPersistence(PersistenceFacade persistenceFacade, Integer gmtIndex) {
        /**
         * Check if connection for the persistenceFacade exist or try to
         * instanciate
         */
        try {
            if (persistenceFacade.isConnectionOn()) {
                for (int i = 0; i < systemThreadListeners.size(); i++) {
                    systemThreadListeners.get(i).onSubProcessActivityState(this, true);
                    systemThreadListeners.get(i).onErrorCollection(this,
                            DateUtil.localDTFFZoneId(gmtIndex)
                            + " : sql connection ok for " + persistenceFacade.getClass().getSimpleName() + " !");
                }

                persistenceCollect = persistenceFacade.findByMachineActivated(machine);
//                        Util.out(Util.errLine() + getClass() + " >> Parent("
//                                + currentThread().getThreadGroup().getParent().getName()  + ")"
//                                + "|- " + getName() + " >> persistence found : " + persistenceCollect.size());

//                if (!persistenceCollect.equals(persistenceActive)) {
                persistenceActive.clear();
                persistenceActive.addAll(persistenceCollect);

                if (!tagsActive.isEmpty()) {
                    if (tagsActive.get(0).getMachine().getId() == 1) {
                        if (!persistenceActive.isEmpty()) {
//                            Util.out(Util.errLine() + getClass().getSimpleName() + " T(" + getName() + ") >> fetchPersistence >> find for machineId : " + tagsActive.get(0).getMachine().getId() + " >> record : " + persistenceActive.size());
//                            for (Persistence p : persistenceActive) {
//                                Util.out(Util.errLine() + getClass().getSimpleName() + " T(" + getName() + ")  fetchPersistence >> tags: " + p);
//                            }
                        } else {
                            Util.out(Util.errLine() + getClass().getSimpleName() + " T(" + getName() + ") >> fetchPersistence >> No persistenceActive for machineId :" + tagsActive.get(0).getMachine().getId());
                        }
                    }
                }

                //Util.out(Util.errLine() + getClass().getSimpleName() + "T(" + getName() + ") >> renew pesistence active : " + persistenceActive.size());
                for (FetchThreadListener fetchThreadListeners : fetchThreadListeners) {
                    fetchThreadListeners.onNewPersistences(persistenceActive);
                }
//                }

            } else { // Error on connection sql
                for (int i = 0; i < systemThreadListeners.size(); i++) {
                    systemThreadListeners.get(i).onSubProcessActivityState(this, false);
                    systemThreadListeners.get(i).onErrorCollection(this,
                            DateUtil.localDTFFZoneId(gmtIndex)
                            + " : connection to sql " + persistenceFacade.getClass().getSimpleName() + " produce error !");

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FetchFacadeThread.class.getName()).log(Level.SEVERE, null, ex);
            Util.out(Util.errLine() + getClass().getSimpleName()
                    + " : onFacadeFetchPersistence >> " + ex.getLocalizedMessage());
        }

    }

}
