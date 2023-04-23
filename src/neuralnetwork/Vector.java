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
    
    public Vector(double[] contents){
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
    
    public double dotProduct(Vector vector) throws VectorDimensionsDoNotMatchException{
        
        double result = 0;
        double[] vectorContents = vector.getContents();
        
        if (dimension != vector.getDimension()){
            throw new VectorDimensionsDoNotMatchException();
        }
        for (int i = 0; i < dimension; i++) {
            result += contents[i] * vectorContents[i];
        }
        
        return result;
    }
    
        public static double dotProduct(Vector vector1, Vector vector2) throws VectorDimensionsDoNotMatchException{
        
        double result = 0;
        double[] vector1Contents = vector1.getContents();
        double[] vector2Contents = vector2.getContents();
        
        if (vector1.getDimension() != vector2.getDimension()){
            throw new VectorDimensionsDoNotMatchException();
        }
        for (int i = 0; i < vector1.getDimension(); i++) {
            result += vector1Contents[i] * vector2Contents[i];
        }
        
        return result;
    }
    
}
