/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.obi.services.Docking;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.ini4j.Wini;
import org.obi.services.Form.DatabaseFrame;
import org.obi.services.listener.DatabaseFrameListener;
import org.obi.services.model.DatabaseModel;
import org.obi.services.util.Ico;
import org.obi.services.util.Settings;
import org.obi.services.util.Util;

/**
 *
 * @author r.hendrick
 */
public class SettingsApplicationFrame extends javax.swing.JPanel implements DatabaseFrameListener {

    /**
     * Counter frame allow to count the frame
     */
    public static Integer openFrameCount = 0;
    public static final int xOffset = 30, yOffset = 30;

    /**
     *
     */
    DatabaseFrame dbf = null;

    /**
     * Creates new form SettingsApplicationFrame
     */
    public SettingsApplicationFrame() {
        initComponents();

        // Read company
        String company = Settings.read(Settings.CONFIG, Settings.COMPANY).toString();
        tfCompany.setText(company);

        // Read GMT
        Object o = Settings.read(Settings.CONFIG, Settings.GMT);
        Integer gmt = 0;
        if (o != null) {
            gmt = Integer.valueOf(o.toString());
        }
        fillGMT();
        cbGMT.setSelectedIndex(gmt);

        // Read saved data
        Object tmp = Settings.read(Settings.CONFIG, Settings.URL_OBI);
        if (tmp == null) {
            tmp = "jdbc:sqlserver:<hostname>\\<instance>:<port 1433>;databaseName=<dbName>?<user>?<password>";
        }
        String urlOBI = tmp.toString();//"jdbc:sqlserver:10.116.26.35\\SQLSERVER:1433;databaseName=optimaint?sa?Opt!M@!nt";
        tmp = Settings.read(Settings.CONFIG, Settings.URL_ZEN);
        if (tmp == null) {
            tmp = "jdbc:sqlserver:<hostname>\\<instance>:<port 1433>;databaseName=<dbName>?<user>?<password>";
        }
        String urlZen = tmp.toString();//"jdbc:sqlserver:10.243.59.27\\SQLSERVER;databaseName=scada?sa?s@z3non";
        schemaOBI.setText(urlOBI);
        schemaZen.setText(urlZen);

        // Init table model
//        Object obj = Settings.read(Settings.URL_OBI, Settings.COUNTER);
//        Integer counter = 0;
//        if (obj != null) {
//            counter = Integer.valueOf(obj.toString());
//        }

        // 
//        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
//        openFrameCount++; // increment configFrame counter
//
//        this.setClosable(true);
//        addInternalFrameListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSaveConfig = new javax.swing.JButton();
        btnCancelConfig = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabCompanyPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfCompany = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbGMT = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jSpinner19 = new javax.swing.JSpinner();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jSpinner20 = new javax.swing.JSpinner();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jSpinner21 = new javax.swing.JSpinner();
        jLabel48 = new javax.swing.JLabel();
        jCheckBox7 = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jSpinner22 = new javax.swing.JSpinner();
        jLabel50 = new javax.swing.JLabel();
        tabDatabasePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnDBConnectOBI = new javax.swing.JButton();
        schemaOBI = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        schemaZen = new javax.swing.JTextField();
        btnDBConnectZen = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        btnSaveConfig.setIcon(Ico.i32("/img/oz/save.png", this));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("bundles/Fr_fr"); // NOI18N
        btnSaveConfig.setText(bundle.getString("BtnField_Save")); // NOI18N
        btnSaveConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveConfigActionPerformed(evt);
            }
        });

        btnCancelConfig.setIcon(Ico.i32("/img/oz/cancel.png", this));
        btnCancelConfig.setText(bundle.getString("BtnField_Cancel")); // NOI18N
        btnCancelConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelConfigActionPerformed(evt);
            }
        });

        tabCompanyPanel.setAutoscrolls(true);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText(bundle.getString("ConfigFrameField_companyCode")); // NOI18N

        tfCompany.setText("01");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("GMT");

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestion Alarmes"));

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText("Information Temps");

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel44.setText("s");

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel45.setText("Attention Temps");

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel46.setText("s");

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel47.setText("Erreurs Temps");

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel48.setText("s");

        jCheckBox7.setText("Activer le debuggage");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner19, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(jCheckBox7))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner20, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner21, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jSpinner19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(jCheckBox7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jSpinner20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jSpinner21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Log File"));

        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel49.setText("Taille des fichier");

        jSpinner22.setValue(1);

        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel50.setText("MB");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner22, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(331, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jSpinner22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabCompanyPanelLayout = new javax.swing.GroupLayout(tabCompanyPanel);
        tabCompanyPanel.setLayout(tabCompanyPanelLayout);
        tabCompanyPanelLayout.setHorizontalGroup(
            tabCompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabCompanyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabCompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabCompanyPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfCompany, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE))
                    .addGroup(tabCompanyPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbGMT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tabCompanyPanelLayout.setVerticalGroup(
            tabCompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabCompanyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabCompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabCompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbGMT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Société", tabCompanyPanel);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText(bundle.getString("ConfigFrameField_dbSchemaOptimaint")); // NOI18N

        btnDBConnectOBI.setText(bundle.getString("BtnField_Edit")); // NOI18N
        btnDBConnectOBI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDBConnectOBIActionPerformed(evt);
            }
        });

        schemaOBI.setText("jdbc:sqlserver://10.242.14.2\\BLCSCADA:1433;databaseName=imoka");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText(bundle.getString("ConfigFrameField_dbSchemaZenon")); // NOI18N

        schemaZen.setText("jdbc:sqlserver://10.243.59.27\\SQLSERVER;databaseName=scada");

        btnDBConnectZen.setText(bundle.getString("BtnField_Edit")); // NOI18N
        btnDBConnectZen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDBConnectZenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabDatabasePanelLayout = new javax.swing.GroupLayout(tabDatabasePanel);
        tabDatabasePanel.setLayout(tabDatabasePanelLayout);
        tabDatabasePanelLayout.setHorizontalGroup(
            tabDatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDatabasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabDatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tabDatabasePanelLayout.createSequentialGroup()
                        .addGroup(tabDatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabDatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabDatabasePanelLayout.createSequentialGroup()
                                .addComponent(schemaOBI, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDBConnectOBI))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabDatabasePanelLayout.createSequentialGroup()
                                .addComponent(schemaZen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDBConnectZen)))))
                .addContainerGap())
        );
        tabDatabasePanelLayout.setVerticalGroup(
            tabDatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDatabasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabDatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnDBConnectOBI)
                    .addComponent(schemaOBI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabDatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(btnDBConnectZen)
                    .addComponent(schemaZen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(314, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Base de données", tabDatabasePanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSaveConfig)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelConfig))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelConfig)
                    .addComponent(btnSaveConfig)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveConfigActionPerformed
        // TODO add your handling code here:
        try {
            Wini ini = new Wini(new File(Settings.iniFilename));
            ini.put(Settings.CONFIG, Settings.COMPANY, tfCompany.getText());
            ini.put(Settings.CONFIG, Settings.GMT, cbGMT.getSelectedIndex());

            ini.put(Settings.CONFIG, Settings.URL_OBI, schemaOBI.getText());
            ini.put(Settings.CONFIG, Settings.URL_ZEN, schemaZen.getText());

            ini.store();
            JOptionPane.showMessageDialog(this, "Sauvegarde terminée avec succès", "Sauvegarde", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveConfigActionPerformed

    private void btnCancelConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelConfigActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btnCancelConfigActionPerformed

    private void btnDBConnectOBIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDBConnectOBIActionPerformed
        if (dbf == null) {
            dbf = new DatabaseFrame(null, false);
            dbf.addListener(this);
        }
        dbf.setLocationRelativeTo(this);
        dbf.setModel(
            DatabaseModel
            .parse("Configurations")
            .parse(schemaOBI.getText()));
        dbf.setSchemaReceiver(schemaOBI);
        dbf.setVisible(true);
    }//GEN-LAST:event_btnDBConnectOBIActionPerformed

    private void btnDBConnectZenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDBConnectZenActionPerformed
        if (dbf == null) {
            dbf = new DatabaseFrame(null, false);
            dbf.addListener(this);
        }
        dbf.setLocationRelativeTo(this);
        dbf.setModel(DatabaseModel.parse(schemaZen.getText()));
        dbf.setSchemaReceiver(schemaZen);
        dbf.setVisible(true);
    }//GEN-LAST:event_btnDBConnectZenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelConfig;
    private javax.swing.JButton btnDBConnectOBI;
    private javax.swing.JButton btnDBConnectZen;
    private javax.swing.JButton btnSaveConfig;
    private javax.swing.JComboBox<String> cbGMT;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSpinner jSpinner19;
    private javax.swing.JSpinner jSpinner20;
    private javax.swing.JSpinner jSpinner21;
    private javax.swing.JSpinner jSpinner22;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField schemaOBI;
    private javax.swing.JTextField schemaZen;
    private javax.swing.JPanel tabCompanyPanel;
    private javax.swing.JPanel tabDatabasePanel;
    private javax.swing.JTextField tfCompany;
    // End of variables declaration//GEN-END:variables

    
    
    @Override
    public void databaseFrameEventValidate(DatabaseModel model) {
        String methodName = getClass().getSimpleName() + Logger.getLogger(Util.class
                .getName()).getResourceBundleName() + " : databaseFrameEventValidate() >> ";
        System.out.println(methodName + "databaseFrameEventValidate !");
        //schemaOpti.setText(DatabaseModel.unparse(model));
    }

    @Override
    public void databaseFrameEventCancel(DatabaseModel model) {
        String methodName = getClass().getSimpleName() + Logger.getLogger(Util.class
                .getName()).getResourceBundleName() + " : databaseFrameEventCancel() >> ";
        System.out.println(methodName + "databaseFrameEventCancel !");
    }

    private void fillGMT() {
        List<String> l = orderTimeZones();
        for (String s : l) {
            cbGMT.addItem(s);
        }
    }

    private static List<String> orderTimeZones() {
        List<String> tzs = timeZones();
        Collections.sort(tzs);
        return tzs;
    }

    private static List<String> timeZones() {
        List<String> tzs = new ArrayList<>();
        String[] ids = TimeZone.getAvailableIDs();
        for (String id : ids) {
            tzs.add(displayTimeZone(TimeZone.getTimeZone(id)));
        }
        return tzs;
    }

    private static String displayTimeZone(TimeZone tz) {

        long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset()) - TimeUnit.HOURS.toMinutes(hours);
        // avoid -4:-30 issue
        minutes = Math.abs(minutes);

        String result = "";
        if (hours >= 0) {
            result = String.format("[GMT]+[%02d:%02d] \t %s", hours, minutes, tz.getID());
        } else {
            result = String.format("[GMT]-[%02d:%02d] \t %s", hours, minutes, tz.getID());
        }

        return result;

    }

    /**
     * Get part hour from a display time zone
     *
     * @param timeZone is like [GMT]+[%02d:%02d] \t %s or like [GMT]-[%02d:%02d]
     * \t %s
     * @return
     */
    public static Integer hourFromDisplayTimeZone(String timeZone) {
        if (timeZone == null) {
            return 0;
        }
        if (timeZone.isEmpty()) {
            return 0;
        }
        String clean = timeZone.replace("[GMT]", "").split("\t")[0].replace("+", "").replace("-", "").replace("[", "").replace("]", "");
        String[] a = clean.split(":");
        if (a.length >= 1) {
            return Integer.valueOf(a[0]);
        }
        return 0;
    }

    /**
     * Get part hour from a display time zone
     *
     * @param timeZone is like [GMT]+[%02d:%02d] \t %s or like [GMT]-[%02d:%02d]
     * \t %s
     * @return
     */
    public static Integer minFromDisplayTimeZone(String timeZone) {
        if (timeZone == null) {
            return 0;
        }
        if (timeZone.isEmpty()) {
            return 0;
        }
        String clean = timeZone.replace("[GMT]", "").split("\t")[0].replace("+", "").replace("-", "").replace("[", "").replace("]", "").replace(" ", "");
        String[] a = clean.split(":");
        if (a.length >= 2) {
            return Integer.valueOf(a[1]);
        }
        return 0;
    }

    public static Integer hourAtOrderTimeZonePosition(Integer position) {
        return hourFromDisplayTimeZone(orderTimeZones().get(position));
    }

    public static Integer minAtOrderTimeZonePosition(Integer position) {
        return minFromDisplayTimeZone(orderTimeZones().get(position));
    }

    /**
     * HTZ hours of time zone saved
     *
     * @return hours of time zone save in config
     */
    public static Integer hTZ() {
        Object o = Settings.read(Settings.CONFIG, Settings.GMT);
        Integer gmt = 0;
        if (o != null) {
            gmt = Integer.valueOf(o.toString());
        }
        return hourAtOrderTimeZonePosition(gmt);
    }

    /**
     * MTZ minute of time zone saved
     *
     * @return minute of time zone saved in config
     */
    public static Integer mTZ() {
        Object o = Settings.read(Settings.CONFIG, Settings.GMT);
        Integer gmt = 0;
        if (o != null) {
            gmt = Integer.valueOf(o.toString());
        }
        return minAtOrderTimeZonePosition(gmt);
    }

    /**
     * STZ secode corresponding of time zone saved mean combination of hour and
     * minute
     *
     * @return
     */
    public static Integer sTZ() {
        return (hTZ() * 3600) + (mTZ() * 60);
    }

}
