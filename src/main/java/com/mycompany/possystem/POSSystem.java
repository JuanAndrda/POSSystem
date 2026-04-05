package com.mycompany.possystem;

import com.mycompany.possystem.ui.theme.LoginFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class POSSystem {
    public static void main(String[] args) {

        // Set Nimbus dark look and feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    UIManager.put("nimbusBase",               new java.awt.Color(0x0D1B2A));
                    UIManager.put("nimbusBlueGrey",           new java.awt.Color(0x112236));
                    UIManager.put("control",                  new java.awt.Color(0x1A3150));
                    UIManager.put("text",                     new java.awt.Color(0xECF0F1));
                    UIManager.put("nimbusFocus",              new java.awt.Color(0x00AACC));
                    UIManager.put("nimbusSelectionBackground",new java.awt.Color(0x00507A));
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Nimbus not available, using default.");
        }

        // Launch login screen
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}