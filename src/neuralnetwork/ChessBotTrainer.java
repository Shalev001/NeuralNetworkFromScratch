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
        
        //8192
        int[] networkInfo = {65 , 4096, 8192, 4096};
        
        Network nnet1 = new Network(networkInfo);
        ArrayList<Vector[]> nnet1TurnHistory = new ArrayList<>();
        ArrayList<Integer> nnet1ChoiceHistory = new ArrayList<>();
        
        nnet1.generateRandomWeights();
        nnet1.generateRandomBiases();
        
        
        Network nnet2 = new Network(networkInfo);
        ArrayList<Vector[]> nnet2TurnHistory = new ArrayList<>();
        ArrayList<Integer> nnet2ChoiceHistory = new ArrayList<>();
        
        nnet2.generateRandomWeights();
        nnet2.generateRandomBiases();
        
        ChessBoard cb = new ChessBoard();
        
        System.out.println("all structures initialized!");
        
        int rep = 0;
        
        while(!cb.checkMate(1) && !cb.checkMate(0)){
            
            rep++;
            
            boolean legalMove = true;
           
            Network currentPlayer;
            Network otherPlayer;
            ArrayList<Vector[]> currentTurnHistory;
            ArrayList<Integer> currentChoiceHistory;
            ArrayList<Vector[]> otherTurnHistory;
            ArrayList<Integer> otherChoiceHistory;
            
            //setting the appropriate network as the player for the turn
            if(cb.getTurn() == 0){
                currentPlayer = nnet2;//this also needs the turn history of each network
                otherPlayer = nnet1;
                currentTurnHistory = nnet2TurnHistory;
                currentChoiceHistory = nnet2ChoiceHistory;
                otherTurnHistory = nnet1TurnHistory;
                otherChoiceHistory = nnet1ChoiceHistory;
            }else{
                currentPlayer = nnet1;
                otherPlayer = nnet2;
                currentTurnHistory = nnet1TurnHistory;
                currentChoiceHistory = nnet1ChoiceHistory;
                otherTurnHistory = nnet2TurnHistory;
                otherChoiceHistory = nnet2ChoiceHistory;
            }
            
            int preTurnEnemyValue = cb.getEnemyPointTotal();
            
            Vector input = cb.toNNetInput(cb.getTurn());
            
            currentPlayer.setInput(input);
            
            //computing the networks choice of move
            currentPlayer.compute(actiFunc);
            
            double[] output = currentPlayer.getOutput();
            int choice = outputToChoice(output);
            
            int[] coordinates = ChessBoard.indexToCoordinates(choice);
            
            //taking the move specified by the network
            if (!cb.takeNextTurn(coordinates[0], coordinates[1], coordinates[2], coordinates[3])){
                legalMove = false;
            }else{
                System.out.println(cb.toString());
            }
            
            //if the network chose an ilegal move it will be trained until it does not
            int reward;
            
            if (!legalMove){
                
                System.out.println(rep);
                
                reward = -10;
                
                // making the chosen move 10% less likly given the same context
                output[choice] *= 1 + reward/100;
                
                currentPlayer.batchGradientDiscent(new Vector(output),1, actiFunc);
                System.out.println("hi");
                
            }else{//in the proccess of recording turn history and training the neural network acording to rewards
                reward = preTurnEnemyValue - cb.getEnemyPointTotal();
                Vector[] turn = new Vector[2];
                turn[0] = input;
                turn[1] = new Vector(output);
                currentTurnHistory.add(turn);
                currentChoiceHistory.add(choice);
                if (reward != 0){//training both networks based on the reward
                    adjustWeights(currentPlayer,currentTurnHistory,currentChoiceHistory,reward,actiFunc);
                    adjustWeights(otherPlayer,otherTurnHistory,otherChoiceHistory,-reward,actiFunc);
                }
            }
            
        }
        if (cb.checkMate(0)){
            System.out.println("white wins!");
            adjustWeights(nnet1,nnet1TurnHistory,nnet1ChoiceHistory,50,actiFunc);
            adjustWeights(nnet2,nnet2TurnHistory,nnet2ChoiceHistory,-50,actiFunc);
        }else if (cb.checkMate(1)){
            System.out.println("black wins!");
            adjustWeights(nnet2,nnet2TurnHistory,nnet2ChoiceHistory,50,actiFunc);
            adjustWeights(nnet1,nnet1TurnHistory,nnet1ChoiceHistory,-50,actiFunc);
        }
        
    }
    
    /**
     * a method to sample the output to make a choice
     * @param output the output given by the neural network
     * @return
     */
    public static int outputToChoice(double[] output){
        
        double sum = 0;
        double rand = Math.random();
        
        for (int i = 0; i < output.length; i++) {
            sum += output[i];
        }
        for (int i = 0; i < output.length; i++) {
            output[i] /= sum; 
        }
        
        for (int i = 0; i < output.length; i++) {
            rand -= output[i];
            if (rand < 0){
                return i;
            }
        }
        
        return output.length - 1;
    }
    
    public static double quadraticCurveMaxOne(int x, int num){
        
        return (x*x)/(num*num);
        
    }
    
    public static void adjustWeights(Network nnet, ArrayList<Vector[]> turnHistory, ArrayList<Integer> choiceHistory,
            int reward, Function actiFunc){
        
        for (int i = 0; i < choiceHistory.size(); i++) {
            Vector[] turn = turnHistory.get(i);
            int choice = choiceHistory.get(i);
            turn[1].setValue(choice, turn[1].getValue(choice) * (1 + reward/100));
            nnet.setInput(turn[0]);
            nnet.batchGradientDiscent(turn[1],quadraticCurveMaxOne(i,choiceHistory.size()), actiFunc);
        }
        
    }
}
