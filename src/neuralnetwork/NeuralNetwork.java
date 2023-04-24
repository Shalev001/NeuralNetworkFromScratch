
package neuralnetwork;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author shale
 */
public class NeuralNetwork {

    
    public static void main(String[] args) {
        
        File file = new File("C:\\Users\\shale\\OneDrive\\Desktop\\neuralNetworks\\network1.nnet");

        int[] networkInfo = {3,2,2};
        
        Network netW = new Network(networkInfo);
        
        netW.generateRandomWeights();
        
        netW.generateRandomBiases();
        
        try {
            netW.export(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
