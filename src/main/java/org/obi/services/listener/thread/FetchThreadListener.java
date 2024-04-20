package org.obi.services.listener.thread;

import java.util.List;
import org.obi.services.entities.persistence.Persistence;
import org.obi.services.entities.tags.Tags;

/**
 *
 * @author r.hendrick
 */
public interface FetchThreadListener {

    /**
     * Emit list of new active tags to be process
     *
     * @param newTags list of new tags that need to be process
     */
    public void onNewTags(List<Tags> newTags);

    /**
     * Emit list of new persistence to be process from specify machine
     *
     * @param newPersistence list of new persistence that need to be process
     */
    public void onNewPersistences(List<Persistence> newPersistence);
}
