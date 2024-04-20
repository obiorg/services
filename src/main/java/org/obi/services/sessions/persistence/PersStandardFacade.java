package org.obi.services.sessions.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.obi.services.Form.DatabaseFrame;
import org.obi.services.entities.persistence.PersStandard;
import org.obi.services.entities.persistence.Persistence;
import org.obi.services.entities.tags.Tags;
import org.obi.services.model.DatabaseModel;
import org.obi.services.sessions.AbstractFacade;
import org.obi.services.util.Util;

/**
 *
 * @author r.hendrick
 */
public class PersStandardFacade {

    private static PersStandardFacade INSTANCE;

    public static PersStandardFacade getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PersStandardFacade();
        }
        return INSTANCE;
    }

    public PersStandardFacade() {
    }

    Connection conn = null;

    protected Connection getConnectionMannager() {
        if (conn == null) {
            conn = DatabaseFrame.toConnection(DatabaseModel.databaseModel());
        } else try {
            if (conn.isClosed()) {
                conn = DatabaseFrame.toConnection(DatabaseModel.databaseModel());
            }
        } catch (SQLException ex) {
            Util.out(Util.errLine() + PersStandardFacade.class.getSimpleName()
                    + " >> getConnectionMannager on DatabaseFrame.toConnection : " + ex.getLocalizedMessage());
            Logger.getLogger(PersStandardFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    /**
     * Allow to initialize conenction or use it if not exist
     *
     * @return
     */
    public Boolean isConnectionOn() throws SQLException {
        if (conn == null) {
            getConnectionMannager();
        }
        return conn.isValid(10);
    }

    /**
     * General method to process a find process from an established query
     *
     * @param findQuery existing query in string format
     * @return list of result found
     */
    private List<PersStandard> find(String findQuery, Boolean easy) {
        String Q_find = findQuery;

        List<PersStandard> lst = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnectionMannager().createStatement();
            ResultSet rs = stmt.executeQuery(Q_find);
            while (rs.next()) {
                PersStandard m = new PersStandard();
                m.update(rs, easy);
                lst.add(m);
            }
        } catch (SQLException ex) {
            Util.out("PersStandardFacade >> find on getConnectionManager() : " + ex.getLocalizedMessage());
            Logger
                    .getLogger(AbstractFacade.class
                            .getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                };
            } catch (SQLException ex) {
                Util.out("PersStandardFacade >> find on close statement : " + ex.getLocalizedMessage());
                Logger
                        .getLogger(PersStandardFacade.class
                                .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lst;
    }

    /**
     * Find By Id - an element
     *
     * Using request : "SELECT * FROM dbo.pers_standard WHERE id = " + id allow
     * to find specifc element
     *
     * @param id element to find definie by id
     * @return the element specifiy by id
     */
    public PersStandard findById(int id) {
        String Q_finBy = "SELECT * FROM dbo.pers_standard WHERE id = " + id;
        return find(Q_finBy, true).get(0);
    }

    /**
     * This method allow to update many tags on main parameter :
     *
     * <br/>"INSERT INTO [dbo].[pers_standard] "
     * <br />([company] "
     * <br />,[tag] "
     * <br />,[vFloat] "
     * <br />,[vInt] "
     * <br />,[vBool] "
     * <br />,[vStr] "
     * <br />,[vDateTime] "
     * <br />,[vStamp] "
     * <br />,[stampStart] "
     * <br />,[stampEnd] "
     * <br />,[tbf] "
     * <br />,[ttr] "
     * <br />,[error] "
     * <br />,[errorMsg]) "
     * <br />VALUES "
     * <br />(<company, int,> "
     * <br />,<tag, int,> "
     * <br />,<vFloat, float,> "
     * <br />,<vInt, int,> "
     * <br />,<vBool, bit,> "
     * <br />,<vStr, varchar(255),> "
     * <br />,<vDateTime, datetime,> "
     * <br />,<vStamp, datetime,> "
     * <br />,<stampStart, datetime,> "
     * <br />,<stampEnd, datetime,> "
     * <br />,<tbf, real,> "
     * <br />,<ttr, real,> "
     * <br />,<error, bit,> "
     * <br />,<errorMsg, varchar(512),>
     *
     * @param persistenceUpdating persistence list
     * @param tagsPersistence tags data to push
     * @throws java.sql.SQLException error on inserting
     */
    public Boolean pushValue(List<Persistence> persistenceUpdating, List<Tags> tagsPersistence) throws SQLException {

        // Will request prepare statement and push all at once
        String query = "INSERT INTO [dbo].[pers_standard] "
                + "           ([company] "
                + "           ,[tag] "
                + "           ,[vFloat] "
                + "           ,[vInt] "
                + "           ,[vBool] "
                + "           ,[vStr] "
                + "           ,[vDateTime] "
                + "           ,[vStamp] "
                + "           ,[stampStart] "
                + "           ,[stampEnd] "
                + "           ,[tbf] "
                + "           ,[ttr] "
                + "           ,[error] "
                + "           ,[errorMsg]) "
                + "     VALUES "
                + "           ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Disable autocommit
        conn.setAutoCommit(false);

        PreparedStatement ps = getConnectionMannager().prepareStatement(query);
        int requestCnt = 0;
        for (Tags tag : tagsPersistence) {
            if (tag.getPersistenceEnable()) {
                // Check if enable for persistence
                Boolean isInPersistenceList = false;
                for (Persistence p : persistenceUpdating) {
                    isInPersistenceList = p.getTag().getId() == tag.getId();
                }

                // Process if enable for persistence
                if (isInPersistenceList) {
                    ps.setInt(1, tag.getCompany().getId());
                    ps.setInt(2, tag.getId());
                    ps.setDouble(3, tag.getVFloat());
                    ps.setInt(4, tag.getVInt());
                    ps.setBoolean(5, tag.getVBool());
                    ps.setString(6, tag.getVStr());
                    ps.setTimestamp(7, Timestamp.valueOf(tag.getVDateTime()));
                    ps.setTimestamp(8, Timestamp.valueOf(tag.getVStamp()));
                    ps.setTimestamp(9, Timestamp.valueOf(tag.getVStamp()));
                    ps.setTimestamp(10, Timestamp.valueOf(tag.getVStamp()));
                    ps.setDouble(11, 0.0);
                    ps.setDouble(12, 0.0);
                    ps.setBoolean(13, tag.getError());
                    ps.setString(14, tag.getErrorMsg());
                    ps.addBatch();
                    requestCnt++;
                }
            }
        }

        // Get result
        int[] result = ps.executeBatch();
        int requestOk = 0;
        for (Integer r : result) {
            if (r == 1) {
                requestOk++;
            }
        }

        // Commit transaction
        conn.commit();

        // Disable autocommit
        conn.setAutoCommit(false);

        return requestOk == requestCnt;

    }

}
