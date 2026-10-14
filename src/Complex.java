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

    public Complex multip(Complex other){

        double cr = this.GetReal();
        double ci = this.GetImage();

        double nr = other.GetReal();
        double ni = other.GetImage();

        double newreal = cr * nr - ci * ni;
        double newimage = cr * ni + nr * ci;

        Complex result = new Complex(newreal, newimage);

        return result;
    }

    public Complex div(Complex other){

        double cr = this.GetReal();
        double ci = this.GetImage();

        double nr = other.GetReal();
        double ni = other.GetImage();

        double newreal = (cr * nr + ci * ni)/(nr*nr + ni*ni);
        double newimage = (nr * ci - cr * ni)/(nr*nr + ni*ni);

        Complex result = new Complex(newreal, newimage);

        return result;
    }

    @Override 
    public String toString() {
        String realStr = String.format("%.2f", real);
        String imagStr = String.format("%.2f", Math.abs(image));

        if (image >= 0) {
            return realStr + " + " + imagStr + "i";
        } else {
            return realStr + " - " + imagStr + "i";
        }
    }
}
