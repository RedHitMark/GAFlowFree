package com.company.model;

import java.awt.*;
import java.util.Random;

public class Chromosome implements Cloneable{
    //Using for movement
    private static final int RIGHT = 1;
    private static final int LEFT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;
    private static final int NOT_POSSIBLE = -1;

    private int[] genes;

    private int geneLength;

    private int n;

    private int fitness;

    private Point[] constain;

    public Chromosome(int geneLength, int n, Point[] points) {
        this.n = n;
        this.geneLength = geneLength;
        this.genes = new int[geneLength];

        this.constain = points;

        Random rn = new Random();

        //Set genes randomly for each individual
        for (int i = 0; i < genes.length; ++i) {
            this.genes[i] = rn.nextInt(this.n);
        }

        for (int i = 0; i < points.length; i++) {
            this.genes[points[i].y*n+points[i].x] = (int) i/2;
        }

        this.fitness = 0;
    }

    public int getFitness() {
        return fitness;
    }

    public int evalFitness() {
        this.fitness = 0;
        for (int i = 0; i < n; i++) {
            Point startingPoint = constain[i*2];
            Point endingPoint = constain[(i*2)+1];

            int prevDir = NOT_POSSIBLE;
            int nextDir = NOT_POSSIBLE;
            Point p = new Point(startingPoint.x, startingPoint.y);
            do {
                int app = nextDir;
                prevDir = nextDir;
                nextDir = findNextDir(p, i, prevDir);

                switch (nextDir){
                    case RIGHT:
                        p.translate(1, 0);
                        break;
                    case LEFT:
                        p.translate(-1, 0);
                        break;
                    case UP:
                        p.translate(0, 1);
                        break;
                    case DOWN:
                        p.translate(0, -1);
                        break;
                }
            } while (nextDir != NOT_POSSIBLE || endingPoint.equals(p));


            if(endingPoint.equals(p)) {
                this.fitness++;
            }
        }
        return this.fitness;
    }


    private int findNextDir(Point point, int token, int directionFrom) {
        //first one check all direction
        int count = 0;
        int nextDIR = -1;
        //check right only if not on most right
        if(directionFrom != LEFT && point.x != (n-1) && genes[point.y * n + (point.x+1)] == token ) {
            count++;
            nextDIR = RIGHT;
        }
        //check left only if not on most left
        if(directionFrom != RIGHT && point.x != (0) && genes[point.y * n + (point.x-1)] == token ) {
            count++;
            nextDIR = LEFT;
        }
        //check up only if not on top
        if(directionFrom != UP && point.y != (0) && genes[(point.y-1) * n + (point.x)] == token ) {
            count++;
            nextDIR = UP;
        }
        if(directionFrom != DOWN && point.y != (n-1) && genes[(point.y+1) * n + (point.x)] == token ) {
            count++;
            nextDIR = DOWN;
        }

        return count == 1 ? nextDIR : NOT_POSSIBLE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Chromosome chromosome = (Chromosome)super.clone();
        chromosome.genes = new int[geneLength];
        for(int i = 0; i < chromosome.genes.length; i++){
            chromosome.genes[i] = this.genes[i];
        }
        return chromosome;
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
