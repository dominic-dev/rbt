package robot.color;

public class RGB {
    private float[] values;
    private float ERROR_MARGIN;

    public RGB(float[] values){
        this.values = values;
        ERROR_MARGIN = 0.2f;
    }

    public RGB(float[] values, float error_margin){
        this.values = values;
        ERROR_MARGIN = error_margin;
    }

    public float getValue(int i){
        return values[i];
    }

    public float[] getValues(){
        return values;
    }

    public boolean matches(RGB other){
        return matches(other, 0.1f);
    }

    public boolean matches(RGB other, float error_margin){
        for(int i=0; i<values.length; i++){
            if(other.getValue(i) < this.getValue(i) - error_margin){
                return false;
            }
            if(other.getValue(i) > this.getValue(i) + error_margin){
                return false;
            }
        }
        return true;
    }


}
