/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.obi.services.listener;

import java.util.List;
import org.obi.services.app.TagsCollectorThread;
import org.obi.services.entities.tags.Tags;

/**
 *
 * @author r.hendrick
 */
public interface TagsCollectorThreadListener {

    /**
     * onProcessingThread
     *
     * <p>
     * Main process is running without indicate if sub process is running
     * {@link TagsCollectorThreadListener#onProcessingSubThread(java.lang.Thread)}.
     * In order to know if process is stopped use
     * {@link TagsCollectorThreadListener#onProcessingStopThread(java.lang.Thread)}
     *
     * @param thread the concern thread
     */
    void onProcessingThread(Thread thread);

    /**
     * onProcessingSubThread
     * <p>
     * Sub thread is on going in the run loop
     *
     * @param thread the concern thread
     */
    void onProcessingSubThread(Thread thread);

    /**
     * onProcessingSubStopThread
     *
     * <p>
     * On sub processing stop thread, main loop still processing waiting for
     * restart. In this case the thread is still alive (check existing method).
     *
     * @param thread the concern thread
     */
    void onProcessingSubStopThread(Thread thread);

    /**
     * onKillProcessThread
     *
     * <p>
     *
     * @param tagsCollectorThread the concern thread
     */
    void onProcessingStopThread(Thread thread);

    /**
     *
     * @param thread the value of thread
     * @param ms the value of ms
     */
    void onProcessingSubCycleTime(Thread thread, Long ms);

    /**
     * Emit thread Cylcle time for the process thread
     *
     * @param thread the value of thread
     * @param ms the value of ms
     */
    void onProcessingCycleTime(Thread thread, Long ms);

    /**
     * Emit thread error message for the specify thread
     *
     * @param thread value of the current thread emitter
     * @param message message delivered
     */
    void onErrorCollection(Thread thread, String message);

    /**
     * Check if main activity is properly processing
     *
     * @param thread the value of thread concern
     * @param activity true if the main purpose activity is on
     */
    void onSubProcessActivityState(Thread thread, Boolean activity);


    /**
     * Emit collection counter 
     * 
     * Indicate number of collection
     * 
     * @param thread the value of emitter
     * @param count  the value count of collection
     */
    void onCollectionCount(Thread thread, int count);

    /**
     * Emit duraction for sequence i
     * 
     * 1- Duration to try to connect !
     * 2- Duration to execute facade findActiveByCompanyAndMachine
     * 3- Duration to read on data
     * 
     * @param thread the value of emitter
     * @param i value of sequence
     * @param toEpochMilli 
     */
    public void onDuration(Thread thread, int i, long toEpochMilli);


}
