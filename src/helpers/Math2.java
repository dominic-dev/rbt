package helpers;

public class Math2 {

    public static float avg(float[] array){
        return sum(array) / array.length;
    
    }

    public static float sum(float[] array){
        float total=0;
        for (float f : array){
            total += f;
        }
        return total;
    }

}
