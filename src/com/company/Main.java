package com.company;

import com.company.model.Chromosome;
import com.company.model.Individual;
import com.company.model.MyPopulation1;
import com.company.model.Population;

import java.awt.*;
import java.util.Random;

public class Main {
    private MyPopulation1 population;
    private Individual fittest;
    private Individual secondFittest;
    private int generationCount;
    private static int numberOfGenes;
    private static int n;
    private static int numberOfIndividuals;
    private static boolean verbose;
    private static boolean coloredGenes;

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
        numberOfGenes = 25;
        n = 5;
        //Number of individuals
        numberOfIndividuals = 50;
        //Verbosity (e.g. Should we print genetic pool in the console?)
        verbose = true;
        //Apply color to genes (if verbose = true) Note: this will slow down the process
        coloredGenes = false;

        //===================

        //Initialize population
        Main demo = new Main();
        Point[] points = new Point[10];
        points[0]=new Point(0,0);
        points[1]=new Point(4,1);
        points[2]=new Point(2,0);
        points[3]=new Point(1,3);
        points[4]=new Point(2,1);
        points[5]=new Point(2,4);
        points[6]=new Point(4,0);
        points[7]=new Point(3,3);
        points[8]=new Point(4,1);
        points[9]=new Point(3,4);

        demo.population = new MyPopulation1(numberOfIndividuals, numberOfGenes, n, points);
        System.out.println("Population of "+demo.population.getPopSize()+" individual(s).");

        //Calculate fitness of each individual
        demo.population.calculateFitness();

        System.out.println("\nGeneration: " + demo.generationCount + " Fittest: " + demo.population.getFittestScore());
        //show genetic pool
        showGeneticPool(demo.population.getChromosomes());
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
}
