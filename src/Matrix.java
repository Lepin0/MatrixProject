public class Matrix{
    private Complex[][] data;

    public Matrix(int rows, int cols){
        this.data = new Complex[rows][cols];

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                data[i][j] = new Complex();
            }
        }
    }

    public void setElement(int rows, int cols, Complex value){
        this.data[rows][cols] = value;
    }

    public void PrinttoConsole(){

        for (int i = 0; i < data.length; i++){
            for (int j = 0; j < data[i].length; j++){
                System.out.print(data[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public Matrix add(Matrix other){
        if (this.data.length != other.data.length || this.data[0].length != other.data[0].length){
            System.out.println("Матрица не подходит по размерам");
            return null;
        }

        int rows = this.data.length;
        int cols = this.data[0].length;
        Matrix result = new Matrix(rows,cols);

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){

                Complex c1 = this.data[i][j];
                Complex c2 = other.data[i][j];

                double newreal = c1.GetReal() + c2.GetReal();
                double newimage = c1.GetImage() + c2.GetImage();

                result.data[i][j] = new Complex(newreal, newimage);
            }    
        }
        return result;
    }

    public Matrix subtract(Matrix other){
        if (this.data.length != other.data.length || this.data[0].length != other.data[0].length){
            System.out.println("Матрица не подходит по размерам");
            return null;
        }

        int rows = this.data.length;
        int cols = this.data[0].length;
        Matrix result = new Matrix(rows,cols);

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){

                Complex c1 = this.data[i][j];
                Complex c2 = other.data[i][j];

                double newreal = c1.GetReal() - c2.GetReal();
                double newimage = c1.GetImage() - c2.GetImage();

                result.data[i][j] = new Complex(newreal, newimage);
            }    
        }
        return result;
    }

    public Matrix Transporation(){
        int rows = this.data[0].length;
        int cols = this.data.length;
        Matrix trans = new Matrix(rows,cols);

         for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                trans.data[i][j] = this.data[j][i];
            }
        }
        return trans;
    }

    public Matrix multip_copmplex(Complex value){
        int rows = this.data.length;
        int cols  = this.data[0].length;
        Matrix result = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){

                result.data[i][j] = this.data[i][j].multip(value);
            }
        }
        return result;
    }

    public Matrix div_copmplex(Complex value){
        int rows = this.data.length;
        int cols  = this.data[0].length;
        Matrix result = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){

                result.data[i][j] = this.data[i][j].div(value);
            }
        }
        return result;
    }
}