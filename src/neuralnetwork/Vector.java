package neuralnetwork;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author shale
 */
public class Vector {

    private int dimension;
    private double[] contents;

    public Vector(double[] contents) {
        this.contents = contents;
        dimension = contents.length;
    }

    public int getDimension() { // no setter for dimension since it is dependent on the contents
        return dimension;
    }

    public double[] getContents() {
        return contents;
    }

    public void setContents(double[] contents) {
        this.contents = contents;
        dimension = contents.length;
    }
    public void setValue(int index,double value){
        contents[index] = value;
    }
    public double getValue(int index){
        return contents[index];
    }

    public double dotProduct(Vector vector) throws VectorDimensionsDoNotMatchException {

        double result = 0;
        double[] vectorContents = vector.getContents();

        if (dimension != vector.getDimension()) {
            throw new VectorDimensionsDoNotMatchException();
        }
        for (int i = 0; i < dimension; i++) {
            result += contents[i] * vectorContents[i];
        }

        return result;
    }

    public static double dotProduct(Vector vector1, Vector vector2) throws VectorDimensionsDoNotMatchException {

        double result = 0;
        double[] vector1Contents = vector1.getContents();
        double[] vector2Contents = vector2.getContents();

        if (vector1.getDimension() != vector2.getDimension()) {
            throw new VectorDimensionsDoNotMatchException();
        }
        for (int i = 0; i < vector1.getDimension(); i++) {
            result += vector1Contents[i] * vector2Contents[i];
        }

        return result;
    }
    
    public static double magnitude(Vector vector){

        double squaresum = 0;
        
        for(double num : vector.getContents()){
            squaresum += num*num;
        }
        
        return Math.sqrt(squaresum);
        
    }
    
    public double magnitude(){

        double squaresum = 0;
        
        for(double num : contents){
            squaresum += num*num;
        }
        
        return Math.sqrt(squaresum);
        
    }

    public void add(Vector vector){
        
        double[] vectorContents = vector.getContents();
        
        if (dimension != vector.getDimension()) {
            throw new VectorDimensionsDoNotMatchException();
        }
        
        for (int i = 0; i < dimension; i++) {
            contents[i] += vectorContents[i];
        }
        
    }
    
    public static Vector add(Vector vector1, Vector vector2){
        
        double[] vector1Contents = vector1.getContents();
        double[] vector2Contents = vector2.getContents();
        double[] result = new double[vector1.getDimension()];

        if (vector1.getDimension() != vector2.getDimension()) {
            throw new VectorDimensionsDoNotMatchException();
        }
        
        for (int i = 0; i < vector1.getDimension(); i++) {
            result[i] = vector1Contents[i] + vector2Contents[i];
        }
        
        return new Vector(result);
        
    }
    
    public void subtract(Vector vector){
        
        double[] vectorContents = vector.getContents();
        
        if (dimension != vector.getDimension()) {
            throw new VectorDimensionsDoNotMatchException();
        }
        
        for (int i = 0; i < dimension; i++) {
            contents[i] -= vectorContents[i];
        }
        
    }
    
    public static Vector subtract(Vector vector1, Vector vector2){
        
        double[] vector1Contents = vector1.getContents();
        double[] vector2Contents = vector2.getContents();
        double[] result = new double[vector1.getDimension()];

        if (vector1.getDimension() != vector2.getDimension()) {
            throw new VectorDimensionsDoNotMatchException();
        }
        
        for (int i = 0; i < vector1.getDimension(); i++) {
            result[i] = vector1Contents[i] - vector2Contents[i];
        }
        
        return new Vector(result);
        
    }
    
    public String toString(){
        String str = "[";
        
        for (double num : contents){
            str += num + ",";
        }
        
        str += "]";
        
        return str;
    }
    
}
