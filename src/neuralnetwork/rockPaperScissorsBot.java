/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package neuralnetwork;

import activationFunctions.Function;
import activationFunctions.sigmoid;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author shale
 */
public class rockPaperScissorsBot {

    public static void main(String[] args) {

        try {
            File location = new File("C:\\Users\\shale\\OneDrive\\Desktop\\neuralNetworks\\rock-paper-scissorsBot.nnet");

            Network net = Network.importf(location);

            Scanner reader = new Scanner(System.in);
            
            Function actiFunc = new sigmoid();
            
            int num;

            while (true) {
                
                double[] choice = {0,0,0};
                double[] output;

                System.out.println("please enter your choice:\n1:rock\n2:paper\n3:scissors\nany other number will quit");
                
                num = reader.nextInt();
                
                if (num > 0 && num < 4){
                    choice[num-1] = 1;
                }else{
                    break;
                }
                
                net.setInput(new Vector(choice));
                
                net.compute(actiFunc);
                
                output = net.getOutput();
                
                if (output[0] > output[1] && output[0] > output[2]){
                    System.out.println("rock paper scissors bot chooses: Rock");
                }else if (output[1] > output[0] && output[1] > output[2]){
                    System.out.println("rock paper scissors bot chooses: paper");
                }else if (output[2] > output[1] && output[2] > output[0]){
                    System.out.println("rock paper scissors bot chooses: scissors");
                }

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
