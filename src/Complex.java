public class Complex {
    private double real;
    private double image;

    public Complex(){
        this.real = 0.0;
        this.image = 0.0;
    }

    public Complex(double real, double image){
        this.real = real;
        this.image = image;
    }
    
    public double GetReal(){
        return real;
    }

    public double GetImage(){
        return image;
    }

    @Override 
    public String toString(){
        if (image>=0){
            return real + " + " + image + "i";
        }
        else {
            return real + " - " + (-image) + "i";
        }
    }
}
