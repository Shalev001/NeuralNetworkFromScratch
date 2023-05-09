package neuralnetwork;

import activationFunctions.Function;
import activationFunctions.sigmoid;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shale
 */
public class NeuralNetwork {

    public static void main(String[] args) {
        
        File exportLoc = new File("C:\\Users\\shale\\OneDrive\\Desktop\\neuralNetworks\\generated-Rock-Paper-Scissors-Bot.nnet");

        int[] netInfo = {3, 3};
        
        Function actiFunc = new sigmoid();
        
        System.out.println("generating random network");

        Network net = new Network(netInfo);

        net.generateRandomWeights();

        double[][] rockPaperScissors = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        Vector[] inputs = {new Vector(rockPaperScissors[0]), new Vector(rockPaperScissors[1]), new Vector(rockPaperScissors[2])};
        Vector[] expectedOutputs = {new Vector(rockPaperScissors[1]), new Vector(rockPaperScissors[2]), new Vector(rockPaperScissors[0])};

        System.out.println("training");
        for (int k = 0; k < 100; k++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 50; j++) {
                    net.setInput(inputs[i]);
                    net.compute(actiFunc);
                    //System.out.println("loss function: "+Network.lossFunk(new Vector(net.getOutput()), expectedOutputs[i]));
                    //System.out.println(Arrays.toString(net.getOutput()) + " : " + expectedOutputs[i].toString());
                    net.batchGradientDiscent(expectedOutputs[i],1,actiFunc);
                }
            }
        }

        System.out.println("training complete");

        Scanner reader = new Scanner(System.in);

        int num;

        while (true) {

            double[] choice = {0, 0, 0};
            double[] output;

            System.out.println("please enter your choice:\n1:rock\n2:paper\n3:scissors\nany other number will quit");

            num = reader.nextInt();

            if (num > 0 && num < 4) {
                choice[num - 1] = 1;
            } else {
                break;
            }

            net.setInput(new Vector(choice));

            net.compute(actiFunc);

            output = net.getOutput();

            if (output[0] > output[1] && output[0] > output[2]) {
                System.out.println("rock paper scissors bot chooses: Rock");
            } else if (output[1] > output[0] && output[1] > output[2]) {
                System.out.println("rock paper scissors bot chooses: paper");
            } else if (output[2] > output[1] && output[2] > output[0]) {
                System.out.println("rock paper scissors bot chooses: scissors");
            }

        }
    }

}
