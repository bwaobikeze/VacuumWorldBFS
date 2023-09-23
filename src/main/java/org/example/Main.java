package org.example;

import java.util.LinkedList;
import java.util.Queue;

class StateOfWorld{
    int [] currentLocation;
    boolean [][] dirtinMatrix;
    String action;
    StateOfWorld(int [] currentLocation, boolean [][] dirtinMatrix){
        this.currentLocation = currentLocation;
        this.dirtinMatrix = dirtinMatrix;
    }
        }
public class Main {
    public static void main(String[] args) {
            int num = 3;
            Queue<StateOfWorld> VisitedInGraph = new LinkedList<>();
            boolean[][] matrixGraph = new boolean[num][num];
            int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            // Setting the initial dirt
            matrixGraph[2][0] = true;
            matrixGraph[2][1] = true;
            matrixGraph[2][2] = true;

            int[] initialStartOfAgent = {1, 1};

            StateOfWorld startingState = new StateOfWorld(initialStartOfAgent, matrixGraph);
            VisitedInGraph.add(startingState);

            boolean[][] visited = new boolean[num][num]; // Track visited states

            // Starting the breadth-first search
            while (!VisitedInGraph.isEmpty()) {
                StateOfWorld tempState = VisitedInGraph.poll();
                boolean goal = true;

                for (int i = 0; i < num; i++) {
                    for (int j = 0; j < num; j++) {
                        if (tempState.dirtinMatrix[i][j]) {
                            goal = false;
                            break;
                        }
                    }
                }

                for (int[] move : moves) {
                    int newRow = tempState.currentLocation[0] + move[0];
                    int newCol = tempState.currentLocation[1] + move[1];

                    if (newRow >= 0 && newRow < num && newCol >= 0 && newCol < num && !visited[newRow][newCol]) {
                        boolean[][] newDirt = new boolean[num][num];
                        for (int i = 0; i < num; i++) {
                            for (int j = 0; j < num; j++) {
                                newDirt[i][j] = tempState.dirtinMatrix[i][j];
                            }
                        }
                        // Cleaning dirt
                        if (newDirt[newRow][newCol]) {
                            newDirt[newRow][newCol] = false;
                            System.out.println("Cleaned dirt at (" + newRow + ", " + newCol + ")");
                        }
                        // Create a new StateWorld and add it to the queue
                        int[] newLocation = {newRow, newCol};
                        StateOfWorld newState = new StateOfWorld(newLocation, newDirt);
                        VisitedInGraph.add(newState);
                        visited[newRow][newCol] = true; // Mark as visited
                    }
                }
            }
    }

}