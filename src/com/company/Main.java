package com.company;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        FlowFreeAlgo flowFreeAlgo = new FlowFreeAlgo();

        Point[] points = new Point[10];
        points[0]=new Point(0,0);
        points[1]=new Point(1,4);
        points[2]=new Point(2,0);
        points[3]=new Point(1,3);
        points[4]=new Point(2,1);
        points[5]=new Point(2,4);
        points[6]=new Point(4,0);
        points[7]=new Point(3,3);
        points[8]=new Point(4,1);
        points[9]=new Point(3,4);

        int numberOfGenes = 25;
        int n = 5;
        //Number of individuals
        int populationSize = 1000;

        flowFreeAlgo.runAlgorithm(populationSize, numberOfGenes, n, points);
    }
}
