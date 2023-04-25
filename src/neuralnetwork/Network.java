package neuralnetwork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *
 * @author shale
 */
public class Network {
    
    Layer[] Layers;
    
    public Network(int[] layerInfo) {
        Layers = new Layer[layerInfo.length];
        Layers[0] = new Layer(layerInfo[0]);
        for (int i = 1; i < layerInfo.length; i++) {
            Layers[i] = new Layer(layerInfo[i]);
            Layer.link(Layers[i - 1], Layers[i]);
            Layers[i - 1].initialiseWeights();
        }
    }
    
    public Layer getLayer(int index) {
        return Layers[index];
    }
    
    public void compute() {
        try {
            for (Layer layer : Layers) {
                layer.calcNextLayer();
            }
        } catch (NextLayerDoesNotExistException e) {
        }
    }
    
    public void dumpOutput() {
        Layers[Layers.length - 1].dumpvals();
    }
    
    public void generateRandomWeights() {
        
        int size;
        double[] randweight;
        
        for (int i = 0; i < Layers.length - 1; i++) {
            
            size = Layers[i].getSize();
            
            for (int j = 0; j < Layers[i].getWeights().length; j++) {
                
                randweight = new double[size];
                
                for (int k = 0; k < size; k++) {
                    randweight[k] = Math.random();
                }
                Layers[i].setWeights(j, new Vector(randweight));
            }
        }
    }
    
    public void generateRandomBiases() {
        for (int i = 1; i < Layers.length; i++) {
            for (Perceptron perceptron : Layers[i].getPerceptrons()) {
                perceptron.setBias(Math.random());
            }
        }
    }
    
    public void export(File file) throws IOException {
        PrintWriter writer = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(file)));
        //file formatt:
        writer.println(Layers.length);//number of layers
        for (Layer layer : Layers) {
            writer.println(layer.getSize());// the size of each layer
        }
        for (Layer layer : Layers) {
            for (Perceptron perceptron : layer.getPerceptrons()) {
                writer.println(perceptron.getBias());//every bias for every perceptron in the network
            }
        }
        try {//the last layer will give a nullpointerexception since its weights are not inisialized
            for (Layer layer : Layers) {
                for (int i = 0; i < layer.getWeights().length; i++) {
                    for (double weight : layer.getWeight(i).getContents()) {
                        writer.println(weight);//every weight
                    }
                }
            }
        } catch (NullPointerException e) {}
        writer.flush();
        writer.close();
    }
    
    public static Network importf(File file) throws FileNotFoundException, IOException {
        
        Network output = null;
        
        try {
            BufferedReader reader = new BufferedReader(
                new FileReader(file));
            
            String line = reader.readLine();
            
            int numLayers = Integer.parseInt(line);// getting the number of layers
            int[] networkInfo = new int[numLayers];
            
            for (int i = 0; i < numLayers; i++) {
                line = reader.readLine();
                networkInfo[i] = Integer.parseInt(line);//getting the layer sizes and setting the array to match
            }
            
            output = new Network(networkInfo);
            
            for (int i = 0; i < networkInfo.length; i++) {
                Layer currentLayer = output.getLayer(i);
                for (int j = 0; j < currentLayer.getPerceptrons().length; j++) {
                    
                    line = reader.readLine();
                    
                    double biasVal = Double.parseDouble(line);//getting all the biases
                    
                    currentLayer.getPerceptron(j).setBias(biasVal);
                }
            }
            
            for (int i = 0; i < networkInfo.length-1; i++) {
                
                Layer currentLayer = output.getLayer(i);
                
                for (int j = 0; j < currentLayer.getWeights().length; j++) {
                    
                    double[] temp = new double[networkInfo[i]];
                    
                    for (int k = 0; k < networkInfo[i]; k++) {
                        
                        line = reader.readLine();
                        
                        temp[k] = Double.parseDouble(line);
                        
                    }
                    
                    currentLayer.setWeights(j, new Vector(temp));
                    
                }
            }
            
            reader.close();
            
            return output;
            
        } catch (InputMismatchException e) {
            System.out.println("file not in the correct formatt");
            e.printStackTrace();
            return output;
           
        }
        
        
    }
    
    public static double lossFunk(Vector real, Vector expected){
        
        Vector diff = Vector.subtract(real, expected);
        
        return Vector.magnitude(diff);
    }
}
