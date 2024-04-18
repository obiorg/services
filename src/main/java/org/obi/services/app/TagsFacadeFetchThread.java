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
import org.obi.services.entities.tags.Tags;
import org.obi.services.listener.TagsCollectorThreadListener;
import org.obi.services.listener.TagsFacadeFetchThreadListener;
import org.obi.services.sessions.tags.TagsFacade;
import org.obi.services.util.DateUtil;
import org.obi.services.util.Settings;
import org.obi.services.util.Util;

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
public class TagsFacadeFetchThread extends Thread {

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
     * Array list which contain all the TagsFacadeThreadListener listeners that
     * should receive event from client class
     */
    private ArrayList<TagsCollectorThreadListener> tagsFacadeThreadListeners = new ArrayList<>();

    private ArrayList<TagsFacadeFetchThreadListener> tagsFacadeFetchThreadListeners = new ArrayList<>();
    
    /**
     * Allow to add listener to the list of event listener
     *
     * @param _tagsCollectorThreadListeners a class which will listen to service
     * event
     */
    public void addClientListener(TagsCollectorThreadListener _tagsFacadeThreadListeners) {
        this.tagsFacadeThreadListeners.add(_tagsFacadeThreadListeners);
    }

    /**
     * Allow to remove listener to the list of event listener
     *
     * @param _tagsCollectorThreadListeners a class which will listen to service
     * event
     */
    public void removeClientListener(TagsCollectorThreadListener _tagsFacadeThreadListeners) {
        this.tagsFacadeThreadListeners.remove(_tagsFacadeThreadListeners);
    }

        
    /**
     * Allow to add listener to the list of event listener
     *
     * @param _tagsCollectorThreadListeners a class which will listen to service
     * event
     */
    public void addClientListener(TagsFacadeFetchThreadListener _tagsFacadeFetchThreadListener) {
        this.tagsFacadeFetchThreadListeners.add(_tagsFacadeFetchThreadListener);
    }

    /**
     * Allow to remove listener to the list of event listener
     *
     * @param _tagsCollectorThreadListeners a class which will listen to service
     * event
     */
    public void removeClientListener(TagsFacadeFetchThreadListener _tagsFacadeFetchThreadListener) {
        this.tagsFacadeFetchThreadListeners.remove(_tagsFacadeFetchThreadListener);
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
    public TagsFacadeFetchThread(Machines machine) {
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
                for (int i = 0; i < tagsFacadeThreadListeners.size(); i++) {
                    tagsFacadeThreadListeners.get(i).onProcessingThread(this);
                    tagsFacadeThreadListeners.get(i).onErrorCollection(this,
                            DateUtil.localDTFFZoneId(gmtIndex)
                            + " : Start processing TagsFacadeThread...");
                    Util.out(Util.errLine() + methodName + DateUtil.localDTFFZoneId(gmtIndex)
                            + " : Start processing TagsFacadeThread...");
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
                    for (int i = 0; i < tagsFacadeThreadListeners.size(); i++) {
                        tagsFacadeThreadListeners.get(i).onProcessingSubThread(this);
                    }
                    running = true;
                }

                /**
                 * Check if connection for the façade exist or try to
                 * instanciate
                 */
                if (tagsFacade.isConnectionOn()) {
                    for (int i = 0; i < tagsFacadeThreadListeners.size(); i++) {
                        tagsFacadeThreadListeners.get(i).onSubProcessActivityState(this, true);
                        tagsFacadeThreadListeners.get(i).onErrorCollection(this,
                                DateUtil.localDTFFZoneId(gmtIndex)
                                + " : sql connection ok !");
                    }

                    tagsCollect = tagsFacade.findActiveByCompanyAndMachine(companyId, machine.getId());
                    if (!tagsCollect.equals(tagsActive)) {
                        tagsActive.clear();
                        tagsActive.addAll(tagsCollect);
                        for (TagsFacadeFetchThreadListener tagsFacadeFetchThreadListener : tagsFacadeFetchThreadListeners) {
                            tagsFacadeFetchThreadListener.onUpdateTags(tagsActive);
                        }
                    }

                } else { // Error on connection sql
                    for (int i = 0; i < tagsFacadeThreadListeners.size(); i++) {
                        tagsFacadeThreadListeners.get(i).onSubProcessActivityState(this, false);
                        tagsFacadeThreadListeners.get(i).onErrorCollection(this,
                                DateUtil.localDTFFZoneId(gmtIndex)
                                + " : connection to sql produce error !");

                    }
                }

                /**
                 * Manage reading time of data tags in the machine not less than
                 * 1s
                 */
                long defaultDelay = 1000;
                long delay = Instant.now().toEpochMilli() - subProcessCycleStamp;
                // Inform on execution delay
                for (int i = 0; i < tagsFacadeThreadListeners.size(); i++) {
                    tagsFacadeThreadListeners.get(i).onProcessingSubCycleTime(this, delay);
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
            for (int i = 0; i < tagsFacadeThreadListeners.size(); i++) {
                tagsFacadeThreadListeners.get(i).onProcessingCycleTime(this, delay);
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
        for (int i = 0; i < tagsFacadeThreadListeners.size(); i++) {
            tagsFacadeThreadListeners.get(i).onProcessingStopThread(this);
        }

        Util.out(Util.errLine() + methodName + " Terminate tag collector Controller Thread");
    }

}
