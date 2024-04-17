package org.obi.services.app;

import org.obi.services.listener.TagsCollectorThreadListener;
import java.awt.TrayIcon;
import static java.lang.Thread.sleep;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.obi.services.core.moka7.IntByRef;
import org.obi.services.core.moka7.S7CpInfo;
import org.obi.services.core.moka7.S7CpuInfo;
import org.obi.services.core.moka7.S7OrderCode;
import org.obi.services.core.moka7.S7Szl;
import org.obi.services.entities.machines.Machines;
import org.obi.services.entities.tags.Tags;
import org.obi.services.listener.ConnectionListener;
import org.obi.services.sessions.tags.TagsFacade;
import org.obi.services.util.DateUtil;
import org.obi.services.util.Ico;
import org.obi.services.util.Settings;
import org.obi.services.util.Util;

/**
 * Tags Collector Thread :
 *
 * The main purpose is to get tags value over a machine. First, connection need
 * to be open with a machine, Than, tags can be collected, At the en connection
 * need to be close with a machine.
 * <p>
 * In order to start the thread you should use normaly start
 * {@link TagsCollectorThread#start()} as all thread system.
 *
 * Note : main loop thread is depend on {@link  TagsCollectorThread#requestKill}
 * which is by default not kill. This loop will run until method
 * {@link TagsCollectorThread#kill()} is called. When finaly killed the
 * following even will be emitted
 * {@link TagsCollectorThreadListener#onProcessingStopThread(java.lang.Thread)}
 *
 *
 * @author r.hendrick
 */
public class TagsCollectorThread extends Thread implements ConnectionListener {

    // Allow to display message on processing
    private TrayIcon trayIcon;
    private final String APP_ICO = "/img/obi/obi-signet-dark.png";

    // Allow to stop process run
    private Boolean requestStop = false;
    private boolean requestKill = false;
    private boolean running = false;

    //!< Machine on which we collecting data
    private Machines machine;

    /**
     * Array list which contain all the TagsCollectorThreadListener listeners
     * that should receive event from client class
     */
    private ArrayList<TagsCollectorThreadListener> tagsCollectorThreadListeners = new ArrayList<>();

    /**
     * Allow to add listener to the list of event listener
     *
     * @param _tagsCollectorThreadListeners a class which will listen to service
     * event
     */
    public void addClientListener(TagsCollectorThreadListener _tagsCollectorThreadListeners) {
        this.tagsCollectorThreadListeners.add(_tagsCollectorThreadListeners);
    }

    /**
     * Allow to remove listener to the list of event listener
     *
     * @param _tagsCollectorThreadListeners a class which will listen to service
     * event
     */
    public void removeClientListener(TagsCollectorThreadListener _tagsCollectorThreadListeners) {
        this.tagsCollectorThreadListeners.remove(_tagsCollectorThreadListeners);
    }

    /**
     * Creates new form
     */
    public TagsCollectorThread(Machines machine) {
        trayIcon = new TrayIcon(Ico.i16(APP_ICO, this).getImage());
        this.machine = machine;

    }

