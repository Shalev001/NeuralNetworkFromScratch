
package neuralnetwork;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shale
 */
public class NeuralNetwork {

    
    public static void main(String[] args) {
        
        File file1 = new File("C:\\Users\\shale\\OneDrive\\Desktop\\neuralNetworks\\network1.nnet");
        File file2 = new File("C:\\Users\\shale\\OneDrive\\Desktop\\neuralNetworks\\importTest.nnet");
        

        int[] networkInfo = {3,3};
        
        Network netW = new Network(networkInfo);
        
        netW.generateRandomWeights();
        
        netW.generateRandomBiases();
        
        try {
            netW.export(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        /*
        try {
            netW = Network.importf(file1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        try {
            netW.export(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        
    }
    
}
