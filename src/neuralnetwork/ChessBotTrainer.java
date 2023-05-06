/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package neuralnetwork;

import activationFunctions.Function;
import activationFunctions.ReLU;
import chess.ChessBoard;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author shale
 */
public class ChessBotTrainer {
    
    public static void main(String[] args) {
        
        Function actiFunc = new ReLU();
        
        int numberOfOutputs = 5;
        
        int[] networkInfo = {65 , 100, 100, numberOfOutputs*3};
        
        Network nnet1 = new Network(networkInfo);
        ArrayList<Vector[]> nnet1TurnHistory = new ArrayList<>();
        ArrayList<Integer> nnet1choiceHistory = new ArrayList<>();
        
        nnet1.generateRandomWeights();
        nnet1.generateRandomBiases();
        
        
        Network nnet2 = new Network(networkInfo);
        ArrayList<Vector[]> nnet2TurnHistory = new ArrayList<>();
        ArrayList<Integer> nnet2choiceHistory = new ArrayList<>();
        
        nnet2.generateRandomWeights();
        nnet2.generateRandomBiases();
        
        ChessBoard cb = new ChessBoard();
        
        while(!cb.checkMate(1) && !cb.checkMate(0)){
            
            boolean legalMove = true;
           
            Network currentPlayer;
            
            //setting the appropriate network as the player for the turn
            if(cb.getTurn() == 0){
                currentPlayer = nnet2;//this also needs the turn history of each network
            }else{
                currentPlayer = nnet1;
            }
            
            int preTurnEnemyValue = cb.getEnemyPointTotal();
            int preTurnAllyValue = cb.getAlliedPointTotal();
            
            Vector input = cb.toNNetInput(cb.getTurn());
            
            currentPlayer.setInput(input);
            
            //computing the networks choice of move
            currentPlayer.compute(actiFunc);
            
            double[] output = currentPlayer.getOutput();
            int choice = outputToChoice(output);
            
            int[] originalLoc = ChessBoard.indexToCoordinates((int)output[choice*3]);
            int[] newLoc = ChessBoard.indexToCoordinates((int)output[(choice*3)+1]);
            
            //taking the move specified by the network
            if (!cb.takeNextTurn(originalLoc[0], originalLoc[1], newLoc[0], newLoc[1])){
                legalMove = false;
                System.out.println("an impossible move was played");
            }else{
                System.out.println(cb.toString());
            }
            
            //if the network chose an ilegal move it will be trained until it does not
            int reward = 0;
            
            if (!legalMove){
                reward = -10;
            }else{//in the proccess of recording turn history
                Vector[] turn = new Vector[2];
                turn[0] = input;
                turn[1] = new Vector(output);
                
            }
            
        }
        if (cb.checkMate(0)){
            System.out.println("white wins!");
        }else if (cb.checkMate(1)){
            System.out.println("black wins!");
        }
        
    }
    
    /**
     * a method to sample the output to make a choice
     * @param output the output given by the neural network
     * @return
     */
    public static int outputToChoice(double[] output){
        
        double sum = 0;
        
        // normalising the output to have a magnitude of 1
        for (int i = 2; i < output.length; i += 3) {
            sum += output[i];
        }
        
        for (int i = 2; i < output.length; i += 3) {
            output[i] = output[i]/sum;
        }
        
        double rand = Math.random();
        int num = 0;
        
        for (int i = 2; i < output.length; i += 3) {
            rand -= output[i];
            if(rand<0){
                return num;
            }
            num++;
        }
        return --num;
    }
}
