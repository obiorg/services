/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obi.services.Form;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.obi.services.util.Settings;
import org.obi.services.util.Util;
import org.obi.services.listener.DatabaseFrameListener;
import org.obi.services.model.DatabaseModel;


/**
 *
 * @author r.hendrick
 */
public class DatabaseFrame extends javax.swing.JDialog {

    private DatabaseModel saveModel = null;
    // Mange envent lister 
    private ArrayList<DatabaseFrameListener> dbfListeners = new ArrayList<>();

    public void addListener(DatabaseFrameListener listener) {
        dbfListeners.add(listener);
    }

    private JTextField schemaReceiver = null;
    
    public static String queryLastError ="";

    /**
     * Creates new form DatabaseFrame
     */
    public DatabaseFrame(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbDriver = new javax.swing.JComboBox<>();
        server = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        hostname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        port = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        btnValidate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnCheckConnexion = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        databaseName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Paramètre de base de données");
        setModal(true);
        setResizable(false);

        jLabel1.setText("Driver");

        cbDriver.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "mysql", "sqlserver", "postgresql" }));

        jLabel2.setText("Server");

        jLabel3.setText("Hôte");
        jLabel3.setPreferredSize(new java.awt.Dimension(82, 14));

        jLabel4.setText("Port");
        jLabel4.setPreferredSize(new java.awt.Dimension(82, 14));

        jLabel5.setText("Utilisateur");
        jLabel5.setPreferredSize(new java.awt.Dimension(82, 14));

        user.setText("sa");

        jLabel6.setText("Mot de passe");
        jLabel6.setPreferredSize(new java.awt.Dimension(82, 14));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("bundles/Fr_fr"); // NOI18N
        btnValidate.setText(bundle.getString("BtnField_Validate")); // NOI18N
        btnValidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidateActionPerformed(evt);
            }
        });

        btnCancel.setText(bundle.getString("BtnField_Cancel")); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnCheckConnexion.setText(bundle.getString("BtnField_CheckConnexion")); // NOI18N
        btnCheckConnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckConnexionActionPerformed(evt);
            }
        });

        jLabel7.setText("Base de données");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(password)
                            .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCheckConnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnValidate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbDriver, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(server)
                            .addComponent(databaseName)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(port)
                            .addComponent(hostname, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbDriver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(server, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(databaseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hostname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnValidate)
                    .addComponent(btnCheckConnexion))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCheckConnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckConnexionActionPerformed

        if (isConnectable()) {
            JOptionPane.showMessageDialog(this, "Connexion réussie", "Tester la connexion", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Erreur de connexion avec les paramètres : "
                    + "\nDriver : " + cbDriver.getSelectedItem().toString()
                    + "\nUser : " + user.getText()
                    + "\nPasswd : " + password.getText(), "Tester la connexion", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnCheckConnexionActionPerformed

    private void btnValidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidateActionPerformed
        if (isConnectable()) {
            String schema = DatabaseModel.unparse(toModel());
            schemaReceiver.setText(schema);
            dbfListeners.stream().forEach((l) -> {
                l.databaseFrameEventValidate(toModel());
            });

            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Erreur de connexion avec les paramètres : "
                    + "\nDriver : " + cbDriver.getSelectedItem().toString()
                    + "\nUser : " + user.getText()
                    + "\nPasswd : " + password.getText(), "Valider la connexion", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnValidateActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        dbfListeners.stream().forEach((l) -> {
            l.databaseFrameEventCancel(saveModel);
        });
        setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCheckConnexion;
    private javax.swing.JButton btnValidate;
    private javax.swing.JComboBox<String> cbDriver;
    private javax.swing.JTextField databaseName;
    private javax.swing.JTextField hostname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField port;
    private javax.swing.JTextField server;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables

    /**
     * Allow to define model to the current Frame
     *
     * @param model containing available information about database
     */
    public void setModel(DatabaseModel model) {
        this.saveModel = model;
        this.cbDriver.setSelectedItem(model.getDriver());
        this.server.setText(model.getServer());
        this.databaseName.setText(model.getDatabaseName());

        this.hostname.setText(model.getHostname());
        this.port.setText(model.getPort());

        this.user.setText(model.getUser());
        this.password.setText(model.getPassword());
    }

    /**
     * To model convert current setup to a database model
     *
     * @return the model defined by current parameter of database frame
     */
    private DatabaseModel toModel() {
        DatabaseModel model = new DatabaseModel();
        model.setDriver(this.cbDriver.getSelectedItem().toString());
        model.setServer(this.server.getText());
        model.setDatabaseName(databaseName.getText());
        model.setHostname(hostname.getText());
        model.setPort(port.getText());
        model.setUser(user.getText());
        model.setPassword(new String(password.getPassword()));
        return model;
    }

    /**
     * Convenient method to know if a database model can be connected with
     * sepecified information. An url will be create from model in the like
     * "jdbc:sqlserver://localhost:1433;\\SQLEXPRESS;databaseName=Tema6","sa","123456");
     *
     * @param model
     * @return
     */
    public static Boolean isConnectable(DatabaseModel model) {
        String url = "";
        try {
            Class.forName(DatabaseModel.mapReadableToDriver(model.getDriver()));
            url = "jdbc:" + model.getDriver() + "://"
                    + model.getServer() + (model.getPort().trim().isEmpty() ? "" : ":" + model.getPort())
                    + ";databaseName=" + model.getDatabaseName();
            Connection conn = DriverManager.getConnection(url, model.getUser(), model.getPassword());
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Util.out(DatabaseFrame.class.getName() + " >> isConnectable(DatabaseModel model) for url(" + url + ") : " + ex.getLocalizedMessage());
            return false;
        }
    }
    
        /**
     * Convenient method to know if a database model can be connected with
     * sepecified information. An url will be create from model in the like
     * "jdbc:sqlserver://localhost:1433;\\SQLEXPRESS;databaseName=Tema6","sa","123456");
     *
     * @param model
     * @return
     */
    public static Connection toConnection(DatabaseModel model) {
        String url = "";
        try {
            Class.forName(DatabaseModel.mapReadableToDriver(model.getDriver()));
            url = "jdbc:" + model.getDriver() + "://"
                    + model.getServer() 
                    + (model.getPort()==null?"":(model.getPort().trim().isEmpty() ? "" : ":" + model.getPort()))
                    + ";databaseName=" + model.getDatabaseName();
            Connection conn = DriverManager.getConnection(url, model.getUser(), model.getPassword());
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            Util.out(DatabaseFrame.class.getName() + " >> isConnectable(DatabaseModel model) for url(" + url + ") : " + ex.getLocalizedMessage());
            return null;
        }
    }
    

    /**
     * Is Connectable allow to know if the current database model of the frame
     * is connectable.
     *
     * @return true if it is connectable false otherwise
     */
    public Boolean isConnectable() {
        return isConnectable(toModel());
    }

    void setSchemaReceiver(JTextField receiver) {
        schemaReceiver = receiver;
    }

    /**
     * Load Connection allow to get a driver manager connection from a database
     * model
     *
     * @param model which containt information to connect over the system.
     * @return a fonctionnal connection if correctly defined otherwise ull
     */
    public static Connection loadConnection(DatabaseModel model) {
        String url = "";
        try {
            // TODO add your handling code here:
            Class.forName(DatabaseModel.mapReadableToDriver(model.getDriver()));
            url = "jdbc:" + model.getDriver() + "://"
                    + model.getServer() 
                    + (model.getPort()==null ? "" : (model.getPort().trim().isEmpty() ? "" : ":" + model.getPort()))
                    + ";databaseName=" + model.getDatabaseName();
            Connection conn = DriverManager.getConnection(url, model.getUser(), model.getPassword());
            Util.out("La connexion suivante est active :  " + url);
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            queryLastError = DatabaseFrame.class.getName() + " >> isConnectable(DatabaseModel model) for url(" + url + ") : " + ex.getLocalizedMessage();
            Util.out(queryLastError);
            return null;
        }
    }
    
    /**
     * Allow to directly load zenon connection from settings
     * @return zenon connection or null
     */
    public static Connection loadConnectionZenon(){
        DatabaseModel dbm = DatabaseModel.parse(Settings.read(Settings.CONFIG, Settings.URL_ZEN).toString());
        return loadConnection(dbm);
    }
    
    /**
     * Allow to directly load optimaint connection froms settings
     * @return optimaint connection or null
     */
    public static Connection loadConnectionOptimaint(){
        DatabaseModel dbm = DatabaseModel.parse(Settings.read(Settings.CONFIG, Settings.URL_OPTI).toString());
        return loadConnection(dbm);
    }

}
