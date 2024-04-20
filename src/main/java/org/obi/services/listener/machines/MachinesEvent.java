/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.obi.services.listener.machines;

import org.obi.services.entities.machines.Machines;

/**
 *
 * @author r.hendrick
 */
public interface MachinesEvent {
    
    /**
     * count Event
     * 
     * Emit that number of machine is now at count
     * 
     * @param count is the actuel number of machine
     */
    void countEvent(int count);
    
    /**
     * Add
     * <p>
     * Emit that the following machine need to be add to the system. 
     * 
     * @param machine that has been add
     */
    void addEvent(Machines machine);
    
    /**
     * Remove
     * <p>
     * Emimt that the following #machine need to be remove from the system
     * 
     * @param machine that has been remove
     */
    void removeEvent(Machines machine);
    
    
}
