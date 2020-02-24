package com.company.GUI;

import com.company.FlowFreeAlgo;

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

        buttons = new JButton[n][n];

        selection = new int[n][n];
        for (int i = 0; i < selection.length; i++) {
            for (int j = 0; j < selection[i].length; j++) {
                selection[i][j] = -1;
            }
        }
        this.n = n;

        this.setLayout(experimentLayout);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                buttons[i][j] = new JButton(i + " " + j);
                buttons[i][j].setBackground(Color.WHITE);

                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        selection[finalI][finalJ] =  selection[finalI][finalJ] == (n-1)? -1 : selection[finalI][finalJ] + 1;
                        if(selection[finalI][finalJ] == -1) {
                            buttons[finalI][finalJ].setBackground(Color.WHITE);
                        } else {
                            buttons[finalI][finalJ].setBackground(COLORS[selection[finalI][finalJ]]);
                        }
                    }
                });
                this.add(buttons[i][j]);
            }
        }

        startButton = new JButton("Avvia");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfGenes = n * n;
                int populationSize = 100;

                Point[] points = new Point[n*2];

                for (int i = 0; i < selection.length; i++) {
                    for (int j = 0; j < selection[i].length; j++) {
                        if(selection[i][j] != -1) {
                            if(points[selection[i][j]*2] == null) {
                                points[selection[i][j]*2] = new Point(j, i);
                            } else {
                                points[(selection[i][j]*2) + 1 ] = new Point(j, i);
                            }
                        }
                    }
                }

                //System.out.println(Arrays.toString(points));

                FlowFreeAlgo flowFreeAlgo = new FlowFreeAlgo();
                selection = flowFreeAlgo.runAlgorithm(populationSize, numberOfGenes, n, points);

                drawSolution();
            }
        });
        this.add(startButton);
    }

    private void drawSolution() {
        for (int i = 0; i < selection.length; i++) {
            for (int j = 0; j < selection[i].length; j++) {
                buttons[i][j].setBackground(COLORS[selection[i][j]]);
            }
        }
    }
}
