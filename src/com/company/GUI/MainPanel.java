package com.company.GUI;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel(int n) {
        GridLayout experimentLayout = new GridLayout(n,n);

        this.setLayout(experimentLayout);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                JButton button = new JButton(i + " " + j);
                button.setBackground(Color.BLACK);
                this.add(button);
            }
        }
    }
}
