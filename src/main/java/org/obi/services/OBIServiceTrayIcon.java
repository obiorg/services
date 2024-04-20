package org.obi.services;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedDarkIJTheme;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.obi.services.Docking.MainWindowDocking;
import org.obi.services.Docking.SettingsApplicationFrame;
import org.obi.services.app.ManagerControllerThread;
import org.obi.services.listener.bdd.DatabaseInfoActionListener;
import org.obi.services.util.Settings;
import org.obi.services.util.Util;

/**
 * OBI Service Tray Icon main entry point
 *
 * OBI Service Tray Icon is the main entry point for the service application. It
 * generate an tray icon in the window status bar of OS. As a result,it start
 * hide in the menu tray.
 *
 * In this entry point default look and feel is selected for the rest of
 * application
 *
 * @author r.hendrick
 */
public class OBIServiceTrayIcon {

    // Variables declaration - do not modify   
    public static ResourceBundle bundle = ResourceBundle.getBundle("bundles/Fr_fr"); // NOI18N
    /* Define logo signet dark path */
    public static final String signet_dark = "/img/obi/obi-signet-dark.png";
    /* Define logo signet light path */
    public static final String signet_light = "/img/obi/obi-signet-light.png";
    /* Application description */
    public static final String app_description = "obi-service";
    /* Application ToolTip description */
    public static final String app_toolTip = "obi-service collect data for OBI";

    /**
     * Main is the entry point
     *
     * @param args that can be past at start : not used !
     */
    public static void main(String[] args) {
        // Manage look and feel
        //
        doLookAndFeel();
        // Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        // Setup configuration ".in" file
        //
        Util.out(Util.errLine() + OBIServiceTrayIcon.class.getSimpleName() + " >> main : OBI - Start...");
        Settings.iniFilename = "obi_service.ini";
        Util.out(Util.errLine() + OBIServiceTrayIcon.class.getSimpleName() + " >> main : Setup configuration file .ini");
        if (Settings.createIniFile()) {
            Settings.writeDefaultClientSetup();
        }

        // Create Tray ICO and set it up
        //
        final TrayIcon trayIcon
                = new TrayIcon(createImage(signet_dark, app_description)
                        .getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        trayIcon.setToolTip(app_toolTip);
        Util.out(Util.errLine() + OBIServiceTrayIcon.class.getSimpleName() + " >> main : Tray icon create");

        // Create a swing thread
        //
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Build GUI Interface
                createAndShowGUI(trayIcon);
                // Tray icon message information
                trayIcon.displayMessage("OBI Service Started",
                        "Application activate in background.",
                        TrayIcon.MessageType.INFO);
            }
        });

        // Affichage plein écrant
        Util.out(Util.errLine() + OBIServiceTrayIcon.class.getSimpleName() + " >> main : program ready.");

    }

    /**
     * Create And Show GUI
     *
     * Will populate tray icon option
     *
     * @param trayIcon
     * @return
     */
    private static TrayIcon createAndShowGUI(TrayIcon trayIcon) {

        // Check the SystemTray support
        //
        if (!SystemTray.isSupported()) {
            Util.out(Util.errLine() + OBIServiceTrayIcon.class.getSimpleName()
                    + " >> CreateAndShowGui >> SystemTray is not supported !");
            return null;
        }

        // Create Tray system
        //
        final SystemTray tray = SystemTray.getSystemTray();

        // Create on main thread that will be use in all child
        //
        ManagerControllerThread managerCtrlThread = new ManagerControllerThread(trayIcon);

        // MainWindow Setup
        //
        MainWindowDocking mw = new MainWindowDocking(trayIcon, managerCtrlThread);

        //
        // 0 - Create a popup menu components
        // 0.0. Menu About
        //
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "OBI Service - is service operation for OBI to collect datas");
            }
        });

        //===== 0.1. Menu Option
        CheckboxMenuItem cb1 = new CheckboxMenuItem("Auto-Size");
        cb1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int cb1Id = e.getStateChange();
                if (cb1Id == ItemEvent.SELECTED) {
                    trayIcon.setImageAutoSize(true);
                } else {
                    trayIcon.setImageAutoSize(false);
                }
            }
        });

        CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
        cb2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int cb2Id = e.getStateChange();
                if (cb2Id == ItemEvent.SELECTED) {
                    trayIcon.setToolTip("OBI Service");
                } else {
                    trayIcon.setToolTip(null);
                }
            }
        });

        Menu optionsMenu = new Menu("Options");
        optionsMenu.add(cb1);
        optionsMenu.add(cb2);
        //===== 0.1. END MENU OPTION

        //===== 0.2. Menu Close
        MenuItem exitItem = new MenuItem("Close");
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
        //===== 0.2. End Menu Close 

        //===== 0.3. Menu Application
        MenuItem obiAppMenuItem = new MenuItem("obi-service");
        obiAppMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mw.setVisible(true);
            }
        });
        //===== 0.3. Menu Application

        //===== 0.4. Menu Configuration BDD
        MenuItem configDBMenuItem = new MenuItem("Configurations");
        configDBMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SettingsApplicationFrame cf = new SettingsApplicationFrame();
                cf.setVisible(true);
            }
        });
        MenuItem infoDBMenuItem = new MenuItem("Informations");
        infoDBMenuItem.addActionListener(new DatabaseInfoActionListener());

        Menu databaseMenu = new Menu("Base de donnée");
        databaseMenu.add(configDBMenuItem);
        databaseMenu.add(infoDBMenuItem);
        //===== 0.4. End Menu Configuration BDD

        // 0.5. Menu Processus
        MenuItem connexionPLCMenuItem = new MenuItem("Connexions PLC");
        connexionPLCMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //ConnectionFrame cf = new ConnectionFrame(trayIcon);
                //cf.setVisible(true);
            }
        });

        MenuItem startProcessusMenuItem = new MenuItem("Démarrer");
        startProcessusMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!managerCtrlThread.isRunning()) {
                    managerCtrlThread.doRelease();
                    if (!managerCtrlThread.isAlive()) {
                        managerCtrlThread.start();
                    }
                } else {
                    trayIcon.displayMessage("OBI",
                            "Processus is already running. Please stop before start !",
                            TrayIcon.MessageType.WARNING);
                    Util.out(Util.errLine() + OBIServiceTrayIcon.class.getSimpleName() + " Processus is already running. Please stop before start !");
                }
            }
        });
        MenuItem stopProcessusMenuItem = new MenuItem("Arrêter");
        stopProcessusMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (managerCtrlThread.isRunning()) {
                    managerCtrlThread.doStop();
                } else {
                    trayIcon.displayMessage("OBI",
                            "Processus is already stopped. Please start before any stop !",
                            TrayIcon.MessageType.WARNING);
                    Util.out(Util.errLine() + OBIServiceTrayIcon.class.getSimpleName() + " Processus is already stopped. Please start before any stop !");
                }
            }
        });
        Menu processusMenu = new Menu("Processus");
