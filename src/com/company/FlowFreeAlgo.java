package com.company;

import com.company.model.Individual;
import com.company.model.Population;

import java.awt.*;

public class FlowFreeAlgo {
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.025;
    private static final int tournamentSize = 10;
    private static final boolean elitism = false;

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
            System.out.println("Generation: " + generationCount + " Genes: " + myPop.getIndividuals().size() + " Best fitness: " + myPop.getFittest().getFitness());
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

        if (elitism) {
            newPopulation.getIndividuals().add(0, pop.getFittest());
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }


        for (int i = elitismOffset; i < pop.getIndividuals().size() / 2; i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.getIndividuals().add(i, newIndiv);
        }
        for (int i = pop.getIndividuals().size() / 2; i < pop.getIndividuals().size(); i++) {
            newPopulation.getIndividuals().add(i, new Individual(geneLength, n, points));
        }

        /*for (int i = elitismOffset; i < newPopulation.getIndividuals().size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }*/

        return newPopulation;
    }

    private Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual(geneLength, n, points);
        for (int i = 0; i < newSol.getGenes().length; i++) {
            for (int j = 0; j < newSol.getGenes()[i].length; j++) {
                if (Math.random() <= uniformRate) {
                    newSol.setSingleGene(i, j, indiv1.getSingleGene(i, j));
                } else {
                    newSol.setSingleGene(i, j, indiv2.getSingleGene(i, j));
                }
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

            if(isPath(individual.getGenes(), individual.getN(), 0, 3, 2)) {
                fitness++;
            }
        }
        return fitness;
    }

    // method for finding and printing
    // whether the path exists or not
    public static boolean isPath(int matrix[][], int n, int start, int end, int traversable) {
        // defining visited array to keep
        // track of already visited indexes
        boolean visited[][] = new boolean[n][n];

        // flag to indicate whether the path exists or not
        boolean flag=false;

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                // if matrix[i][j] is source
                // and it is not visited
                if(matrix[i][j]==start && !visited[i][j])

                    // starting from i, j and then finding the path
                    if(isPath(matrix, i, j, start, end, traversable, visited))
                    {
                        flag=true; // if path exists
                        break;
                    }
            }
        }

        return flag;
    }


    // method for checking boundries
    public static boolean isSafe(int i, int j, int matrix[][]) {

        if(i>=0 && i<matrix.length && j>=0
                && j<matrix[0].length)
            return true;
        return false;
    }

    // Returns true if there is a path from a source (a
    // cell with value 1) to a destination (a cell with
    // value 2)
    public static boolean isPath(int matrix[][], int i, int j, int start, int end, int traversable,boolean visited[][]){

        // checking the boundries, walls and
        // whether the cell is unvisited
        if(isSafe(i, j, matrix) && (matrix[i][j]==traversable || matrix[i][j]==end || matrix[i][j]==start) && !visited[i][j]) {
            // make the cell visited
            visited[i][j]=true;

            // if the cell is the required
            // destination then return true
            if(matrix[i][j]==end)
                return true;

            // traverse up
            boolean up = isPath(matrix, i-1, j, start, end, traversable, visited);

            // if path is found in up direction return true
            if(up)
                return true;

            // traverse left
            boolean left = isPath(matrix, i, j-1, start, end, traversable, visited);

            // if path is found in left direction return true
            if(left)
                return true;

            //traverse down
            boolean down = isPath(matrix, i+1, j, start, end, traversable, visited);

            // if path is found in down direction return true
            if(down)
                return true;

            // traverse right
            boolean right = isPath(matrix, i, j+1, start, end, traversable, visited);

            // if path is found in right direction return true
            if(right)
                return true;
        }
        return false; // no path has been found
    }

    /*public static int getFitness(Individual individual) {
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
    }*/
}
