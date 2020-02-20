package com.company.model;

import com.company.FlowFreeAlgo;

import java.awt.*;
import java.util.Random;

public class Individual {
    private static final int FITNESS_NOT_SET = -1;

    private int n;
    protected int geneLength;
    private int[] genes;
    private int fitness;

    public Individual(int geneLength, int n, Point[] points) {
        this.n = n;
        this.geneLength = geneLength;
        this.genes = new int[geneLength];

        Random rn = new Random();

        //Set genes randomly for each individual
        for (int i = 0; i < genes.length; ++i) {
            this.genes[i] = rn.nextInt(this.n);
        }

        //put right point in genes
        for (int i = 0; i < points.length; i++) {
            this.genes[points[i].y*n+points[i].x] = (int) i/2;
        }

        this.fitness = -1;
    }

    public int[] getGenes() {
        return genes;
    }

    public int getN() {
        return n;
    }

    public int getSingleGene(int index) {
        return genes[index];
    }

    public void setSingleGene(int index, int value) {
        genes[index] = value;
        fitness = 0;
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
        for (int i = 0; i < geneLength; i++) {
            if(i%n == 0 && i!=0 ) {
                stringBuilder.append("\n");
            }
            stringBuilder.append(genes[i]);
            stringBuilder.append(", ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
