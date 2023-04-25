
package neuralnetwork;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

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

    public Layer getLayer(int index){
        return Layers[index];
    }
    
    public void compute(){
        try{
        for(Layer layer : Layers){
            layer.calcNextLayer();
        }
        }catch(NextLayerDoesNotExistException e){}
    }
    
    public void dumpOutput(){
        Layers[Layers.length-1].dumpvals();
    }
    
    public void generateRandomWeights() {

        int size;
        double[] randweight;
        
        for (int i = 0; i < Layers.length - 1; i++) {

            size = Layers[i].getSize();

            for (int j = 0; j < Layers[i].getWeights().length; j++) {
                
                randweight = new double[size];
                
                for (int k = 0; k <  size; k++) {
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
        writer.println(Layers.length + " number of layers");//number of layers
        for (Layer layer : Layers) {
            writer.println(layer.getSize() + " size");// the size of each layer
        }
        for (Layer layer : Layers) {
            for (Perceptron perceptron : layer.getPerceptrons()) {
                writer.println(perceptron.getBias() + " bias");//every bias for every perceptron in the network
            }
        }
        try {//the last layer will give a nullpointerexception since its weights are not inisialized
            for (Layer layer : Layers) {
                for (int i = 0; i < layer.getWeights().length; i++) {
                    for (double weight : layer.getWeight(i).getContents()) {
                        writer.println(weight + " weight");//every weight
                    }
                }
            }
        } catch (NullPointerException e) {
        }
        writer.flush();
        writer.close();
    }
}
