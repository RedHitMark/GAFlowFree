package com.company.model;

import java.awt.*;
import java.util.ArrayList;

public class Population {

    private final Point[] points;
    private int size;
    private int geneLength;
    private int n;

    private ArrayList<Individual> individuals;

    public Population(int size, int geneLength, int n, Point[] points, boolean createNew) {
        this.geneLength = geneLength;
        this.n = n;
        this.points = points;

        individuals = new ArrayList<>();
        if (createNew) {
            createNewPopulation(size);
        }
    }

    public ArrayList<Individual> getIndividuals() {
        return individuals;
    }

    public Individual getIndividual(int index) {
        return individuals.get(index);
    }

    public Individual getFittest() {
        Individual fittest = individuals.get(0);
        for (int i = 0; i < individuals.size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    private void createNewPopulation(int size) {
        for (int i = 0; i < size; i++) {
            Individual newIndividual = new Individual(this.geneLength, this.n, this.points);
            individuals.add(i, newIndividual);
        }
    }
}
