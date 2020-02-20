package com.company.GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {


    public MainFrame() {
        this.setTitle("GA");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setLocationRelativeTo(null);

        int n = 0;
        do {
            try {
                n  = Integer.parseInt(JOptionPane.showInputDialog(this, "Inserisci il tuo nome", "Titolo Barra", JOptionPane.QUESTION_MESSAGE));
            } catch (NumberFormatException e){
                n = 0;
            }
        } while(n <= 0);

        JPanel jPanel = new MainPanel(n);
        this.getContentPane().add(jPanel);
    }
}
