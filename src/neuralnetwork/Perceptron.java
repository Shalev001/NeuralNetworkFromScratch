/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package neuralnetwork;

/**
 *
 * @author shale
 */
public class Perceptron {
    
    double bias;
    double value;
    
    public Perceptron(double value, double bias){
        this.bias = bias;
        this.value = value;
    }
    public Perceptron(){
        bias = 0;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
}