    public TagsCollectorThread(Machines machine, TrayIcon trayIcon) {
        this.trayIcon = trayIcon;
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

        // Check correct startup of Collection
        if (machine == null) {
            doStop();
            Util.out(methodName + " cannot start to run without any machine !\nPlease define machine before running.");
            return;
        }

        // Start parent thread
        super.run();//
        Util.out(Util.errLine() + methodName + "Thead machine : " + machine.getName() + " started with review of connection each 5s");

        // Prepare working element
        TagsFacade tagsFacade = TagsFacade.getInstance();
        MachineConnection mc = new MachineConnection(machine);
        boolean firstTimeInProcessing = true;   //< Inidcate run loop go back at first in main processing loop
        Integer gmtIndex = Integer.valueOf(Settings.read(Settings.CONFIG, Settings.GMT).toString());
        Integer companyId = Integer.valueOf(Settings.read(Settings.CONFIG, Settings.COMPANY).toString());

        /**
         * Create Sub process to write in database
         */
        TagsFacadeThread tagsFacadeThread = TagsFacadeThread.getInstance();
        tagsFacadeThread.doRelease();
        if (!tagsFacadeThread.isAlive()) {
            tagsFacadeThread.start();
        }

        /**
         * START MAIN THREAD LOOP will stop when requestKill is receive by
         * {@link TagsCollectorThread#kill()} or straight requestKill = true.
         */
        while (!requestKill) {
            /**
             * processCycleStamp allow to reduce processing analysis over olding
             * time waiting
             */
            long processCycleStamp = Instant.now().toEpochMilli(); // allow firstime play

            // Inform main thread only once it reach this point after execution of subproces
            if (firstTimeInProcessing) {
                for (int i = 0; i < tagsCollectorThreadListeners.size(); i++) {
                    tagsCollectorThreadListeners.get(i).onProcessingThread(this);
                    tagsCollectorThreadListeners.get(i).onErrorCollection(this,
                            DateUtil.localDTFFZoneId(gmtIndex)
                            + " : " + machine.getName() + " Start processing...");
                    Util.out(Util.errLine() + methodName + " Machine " + machine.getName()
                            + " Start processing...");
                }
                firstTimeInProcessing = false;
            }

            // Check available tags for processing
            Boolean wait = false;
            List<Tags> tags = tagsFacade._findActiveByCompanyAndMachine(companyId, machine.getId());
            if (tags.isEmpty()) {
                wait = true;
                // Inform liteners about number off collection count and error
                for (int i = 0; i < tagsCollectorThreadListeners.size(); i++) {
                    tagsCollectorThreadListeners.get(i).onCollectionCount(this, 0);
                    tagsCollectorThreadListeners.get(i).onErrorCollection(this,
                            DateUtil.localDTFFZoneId(gmtIndex)
                            + " : No tags to read, will run in \"wait\" mode !");
                }
            }

            // SUB PROCESS LOOP
            while (!requestStop & !requestKill & !wait) {
                /**
                 * subProcessCycleStamp allow to reduce processing analysis over
                 * connection mistakes and database access
                 */
                long subProcessCycleStamp = Instant.now().toEpochMilli(); // allow firstime play

                // Inform sub thread only once it reach this after exuction of subprocess 
                if (running == false) {
                    for (int i = 0; i < tagsCollectorThreadListeners.size(); i++) {
                        tagsCollectorThreadListeners.get(i).onProcessingSubThread(this);
                    }
                    running = true;
                }

                /**
                 * Try to connect to machine Inform that machine was not
                 * connected If connected than inform machine is connected
                 */
                if (!mc.getConnected()) {
                    // Specify no conncetion exist
                    for (int i = 0; i < tagsCollectorThreadListeners.size(); i++) {
                        tagsCollectorThreadListeners.get(i).onSubProcessActivityState(this, false);
                        tagsCollectorThreadListeners.get(i).onErrorCollection(this,
                                DateUtil.localDTFFZoneId(gmtIndex)
                                + " : Not connected, start connection...");
                    }
                    // Try to connect
                    if (mc.doConnect()) {
                        for (int i = 0; i < tagsCollectorThreadListeners.size(); i++) {
                            tagsCollectorThreadListeners.get(i).onSubProcessActivityState(this, true);
                            tagsCollectorThreadListeners.get(i).onErrorCollection(this,
                                    DateUtil.localDTFFZoneId(gmtIndex)
                                    + " : Connected !");
                        }
                    } else {// Inform why not able to connect
                        for (int i = 0; i < tagsCollectorThreadListeners.size(); i++) {
                            tagsCollectorThreadListeners.get(i).onSubProcessActivityState(this, false);
                            tagsCollectorThreadListeners.get(i).onErrorCollection(this,
                                    DateUtil.localDTFFZoneId(gmtIndex)
                                    + " : Not connected !" + mc.getErrorText());

                        }
                    }
                }

                //2- create S7 connection to PLC
                Long processTagsCycle = 0l; // 
                while (mc.getConnected() & !requestStop & !requestKill) {
                    /**
                     * subProcessCycleStamp allow to reduce processing analysis
                     * over reading machine access this time in while connected
                     */
                    subProcessCycleStamp = Instant.now().toEpochMilli(); // allow firstime play

                    /**
                     * Cyclical update tags list
                     */
                    if(subProcessCycleStamp - processTagsCycle >= 10000){
                        tags = tagsFacade._findActiveByCompanyAndMachine(companyId, machine.getId());
                        processTagsCycle = Instant.now().toEpochMilli();
                    }
                    
                    // Get all tags list active and available for recovery
                    int tagSize = tags.size();
                    // Inform liteners about number off collection count
                    for (int i = 0; i < tagsCollectorThreadListeners.size(); i++) {
                        tagsCollectorThreadListeners.get(i).onCollectionCount(this, tagSize);
                    }

                    /**
                     * Only process if ther is some tags
                     */
                    if (!tags.isEmpty()) {

                        for (int i = 0; i < tags.size(); i++) {
//                            Tags tag = tags.get(i);
                            // Collect only if cyle time is reached since last change
                            long cycleTime = tags.get(i).getCycle() * 1000; // msec
                            long savedEpoch = DateUtil.epochMilliOf(gmtIndex);
                            if (tags.get(i).getVStamp() != null) {
                                savedEpoch = DateUtil.epochMilliOf(gmtIndex, tags.get(i).getVStamp());//tag.getVStamp().toInstant().toEpochMilli();
                            } else {
                                savedEpoch = DateUtil.epochMilliOf(gmtIndex) - cycleTime - 1;
                            }
                            Long nowEpoch = DateUtil.epochMilliOf(gmtIndex);
                            Long deltaEpoch = (nowEpoch - savedEpoch);
                            if (deltaEpoch > cycleTime) {
                                // Init. default value
                                tags.get(i).setVFloat(0.0);
                                tags.get(i).setVInt(0);
                                tags.get(i).setVBool(false);
                                tags.get(i).setVStr("");
                                tags.get(i).setVDateTime(LocalDateTime.now(ZoneId.of(DateUtil.zoneIdOf(gmtIndex))));
                                tags.get(i).setVStamp(LocalDateTime.now(ZoneId.of(DateUtil.zoneIdOf(gmtIndex))));

                                //TagsTypes tagsType = tagsTypesFacade.findById(tag.getType().getId());
                                if (tags.get(i).getType() != null) {
                                    //tag.setType(tagsType);
                                    mc.readValue(tags.get(i));
                                    //tagsFacade.updateOnValue(tags.get(i));
                                } else {
                                    // Inform liteners about number off collection count and error
                                    for (int j = 0; j < tagsCollectorThreadListeners.size(); j++) {
                                        tagsCollectorThreadListeners.get(j).onErrorCollection(this, Util.errLine() + methodName + " Unable to find type " + tags.get(i).getType() + " for tag " + tags.get(i));
                                    }
                                }
                            }
                        }

                        // Now puh data
                        tagsFacadeThread.addUpdateTags(tags);
                    } else {
                        Util.out(Util.errLine() + methodName + " empty list tag found for machine = " + machine);
                        requestStop = true;

                    }

                    /**
                     * Manage reading time of data tags in the machine not less
                     * than 1s
                     */
                    long defaultDelay = 1000;
                    long delay = Instant.now().toEpochMilli() - subProcessCycleStamp;
                    // Inform on execution delay
                    for (int i = 0; i < tagsCollectorThreadListeners.size(); i++) {
                        tagsCollectorThreadListeners.get(i).onProcessingSubCycleTime(this, delay);
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

                /**
                 * If Machine is still connected we close it by the mean of
                 * requestStop or kill! If request stop it will only appear once
                 */
                if (mc.getConnected() & requestKill) {
                    mc.close();
                    // Inform connection release
                    for (int i = 0; i < tagsCollectorThreadListeners.size(); i++) {
                        tagsCollectorThreadListeners.get(i).onSubProcessActivityState(this, false);
                        tagsCollectorThreadListeners.get(i).onProcessingSubStopThread(this);
                    }
                }

                // sub process stop running
                running = false; //!< indicate end of processus running

                /**
                 * Manage initiation of new request reading Default preset time
                 * is 5s
                 */
                long processDelay = 5000;
                long delay = Instant.now().toEpochMilli() - processCycleStamp;
                // Inform on execution delay
                for (int i = 0; i < tagsCollectorThreadListeners.size(); i++) {
                    tagsCollectorThreadListeners.get(i).onProcessingCycleTime(this, delay);
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
             * Manage initiation of new request reading Default preset time is
             * 5s
             */
            long processDelay = 5000;
            long delay = Instant.now().toEpochMilli() - processCycleStamp;
            // Inform on execution delay
            for (int i = 0; i < tagsCollectorThreadListeners.size(); i++) {
                tagsCollectorThreadListeners.get(i).onProcessingCycleTime(this, delay);
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

        // Release TagsFacadeThread
        if (!tagsFacadeThread.isAlive()) {
            tagsFacadeThread.doStop();
            tagsFacadeThread.kill();
        }

        /**
         * Will kill tags collector controller : inform all client
         */
        for (int i = 0; i < tagsCollectorThreadListeners.size(); i++) {
            tagsCollectorThreadListeners.get(i).onProcessingStopThread(this);
        }
        Util.out(Util.errLine() + methodName + " Terminate tag collector Controller Thread");
    }

    public Machines getMachine() {
        return machine;
    }

    public void setMachine(Machines machine) {
        this.machine = machine;
    }

    @Override
    public void onNewError(int errorCode, String err) {
        for (int i = 0; i < tagsCollectorThreadListeners.size(); i++) {
            tagsCollectorThreadListeners.get(i).onErrorCollection(this,
                    "[" + errorCode + "] - " + err);
        }
    }

    @Override
    public void onConnectionSucced(Integer duration) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onConnectionError(Integer duration) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onPDUUpdate(Integer PDUNegotiationByte) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onDateTimeResponse(Date plcDateTime) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void isProcessing() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onOrderCodeResponse(S7OrderCode orderCode) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onPLCStatusResponse(IntByRef status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onPLCInfoResponse(S7CpuInfo CpuInfo) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onCpInfoResponse(S7CpInfo CpInfo) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onReadSzlResponse(S7Szl SZL) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onConnectionSucced(Machines machine) {

    }

}
