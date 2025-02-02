/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.obi.services.Docking;

import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.obi.services.listener.thread.ManagerFrameEvent;
import org.obi.services.listener.thread.SystemThreadListener;
import org.obi.services.util.Util;

/**
 *
 * @author r.hendrick
 */
public class ThreadFrame extends javax.swing.JPanel
        implements ManagerFrameEvent {

    public static final int N = 0;
    public static final int ID = 1;
    public static final int NAME = 2;
    public static final int PRIORITY = 3;
    public static final int STATE = 4;
    public static final int STATUS = 5;
//    public static final int STATUS = 6;
//    public static final int SUBPROCESSING = 7;
//    public static final int SUBPROCESSINGCYLE = 8;
//    public static final int MESSAGE = 9;
//    public static final int COLLECTION_COUNT = 10;
//
//    public static final int T_TOCONNECT = 11;
//    public static final int T_TAGS_FIND = 12;
//    public static final int T_TAGS_READING = 13;

    private final DefaultTableModel modelThread;
    private final DefaultTableModel modelThreadApp;

    /**
     * Creates new form ThreadFrame
     */
    public ThreadFrame() {
        initComponents();
        modelThread = (DefaultTableModel) tableThread.getModel();
        modelThreadApp = (DefaultTableModel) tableThreadApp.getModel();
    }

    public void fillThread() {
        clearTableThread();
        clearTableThreadApp();
        
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        for (Thread st : map.keySet()) {
//            Util.out(Util.getLineNumber() + ' '
//                    + " Thread id : " + st.getId()
//                    + " name: " + st.getName()
//                    + " Priority : " + st.getPriority()
//                    + " state : " + st.getState()
//                    + " status : " + st.isAlive()
//                    + " StackTrace : " + st.getStackTrace()
//            );
//
//            for (int i = 0; i < st.getStackTrace().length; i++) {
//                StackTraceElement ste = st.getStackTrace()[i];
//                Util.out("[" + Util.getLineNumber() + "] "
//                        + ste.getClassName()
//                );
//            }

            addVM(st.getId(),
                    st.getName(),
                    st.getPriority(),
                    st.getState(),
                    st.isAlive(),
                    st.getStackTrace()
            );

            
        }

        
        
        // Manage Thread App
        ThreadGroup threadGroup
                = Thread.currentThread().getThreadGroup();
        // getting the total active count of the threads
        int threadCount = threadGroup.activeCount();

        Thread threadList[] = new Thread[threadCount];
        // enumerating over the thread list
        threadGroup.enumerate(threadList);

        // iterating over the for loop to get the names of
        // all the active threads.nbRunning = 0;
        for (int i = 0; i < threadCount; i++) {
            addApp(threadList[i].getId(),
                    threadList[i].getName(),
                    threadList[i].getPriority(),
                    threadList[i].getState(),
                    threadList[i].isAlive(),
                    threadList[i].getStackTrace()
            );
            
            
//            System.out.println(threadList[i].getName());
//            if (threadList[i].getState() == Thread.State.RUNNABLE) {
//                nbRunning++;
//            }
        }
    }

    public void addVM(long id, String name, int priority,
            Thread.State state, Boolean status, StackTraceElement[] Stack) {
        TableModel m = tableThread.getModel();
        Object[] rowData = {
            modelThread.getRowCount() + 1,
            id,
            name,
            priority,
            state,
            status, //            false,
        //            0,
        //            "Aucun",
        //            0, // Tags
        //            0, // T to connect
        //            0, // T to find tags of machine
        //            0 // T to read tags 
        };
        modelThread.addRow(rowData);
        tableThread.setModel(m);
    }

    public void addApp(long id, String name, int priority,
            Thread.State state, Boolean status, StackTraceElement[] Stack) {
        TableModel m = tableThreadApp.getModel();
        Object[] rowData = {
            modelThreadApp.getRowCount() + 1,
            id,
            name,
            priority,
            state,
            status, //            false,
        //            0,
        //            "Aucun",
        //            0, // Tags
        //            0, // T to connect
        //            0, // T to find tags of machine
        //            0 // T to read tags 
        };
        modelThreadApp.addRow(rowData);
        tableThreadApp.setModel(m);
    }

    
    /**
     * Remove all rows from table
     */
    void clearTableThread() {
        DefaultTableModel m = (DefaultTableModel) tableThread.getModel();
        for (int i = m.getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) tableThread.getModel()).removeRow(i);
        }
    }
    void clearTableThreadApp() {
        DefaultTableModel m = (DefaultTableModel) tableThreadApp.getModel();
        for (int i = m.getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) tableThreadApp.getModel()).removeRow(i);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        labThreadCounterTotalVM = new javax.swing.JLabel();
        labelThreadCounterTotalVM = new javax.swing.JLabel();
        labelThreadCounterExecuting = new javax.swing.JLabel();
        labThreadCounterExecuting = new javax.swing.JLabel();
        labelThreadCounterTotalApp = new javax.swing.JLabel();
        labThreadCounterTotalApp = new javax.swing.JLabel();
        labelThreadCounterExecutingApp = new javax.swing.JLabel();
        labThreadCounterExecutingApp = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableThread = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableThreadApp = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Options"));

        btnUpdate.setText("ACTUALISER");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        labThreadCounterTotalVM.setText("0");

        labelThreadCounterTotalVM.setText("Total VM");

        labelThreadCounterExecuting.setText("Executing");

        labThreadCounterExecuting.setText("0");

        labelThreadCounterTotalApp.setText("Total  Application");

        labThreadCounterTotalApp.setText("0");

        labelThreadCounterExecutingApp.setText("Executing Application");

        labThreadCounterExecutingApp.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdate)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelThreadCounterTotalVM, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labThreadCounterTotalVM, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelThreadCounterExecuting, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labThreadCounterExecuting, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelThreadCounterTotalApp, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labThreadCounterTotalApp, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelThreadCounterExecutingApp, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labThreadCounterExecutingApp, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(467, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnUpdate)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labThreadCounterTotalVM)
                            .addComponent(labelThreadCounterTotalVM))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labThreadCounterExecuting)
                            .addComponent(labelThreadCounterExecuting)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labThreadCounterTotalApp)
                            .addComponent(labelThreadCounterTotalApp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labThreadCounterExecutingApp)
                            .addComponent(labelThreadCounterExecutingApp))))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Thread VM"));

        tableThread.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "ID", "Nom", "Priorité", "Etat", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Long.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableThread);
        if (tableThread.getColumnModel().getColumnCount() > 0) {
            tableThread.getColumnModel().getColumn(0).setPreferredWidth(32);
            tableThread.getColumnModel().getColumn(1).setMinWidth(20);
            tableThread.getColumnModel().getColumn(1).setPreferredWidth(32);
            tableThread.getColumnModel().getColumn(2).setPreferredWidth(45);
            tableThread.getColumnModel().getColumn(3).setPreferredWidth(20);
            tableThread.getColumnModel().getColumn(4).setPreferredWidth(45);
            tableThread.getColumnModel().getColumn(5).setPreferredWidth(20);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Thread Application"));

        tableThreadApp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "ID", "Nom", "Priorité", "Etat", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Long.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableThreadApp);
        if (tableThreadApp.getColumnModel().getColumnCount() > 0) {
            tableThreadApp.getColumnModel().getColumn(0).setPreferredWidth(32);
            tableThreadApp.getColumnModel().getColumn(1).setMinWidth(20);
            tableThreadApp.getColumnModel().getColumn(1).setPreferredWidth(32);
            tableThreadApp.getColumnModel().getColumn(2).setPreferredWidth(45);
            tableThreadApp.getColumnModel().getColumn(3).setPreferredWidth(20);
            tableThreadApp.getColumnModel().getColumn(4).setPreferredWidth(45);
            tableThreadApp.getColumnModel().getColumn(5).setPreferredWidth(20);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        countThreadsVM();
        countThreadsExecuting();
        fillThread();
    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labThreadCounterExecuting;
    private javax.swing.JLabel labThreadCounterExecutingApp;
    private javax.swing.JLabel labThreadCounterTotalApp;
    private javax.swing.JLabel labThreadCounterTotalVM;
    private javax.swing.JLabel labelThreadCounterExecuting;
    private javax.swing.JLabel labelThreadCounterExecutingApp;
    private javax.swing.JLabel labelThreadCounterTotalApp;
    private javax.swing.JLabel labelThreadCounterTotalVM;
    private javax.swing.JTable tableThread;
    private javax.swing.JTable tableThreadApp;
    // End of variables declaration//GEN-END:variables

    @Override
    public void countThreadsVM() {
        int nbThreads = Thread.getAllStackTraces().keySet().size();
        labThreadCounterTotalVM.setText(String.valueOf(nbThreads));

        // Manage Thread App
        ThreadGroup threadGroup
                = Thread.currentThread().getThreadGroup();
        // getting the total active count of the threads
        int threadCount = threadGroup.activeCount();
        labThreadCounterTotalApp.setText(String.valueOf(threadCount));
    }

    @Override
    public void countThreadsExecuting() {
        int nbRunning = 0;
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getState() == Thread.State.RUNNABLE) {
                nbRunning++;
            }
        }
        labThreadCounterExecuting.setText(String.valueOf(nbRunning));

        // Manage Thread App
        ThreadGroup threadGroup
                = Thread.currentThread().getThreadGroup();
        // getting the total active count of the threads
        int threadCount = threadGroup.activeCount();

        Thread threadList[] = new Thread[threadCount];
        // enumerating over the thread list
        threadGroup.enumerate(threadList);

        // iterating over the for loop to get the names of
        // all the active threads.
        nbRunning = 0;
        for (int i = 0; i < threadCount; i++) {
            System.out.println(threadList[i].getName());
            if (threadList[i].getState() == Thread.State.RUNNABLE) {
                nbRunning++;
            }
        }
        labThreadCounterExecutingApp.setText(String.valueOf(threadCount));
    }

    @Override
    public void addThread(Thread thread) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeThread(Thread thread) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
