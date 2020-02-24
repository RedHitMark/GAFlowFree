package com.company.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.Arrays;

public class MainPanel extends JPanel {
    private JButton[][] buttons;
    private JButton startButton;

    private static final Color[] COLORS = { Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.PINK, Color.BLACK, Color.CYAN, Color.MAGENTA, Color.GRAY};
    private int[][] selection;

    private int n;


    public MainPanel(int n) {
        GridLayout experimentLayout = new GridLayout(n+1,n);

        selection = new int[n][n];
        this.n = n;

        this.setLayout(experimentLayout);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                JButton button = new JButton(i + " " + j);
                button.setBackground(Color.BLACK);

                int finalI = i;
                int finalJ = j;
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        selection[finalI][finalJ] =  selection[finalI][finalJ] == (n-1)? 0 : selection[finalI][finalJ] + 1;
                        button.setBackground(COLORS[selection[finalI][finalJ]]);
                    }
                });
                this.add(button);
            }
        }

        startButton = new JButton("Avvia");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < selection.length; i++) {
                    System.out.println(Arrays.toString(selection[i]));
                }
            }
        });
        this.add(startButton);
    }
}