//        processusMenu.add(connexionPLCMenuItem);
        processusMenu.add(startProcessusMenuItem);
        processusMenu.add(stopProcessusMenuItem);
        // 0.5. END Menu Processus

        // 1 - Add components to popup menu
        final PopupMenu popup = new PopupMenu();
        popup.add(obiAppMenuItem);
        popup.addSeparator();
//        popup.add(databaseMenu);
        popup.add(processusMenu);
        popup.addSeparator();
        popup.add(optionsMenu);
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(exitItem);

        // 2 - Add popup to a tray ICON
        trayIcon.setPopupMenu(popup);

        // 3 - Add TrayIcon to the system tray
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return null;
        }

        return trayIcon;
    }

    /**
     * Create Image Allow from a path to create an image
     *
     * @param path path to file to get
     * @param description a sub description of the iamge
     * @return Image created
     */
    protected static Image createImage(String path, String description) {
        URL imageURL = OBIServiceTrayIcon.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

    /**
     * Managing Look and feel selection
     */
    private static void doLookAndFeel() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        Util.out(Util.errLine() + OBIServiceTrayIcon.class.getSimpleName() + " Try to check look and feel...");
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            Util.out(Util.errLine() + OBIServiceTrayIcon.class.getSimpleName() + " Look and fell " + info.getName() + " use class : " + info.getClassName());
            if ("FlatGitHubDarkIJtheme".equals(info.getName())) {
                try {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    //break;
                } catch (ClassNotFoundException ex) {
                    Util.out(Util.errLine() + OBIServiceTrayIcon.class.getSimpleName() + " Error on look and feel");
                    Logger.getLogger(TrayIcon.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Util.out(Util.errLine() + OBIServiceTrayIcon.class.getSimpleName() + " Error on look and feel");
                    Logger.getLogger(TrayIcon.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Util.out(Util.errLine() + OBIServiceTrayIcon.class.getSimpleName() + " Error on look and feel");
                    Logger.getLogger(TrayIcon.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Util.out(Util.errLine() + OBIServiceTrayIcon.class.getSimpleName() + " Error on look and feel");
                    Logger.getLogger(TrayIcon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            UIManager.setLookAndFeel(new FlatSolarizedDarkIJTheme());
        } catch (Exception e) {
            Util.out(e.getLocalizedMessage());
        }
        UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
        UIManager.getDefaults().put("TabbedPane.tabsOverlapBorder", true);

        //</editor-fold>
        //</editor-fold>
    }

}
