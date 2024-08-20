/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services.Form.output;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

/**
 *
 * @author r.hendrick
 */
public class CapturePane extends JPanel implements Consumer {

    private JTextArea output;
    private Integer lineMax = 1000;
    private Boolean pause = false;

    public CapturePane() {
        setLayout(new BorderLayout());
        output = new JTextArea();
        add(new JScrollPane(output));
    }

    @Override
    public void appendText(final String text) {
        if (EventQueue.isDispatchThread()) {
            if (!pause) {
                output.append(text);
                output.setCaretPosition(output.getText().length());
                // Limit the number of line displayed
                if (output.getLineCount() >= lineMax) {
                    try {
                        int end = output.getLineEndOffset(output.getLineCount() - lineMax);
                        output.replaceRange("", 0, end);
                    } catch (BadLocationException ex) {
                        Logger.getLogger(CapturePane.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    appendText(text);
                }
            });

        }
    }

    public Integer getLineMax() {
        return lineMax;
    }

    public void setLineMax(Integer lineMax) {
        this.lineMax = lineMax;
    }

}
