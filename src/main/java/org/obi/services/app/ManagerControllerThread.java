/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.app;

import org.obi.services.listener.TagsCollectorThreadListener;
import java.awt.TrayIcon;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.obi.services.entities.machines.Machines;
import org.obi.services.sessions.machines.MachinesFacade;
import org.obi.services.util.Ico;
import org.obi.services.util.Settings;
import org.obi.services.util.Util;

/**
 * Manage list of connection over an existing list ????? and the database actual
 * defined machine. Then on the existing list it start by opening connection
 * than let the tag collector to process collection of datas. Finaly one stop or
 * finished request collection or connection dropped close communication to
 * machine
 *
 * @author r.hendrick
 */
public class ManagerControllerThread extends Thread implements TagsCollectorThreadListener {

    // Allow to display message on processing
    private TrayIcon trayIcon;
    private final String APP_ICO = "/img/obi/obi-signet-dark.png";

    // Allow to stop process run
    private Boolean requestStop = false;
    private boolean requestKill = false;
    private boolean running = false;

    //!< List of machines already managed by Manager controller
    List<TagsCollectorThread> tagsCollectorManaged = new ArrayList<>();

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
    public ManagerControllerThread() {
        trayIcon = new TrayIcon(Ico.i16(APP_ICO, this).getImage());

    }

    public ManagerControllerThread(TrayIcon trayIcon) {
        this.trayIcon = trayIcon;
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
     * Delay Method
     *
     * Delay allow to compute the delay between an old epoch compare to actual
     * epoch (now - oldEpoch) = delay
     *
     * @param oldEpoch an epoch value of previous time
     *
     * @return delay in ms from oldEpoch to now
     */
    public static Long delay(Long oldEpoch) {
        return (Instant.now().toEpochMilli() - oldEpoch);
    }

    /**
     * Main loop of the thread data collector
     */
    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        String methodName = getClass().getSimpleName() + " : run() >> ";

        Util.out(methodName + " state of machine connection are review each 5s");
        
        // Récupération des facades de communication bdd
        MachinesFacade machinesFacade = new MachinesFacade();

        // Int Main Loop 
        Integer mainLoop = 0;
        boolean onceOnMain = false; // only display once
        boolean onceOnStop = false; // only display once
        while (!requestKill) {
            long requestEpoch = 0; // allow firstime play
            // Main loop
            while (!requestStop) {
                if (running == false) {
                    tagsCollectorThreadListeners.stream().forEach((tagCollectorThreadListener) -> {
                        tagCollectorThreadListener.onProcessingThread();
                    });
                }
                // Set processus in run mode
                running = true;
                onceOnMain = true;
                int requestEpochCnt = 0;

                // Elapse 5 second between each request ! 
                long delay = delay(requestEpoch);
                if (delay < 5000) {
                    long d = 5000 - delay;
                    try {
                        sleep(d);
                    } catch (InterruptedException ex) {
                        Util.out(methodName + " > Unable to sleep for minimum delay time !" + ex.getLocalizedMessage());
                        Logger.getLogger(ManagerControllerThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                

                // Process only if minimum time is respected
//                long now2 = Instant.now().toEpochMilli();
//                if ((now2 - requestEpoch) >= 3000) {
//                    onceOnStop = true;

                    // change epoch reference
                    requestEpoch = Instant.now().toEpochMilli();
                    requestEpochCnt = 0;

                    // Refresh list of available machine in the company 
                    String societeStr = Settings.read(Settings.CONFIG, Settings.COMPANY).toString();
                    Integer societe = Integer.valueOf(societeStr);
                    List<Machines> machines = machinesFacade.findByCompanyId(societe);

                    // Actualize machine managed list
                    // 1. Remove deleted connection from managed list
                    // 2. Check for une connection to be initiated
                    if (!machines.isEmpty() || !tagsCollectorManaged.isEmpty()) {

                        // 1. REMOVE DELETED CONNECTION IN DATABASE FROM MANAGED LIST
                        // 1.1. Recover machine to remove
                        tagsCollectorManaged.stream().forEach((tagsCollector) -> {
                            // > Check in database if still existing : if true do not remove otherwise remove
                            if (!machines.contains(tagsCollector.getMachine())) {
                                // 1.2. Request to kill this thread collector 
                                // @see void onKillProcessThread(Machines m)
                                tagsCollector.kill();
                            }
                        });

                        // 2 CHECK FOR NEW CONNECTION TO BE INITIATED
                        // 2.1. Recreate list of existing machine
                        List<Machines> machinesManaged = new ArrayList<>();
                        tagsCollectorManaged.stream().forEach((tagsCollector) -> {
                            machinesManaged.add(tagsCollector.getMachine());
                        });

                        // 2.2. Create new machine
                        machines.stream().forEach((machine) -> {
                            // Create not existing connection and start them
                            if (!machinesManaged.contains(machine)) {
                                // Create a new machine connection and start execution
                                TagsCollectorThread t = new TagsCollectorThread(machine);
                                t.doRelease();
                                if (!t.isAlive()) {
                                    t.start();
                                }
                                t.addClientListener(this);
                                // add the new collection to the tags collector manager
                                tagsCollectorManaged.add(t);
                                tagsCollectorThreadListeners.add(t);
                            }
                        });
                    }

//                } else {
//                    requestEpochCnt++;
//                    if (requestEpochCnt >= 2) {
//                        Util.out(methodName + "Epoch not reach more than 2 times : " + requestEpochCnt
//                                + " time : " + now2 + " - " + requestEpoch + " = " + (now2 - requestEpoch));
//                    } else {
//                        Util.out(methodName + "Epoch not reached ! "
//                                + now2 + " - " + requestEpoch + " = " + (now2 - requestEpoch));
//                    }
//                }
//                if (onceOnStop) {
//                    onceOnStop = false;
//                }
            }
            running = false; //!< indicate end of processus running
            if (onceOnMain) {
                Util.out("TagCollectorThread >> run >> back to mainLoop");
                onceOnMain = false;
                tagsCollectorManaged.stream().forEach((tagsCollectorThread) -> {
                    tagsCollectorThread.doStop();
                    tagsCollectorThread.kill();
                });
                tagsCollectorManaged.clear();
//                tagsCollectorThreadListeners.stream().forEach((tagCollectorThreadListener) -> {
//                    tagCollectorThreadListener.onOldingThread();
//                });
            }

            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ManagerControllerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Util.out(methodName + " Terminate Manager Controller Thread");
    }

    @Override
    public void onProcessingThread() {

    }

    @Override
    public void onOldingThread() {

    }

    @Override
    public void onKillProcessThread(TagsCollectorThread t) {
        String methodName = getClass().getSimpleName() + " : onKillProcessThread(Machines m) >> ";
        Util.out(methodName + "Machine connection " + t.getMachine().getAddress() + " remove done !");
        tagsCollectorManaged.remove(t);
        tagsCollectorThreadListeners.remove(t);

        // 1 ! Remove listener !!!!
        Util.out(methodName + " You didn't  remove listener !!!!!! ");

    }

}
