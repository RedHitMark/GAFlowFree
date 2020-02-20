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
        int populationSize = 20;

        flowFreeAlgo.runAlgorithm(populationSize, numberOfGenes, n, points);
    }

    /*
    private MyPopulation1 population;
    private Chromosome fittest;
    private Chromosome secondFittest;
    private long generationCount;
    private static int numberOfGenes;
    private static int n;
    private static int numberOfIndividuals;
    private static boolean verbose;
    private static boolean coloredGenes;

    static private Point[] points;

    public Main() {
        //this.population = new MyPopulation1(numberOfIndividuals, numberOfGenes, n);
        //this.population = new MyPopulation1(numberOfIndividuals, numberOfGenes);
        this.generationCount = 0;

        //MainFrame mainFrame = new MainFrame();
        //mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        Random rn = new Random();


        //Set parameters here

        //Number of genes each individual has

        //Verbosity (e.g. Should we print genetic pool in the console?)
        verbose = false;
        //Apply color to genes (if verbose = true) Note: this will slow down the process
        coloredGenes = false;

        //===================

        //Initialize population
        Main demo = new Main();


        demo.population = new MyPopulation1(numberOfIndividuals, numberOfGenes, n, points);
        System.out.println("Population of " + demo.population.getPopSize() + " individual(s).");

        //Calculate fitness of each individual
        demo.population.calculateFitness();

        System.out.println("\nGeneration: " + demo.generationCount + " Fittest: " + demo.population.getFittestScore());
        //show genetic pool
        showGeneticPool(demo.population.getChromosomes());

        //While population gets an individual with maximum fitness
        while (demo.population.getFittestScore() < n) {
            ++demo.generationCount;

            demo.evolvePopulation();

            //Do selection
            demo.selection();

            //Do crossover
            demo.crossover();

            //Do mutation under a random probability
            *//*if (rn.nextInt()%7 < 5) {
                demo.mutation();
            }*//*

            //Add fittest offspring to population
            demo.addFittestOffspring();

            //Calculate new fitness value
            demo.population.calculateFitness();

            System.out.println("\nGeneration: " + demo.generationCount + " Fittest score: " + demo.population.getFittestScore());

            //show genetic pool
            showGeneticPool(demo.population.getChromosomes());
        }

        System.out.println("\nSolution found in generation " + demo.generationCount);
        System.out.println("Index of winner Individual: "+demo.population.getFittestIndex());
        System.out.println("Fitness: "+demo.population.getFittestScore());
        System.out.print("Genes: ");
        for (int i = 0; i < numberOfGenes; i++) {
            System.out.print(demo.population.selectFittest().getGenes()[i]);
        }

        System.out.println("");
    }
    private void evolvePopulation(Population) {
    }

    //show genetic state of the population pool
    static void showGeneticPool(Chromosome[] chromosomes) {
        if(!verbose) return;

        System.out.println("==Genetic Pool==");
        int increment=0;
        for (Chromosome chromosome:chromosomes) {
            System.out.println("> Individual  "+increment+"\n"+(coloredGenes?chromosome.toString():chromosome.toString())+"\n");
            increment++;
        }
        System.out.println("================");
    }

    //Selection
    void selection() {

        //Select the most fittest individual
        fittest = population.selectFittest();

        //Select the second most fittest individual
        secondFittest = population.selectSecondFittest();
    }

    //Crossover
    void crossover() {
        Random rn = new Random();

        //Select a random crossover point
        int crossOverPoint = rn.nextInt(this.numberOfGenes);

        //Swap values among parents
        //TODO this should be random
        for (int i = 0; i < crossOverPoint; i++) {
            int temp = fittest.getGenes()[i];
            fittest.getGenes()[i] = secondFittest.getGenes()[i];
            secondFittest.getGenes()[i] = temp;
        }
    }

    //Mutation
    void mutation() {
        *//*Random rn = new Random();

        //Select a random mutation point
        int mutationPoint = rn.nextInt(this.numberOfGenes);

        //Flip values at the mutation point
        if (fittest.getGenes()[mutationPoint] == 0) {
            fittest.getGenes()[mutationPoint] = 1;
        } else {
            fittest.getGenes()[mutationPoint] = 0;
        }

        mutationPoint = rn.nextInt(this.numberOfGenes);

        if (secondFittest.getGenes()[mutationPoint] == 0) {
            secondFittest.getGenes()[mutationPoint] = 1;
        } else {
            secondFittest.getGenes()[mutationPoint] = 0;
        }*//*
    }

    //Get fittest offspring
    Chromosome getFittestOffspring() {
        if (fittest.getFitness() > secondFittest.getFitness()) {
            return fittest;
        }
        return secondFittest;
    }


    //Replace least fittest individual from most fittest offspring
    void addFittestOffspring() {

        //Update fitness values of offspring
        fittest.evalFitness();
        secondFittest.evalFitness();

        //Get index of least fit individual
        int leastFittestIndex = population.getLeastFittestIndex();

        //Replace least fittest individual from most fittest offspring
        population.getChromosomes()[leastFittestIndex] = getFittestOffspring();
    }*/

}
