/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.obi.services.listener.thread;



/**
 *
 * @author r.hendrick
 */
public interface ManagerFrameEvent {
    
    /**
     * count Event
     * 
     * Emit thread number
     * 
     */
    void countThreadsVM();
    
        
    /**
     * count Event
     * 
     * Emit thread number executing
     * 
     */
    void countThreadsExecuting();
    
    /**
     * Add
     * <p>
     * Emit that the following #thread need to be add to the system. 
     * 
     * @param thread that has been add
     */
    void addThread(Thread thread);
    
    /**
     * Remove
     * <p>
     * Emimt that the following #thread need to be remove from the system
     * 
     * @param thread that has been remove
     */
    void removeThread(Thread thread);
    
    
}
