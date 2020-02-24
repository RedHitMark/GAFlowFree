package com.company.model;

import com.company.FlowFreeAlgo;

import java.awt.*;
import java.util.Random;

public class Individual {
    private static final int FITNESS_NOT_SET = -1;

    private int n;
    protected int geneLength;
    private int[][] genes;
    private int fitness;
    public boolean[] colorsDone;

    public Individual(int geneLength, int n, Point[] points) {
        this.n = n;
        this.geneLength = geneLength;
        this.genes = new int[n][n];

        this.colorsDone = new boolean[this.n];

        Random rn = new Random();

        //Set genes randomly for each individual
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; j++) {
                this.genes[i][j] = rn.nextInt(this.n);
            }
        }

        //put right point in genes
        for (int i = 0; i < points.length; i++) {
            this.genes[points[i].y][points[i].x] = (int) i/2;
        }

        this.fitness = FITNESS_NOT_SET;
    }

    public int[][] getGenes() {
        return genes;
    }

    public int getN() {
        return n;
    }

    public int getSingleGene(int i, int j) {
        return genes[i][j];
    }

    public void setSingleGene(int i, int j, int value) {
        genes[i][j] = value;
        fitness = FITNESS_NOT_SET;
    }

    public int getFitness() {
        if (fitness == FITNESS_NOT_SET) {
            fitness = FlowFreeAlgo.getFitness(this);
        }
        return fitness;
    }


    @Override
    public String toString() {
        //without colors
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[genes=\n");
        for (int i = 0; i < genes.length; i++) {
            for (int j = 0; j < genes[i].length; j++) {
                stringBuilder.append(genes[i][j]).append(" ");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
