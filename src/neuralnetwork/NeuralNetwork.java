
package neuralnetwork;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shale
 */
public class NeuralNetwork {

    
    public static void main(String[] args) {
        double[] d1 = {1,2,1};
        double[] d2 = {0,-21,0};
       
        Vector v1 = new Vector(d1);
        Vector v2 = new Vector(d2);
        
        System.out.println(Vector.dotProduct(v1, v2)+" : "+ v1.dotProduct(v2));

    }
    
}
