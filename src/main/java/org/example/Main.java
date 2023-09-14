package org.example;

import java.util.LinkedList;
import java.util.Queue;

class StateOfWorld{
    int [] currentLocation;
    boolean [][] dirtinMatrix;
    StateOfWorld(int [] currentLocation, boolean [][] dirtinMatrix){
        this.currentLocation = currentLocation;
        this.dirtinMatrix = dirtinMatrix;
    }
        }
public class Main {
    public static void main(String[] args) {
        int num = 3;
        Queue <StateOfWorld> VistedInGrpah = new LinkedList<>();
        boolean [][] matrixGrpah = new boolean[num][num];
        int[][] moves = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        //setting the intial dirt
        matrixGrpah[2][0] = true;
        matrixGrpah[2][1] = true;
        matrixGrpah[2][2] = true;

        int []intialStartOfAgent = {1,1};

        StateOfWorld startingState = new StateOfWorld(intialStartOfAgent, matrixGrpah);
        VistedInGrpah.add(startingState);

        //starting the breadth first search

        while(!VistedInGrpah.isEmpty()){
            StateOfWorld tempState = VistedInGrpah.poll();
            boolean goal = true;

            for(int i = 0; i< num ;i++){
                for(int j =0; j< num ; j++){
                    if(tempState.dirtinMatrix[i][j]){
                        goal = false;
                        break;
                    }
                }
            }
            if(goal){
                System.out.println("Goal Reached");
                break;
            }
            for(int []move: moves){
                int newRow = tempState.currentLocation[0] + move[0];
                int newCol = tempState.currentLocation[1] + move[1];

                if(newRow >=0 && newRow < num && newCol >= 0 && newCol < num){
                    boolean[][] newDirt = new boolean[num][num];
                    for(int i =0; i < num ; i++){
                        for(int j=0; j < num ; j++){
                            newDirt[i][j] = tempState.dirtinMatrix[i][j];
                        }
                    }
                    //cleaning dirt
                    if(newDirt[newRow][newCol]){
                        newDirt[newRow][newCol] = false;
                        System.out.println("Cleaned dirt at (" + newRow + ", " + newCol + ")");
                    }
                    //Create a new StateWorld and add it to the quewe
                    int [] newLocation = {newRow, newCol};
                    StateOfWorld newState = new StateOfWorld(newLocation,newDirt);
                    VistedInGrpah.add(newState);
                }
            }
        }

    }

}