/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package neuralnetwork;

/**
 *
 * @author shale
 */
public class Layer {
    
    public double sigmoid(double input){
        return (1/(1+Math.pow(Math.E,-input)));
    }
    
    protected Layer previosLayer;
    protected Layer nextLayer;
    protected Perceptron[] perceptrons;
    protected double[] perceptronValues;
    protected Vector[] weights;
    protected int size;
    protected int numweights; //a variable intended to contain the number of expected weights between this and the last layer
    
    public Layer(int size){
        this.size = size;
        perceptrons = new Perceptron[size];
        perceptronValues = new double[size];
        nextLayer = null;
        previosLayer = null;
        for (int i = 0; i < size; i++) {
            perceptrons[i] = new Perceptron(0,0);
            perceptronValues[i] = 0;
        }
    }
    
    public void setValues(Vector newVals) throws VectorDimensionsDoNotMatchException{
        
        double[] contents = newVals.getContents();
        
        if (newVals.getDimension() != size){
            throw new VectorDimensionsDoNotMatchException();
        }
        for (int i = 0; i < size; i++) {
            getPerceptron(i).setValue(contents[i]);
            perceptronValues[i] = contents[i];
        }
    }
    
    /**
     * a method to link the current layer with a pre-existing layer
     * @param previosLayer 
     */
    public static void link(Layer previosLayer, Layer nextLayer){
        previosLayer.setNextLayer(nextLayer);
        nextLayer.setPreviosLayer(previosLayer);  
    }

    public Layer getPreviosLayer() {
        return previosLayer;
    }

    public void setPreviosLayer(Layer previosLayer) {
        this.previosLayer = previosLayer;
    }

    public Layer getNextLayer() {
        return nextLayer;
    }

    public void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }

    public Perceptron[] getPerceptrons() {
        return perceptrons;
    }
    
    public Perceptron getPerceptron(int index) {
        return perceptrons[index];
    }

    public void setPerceptrons(Perceptron[] perceptrons) {
        this.perceptrons = perceptrons;
        for (int i = 0; i < perceptrons.length; i++) {
            perceptronValues[i] = perceptrons[i].getValue();
        }
    }
    
    public void setPerceptron(int index,Perceptron perceptron) {
        perceptrons[index] = perceptron;
        perceptronValues[index] = perceptron.getValue();
    }

    public int getSize() {
        return size;
    }

    public Vector[] getWeights() {
        return weights;
    }
    
    public Vector getWeight(int index) {
        return weights[index];
    }

    public void setWeights(Vector[] weights) {
        this.weights = weights;
    }
    
    /**
     * a method to set the weights for one of the perceptrons in the next layer
     * @param index the index of the perceptron in the next layer to set the weights for
     * @param weights the new set of weights
     */
    public void setWeights(int index, Vector weights){
        this.weights[index] = weights;
    }
    
    /**
     * a method to initialise the weights between the current and next layer
     */
    public void initialiseWeights() throws NextLayerDoesNotExistException{
        
        weights = new Vector[nextLayer.getSize()];
        
        if (nextLayer == null){
            throw new NextLayerDoesNotExistException();
        }
        
        for (int i = 0; i < nextLayer.getSize(); i++) {
            weights[i] = new Vector(new double[size]);
        }
    }
    
    /**
     * a method that uses the existing wights to calculate the values of the perceptrons in the next Layer
     */
    public void calcNextLayer() throws NextLayerDoesNotExistException{
        double val;
        Vector values = new Vector(perceptronValues);
        
        if (nextLayer == null){
            throw new NextLayerDoesNotExistException();
        }
        
        for (int i = 0; i < nextLayer.getSize(); i++) {
            
            val = nextLayer.getPerceptron(i).getBias()+ Vector.dotProduct(values, weights[i]);
            
            val = sigmoid(val);
            
            nextLayer.getPerceptron(i).setValue(val);
        }
    }
    
    public void dumpvals(){
        for(Perceptron perc : perceptrons){
            System.out.println(perc.getValue());
        }
    }
    
    public double[] getvals(){
        double[] output = new double[size];
        for (int i = 0; i < size; i++) {
            output[i] = perceptrons[i].getValue();
        }
        return output;
    }
}
