package com.company;

import com.company.model.Individual;
import com.company.model.Population;

import java.awt.*;

public class FlowFreeAlgo {
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.025;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    //Using for fitness check
    private static final int RIGHT = 1;
    private static final int LEFT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;
    private static final int NOT_POSSIBLE = -1;


    private int maxFitness;
    private static Point[] points;
    private int n;
    private int geneLength;
    private int populationSize;


    public boolean runAlgorithm(int populationSize, int geneLength, int n, Point[] points) {
        this.populationSize = populationSize;
        this.maxFitness = n;
        this.n = n;
        this.geneLength = geneLength;
        this.points = points;

        setMaxFitness(this.n);

        Population myPop = new Population(populationSize, geneLength, n, points, true);

        long generationCount = 1;
        while (myPop.getFittest().getFitness() < getMaxFitness()) {
            System.out.println("Generation: " + generationCount + " Correct genes found: " + myPop.getFittest().getFitness());
            System.out.println(myPop.getFittest().toString());
            myPop = evolvePopulation(myPop);
            generationCount++;
        }
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes: ");
        System.out.println(myPop.getFittest());
        return true;
    }

    public Population evolvePopulation(Population pop) {
        int elitismOffset;
        Population newPopulation = new Population(populationSize, geneLength, n, points, false);

        int randomIndex = 0;
        if (elitism) {
            newPopulation.getIndividuals().add(0, pop.getFittest());
            elitismOffset = 1;
            randomIndex = (int) (Math.random()*(newPopulation.getIndividuals().size()-1))+1;
        } else {
            elitismOffset = 0;
            randomIndex = (int) (Math.random()*newPopulation.getIndividuals().size());
        }



        for (int i = elitismOffset; i < randomIndex; i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.getIndividuals().add(i, newIndiv);
        }
        for (int i = randomIndex; i < newPopulation.getIndividuals().size(); i++) {
            newPopulation.getIndividuals().add(i, new Individual(geneLength, n, points));
        }

        for (int i = elitismOffset; i < newPopulation.getIndividuals().size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    private Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual(geneLength, n, points);
        for (int i = 0; i < newSol.getGenes().length; i++) {
            if (Math.random() <= uniformRate) {
                newSol.setSingleGene(i, indiv1.getSingleGene(i));
            } else {
                newSol.setSingleGene(i, indiv2.getSingleGene(i));
            }
        }
        return newSol;
    }

    private void mutate(Individual indiv) {
        /*for (int i = 0; i < indiv.getDefaultGeneLength(); i++) {
            if (Math.random() <= mutationRate) {
                byte gene = (byte) Math.round(Math.random());
                indiv.setSingleGene(i, gene);
            }
        }*/
    }

    private Individual tournamentSelection(Population pop) {
        Population tournament = new Population(tournamentSize, geneLength, n, points, false);
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.getIndividuals().size());
            tournament.getIndividuals().add(i, pop.getIndividual(randomId));
        }
        Individual fittest = tournament.getFittest();
        return fittest;
    }

    protected int getMaxFitness() {
        return this.maxFitness;
    }

    protected void setMaxFitness(int maxFitness) {
        this.maxFitness = maxFitness;
    }

    public static int getFitness(Individual individual) {
        int fitness = 0;

        int n = individual.getN();

        for (int i = 0; i < n; i++) {
            Point startingPoint = points[i*2];
            Point endingPoint = points[(i*2)+1];

            int prevDir = NOT_POSSIBLE;
            int nextDir = NOT_POSSIBLE;
            Point p = new Point(startingPoint.x, startingPoint.y);
            do {
                //System.out.println(i + " " + p.toString());
                nextDir = findNextDir(individual.getGenes(), n, p, i, prevDir);

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
                prevDir = nextDir;
            } while (nextDir != NOT_POSSIBLE && !endingPoint.equals(p));


            if(endingPoint.equals(p)) {
                fitness++;
            }
        }
        return fitness;
    }

    protected static int findNextDir(int[] genes, int n, Point point, int token, int directionFrom) {
        //first one check all direction
        int count = 0;
        int nextDIR = NOT_POSSIBLE;
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
        if(directionFrom != DOWN && point.y != (n-1) && genes[(point.y+1) * n + (point.x)] == token ) {
            count++;
            nextDIR = UP;
        }
        if(directionFrom != UP && point.y != (0) && genes[(point.y-1) * n + (point.x)] == token ) {
            count++;
            nextDIR = DOWN;
        }

        return count == 1 ? nextDIR : NOT_POSSIBLE;
    }
}
