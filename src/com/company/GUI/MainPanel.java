package com.company.GUI;

import com.company.FlowFreeAlgo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    private JButton clearButton;
    private JButton[][] buttons;
    private JButton startButton;
    private JButton stopButton;

    private static final Color[] COLORS = {  Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.BLACK, Color.CYAN, Color.MAGENTA, Color.GRAY};
    private int[][] selection;

    private int n;

    Thread thread;

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

                thread = new Thread(new FlowThread(populationSize, numberOfGenes, n , points));
                thread.start();

                startButton.setEnabled(false);
                stopButton.setEnabled(true);
            }
        });
        this.add(startButton);

        this.stopButton = new JButton("Stop");
        this.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(thread != null && thread.isAlive()) {
                    thread.stop();
                }
                stopButton.setEnabled(false);
                startButton.setEnabled(true);
            }
        });
        this.stopButton.setEnabled(false);
        this.add(stopButton);

        this.clearButton = new JButton("Clear");
        this.clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < selection.length; i++) {
                    for (int j = 0; j < selection[i].length; j++) {
                        selection[i][j] = -1;
                        buttons[i][j].setBackground(Color.WHITE);
                    }
                }
            }
        });
        this.add(this.clearButton);
    }

    private void drawSolution() {
        for (int i = 0; i < selection.length; i++) {
            for (int j = 0; j < selection[i].length; j++) {
                buttons[i][j].setBackground(COLORS[selection[i][j]]);
            }
        }
    }

    private class FlowThread implements Runnable {

        private Point[] points;
        private int n;
        private int geneLength;
        private int populationSize;

        public FlowThread(int populationSize, int geneLength, int n, Point[] points) {
            this.points = points;
            this.n = n;
            this.geneLength = geneLength;
            this.populationSize = populationSize;
        }

        @Override
        public void run() {
            FlowFreeAlgo flowFreeAlgo = new FlowFreeAlgo();
            selection = flowFreeAlgo.runAlgorithm(populationSize, geneLength, n, points);
            drawSolution();

            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }
}
