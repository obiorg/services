

package org.obi.services.listener;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.obi.services.Form.DatabaseFrame;
import org.obi.services.Form.DatabaseInformations;
import org.obi.services.OBIServiceTrayIcon;
import org.obi.services.model.DatabaseModel;


/**
 *
 * @author r.hendrick
 */
public class DatabaseInfoActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Connection conn = null;

        try {
            conn = DatabaseFrame.toConnection(DatabaseModel.databaseModel());

            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
                
                DatabaseInformations dbiForm = new DatabaseInformations(new Frame(), true);
                dbiForm.doUpdateWithDatabaseMetaData(dm);
                dbiForm.show();
                
                
                
            } else {
                System.out.println("Imoka Service >> Unable to connect !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OBIServiceTrayIcon.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
