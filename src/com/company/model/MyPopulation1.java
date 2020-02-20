package com.company.model;

import java.awt.*;

public class MyPopulation1 {

    private int popSize;
    private Chromosome[] chromosomes;
    private int geneLength;
    private int n;
    private int fittestScore = 0;



    /**
     * @purpose Initialize population
     * @param popSize is the population size
     * @param geneLength is the number of genes an individual will have
     */
    public MyPopulation1(int popSize, int geneLength, int n, Point[] points) {
        super();
        this.popSize = popSize;
        this.geneLength = geneLength;
        this.chromosomes = new Chromosome[popSize];

        //Create a first population pool
        for (int i = 0; i < popSize; i++) {
            chromosomes[i] = new Chromosome(geneLength, n, points);
        }
    }

    //Get the fittest individual and update fittest score
    public Chromosome selectFittest() {
        int maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < chromosomes.length; i++) {
            if (maxFit <= chromosomes[i].getFitness()) {
                maxFit = chromosomes[i].getFitness();
                maxFitIndex = i;
            }
        }
        //update fittest score
        fittestScore = chromosomes[maxFitIndex].getFitness();

        //try to return the fittest individual
        try {
            return (Chromosome) chromosomes[maxFitIndex].clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Get the second most fittest individual
    public Chromosome selectSecondFittest() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < chromosomes.length; i++) {
            if (chromosomes[i].getFitness() > chromosomes[maxFit1].getFitness()) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if (chromosomes[i].getFitness() > chromosomes[maxFit2].getFitness()) {
                maxFit2 = i;
            }
        }
        try {
            return (Chromosome) chromosomes[maxFit2].clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Get index of least fittest individual
    public int getLeastFittestIndex() {
        int minFitVal = Integer.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < chromosomes.length; i++) {
            if (minFitVal >= chromosomes[i].getFitness()) {
                minFitVal = chromosomes[i].getFitness();
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }

    //Get index of the fittest individual
    public int getFittestIndex() {
        int maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < chromosomes.length; i++) {
            if (maxFit <= chromosomes[i].getFitness()) {
                maxFit = chromosomes[i].getFitness();
                maxFitIndex = i;
            }
        }
        return maxFitIndex;
    }

    //Calculate fitness of each individual
    public void calculateFitness() {
        for (int i = 0; i < chromosomes.length; i++) {
            chromosomes[i].evalFitness();
        }
        selectFittest();
    }

    //Getters and Setters

    public int getPopSize() {
        return popSize;
    }

    public void setPopSize(int popSize) {
        this.popSize = popSize;
    }

    public Chromosome[] getChromosomes() {
        return chromosomes;
    }

    public void setChromosomes(Chromosome[] chromosomes) {
        this.chromosomes = chromosomes;
    }

    public int getGeneLength() {
        return geneLength;
    }

    public void setGeneLength(int geneLength) {
        this.geneLength = geneLength;
    }


    public int getFittestScore() {
        return fittestScore;
    }


    public void setFittestScore(int fittestScore) {
        this.fittestScore = fittestScore;
    }
}
