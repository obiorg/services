/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.obi.services.listener;

import java.util.List;
import org.obi.services.entities.tags.Tags;

/**
 *
 * @author r.hendrick
 */
public interface TagsFacadeFetchThreadListener {
    
    /**
     * Emit list of new active tags to be process
     * 
     * @param tagsActive list of active tags that need to be process
     */
    public void onUpdateTags(List<Tags> tagsActive);
}
