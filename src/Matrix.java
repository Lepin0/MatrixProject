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

    public void PrinttoConsole() {
        for (int i = 0; i < data.length; i++) {
            System.out.print("[ ");
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + "\t");
            }
            System.out.println(" ]");
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

    public Matrix multiply(Matrix other) {
    if (this.data[0].length != other.data.length) {
        System.out.println("Матрицы не могут быть умножены: размеры не совпадают.");
        return null;
    }

    int rowsA = this.data.length;         
    int colsA = this.data[0].length;
    int colsB = other.data[0].length;

    Matrix result = new Matrix(rowsA, colsB);

    for (int i = 0; i < rowsA; i++) {
        for (int j = 0; j < colsB; j++) {
            
            Complex sum = new Complex(0.0, 0.0);

            for (int k = 0; k < colsA; k++) {
                Complex c1 = this.data[i][k];
                Complex c2 = other.data[k][j];

                Complex product = c1.multip(c2);

                double realSum = sum.GetReal() + product.GetReal();
                double imagSum = sum.GetImage() + product.GetImage();
                sum = new Complex(realSum, imagSum);
            }
            result.setElement(i, j, sum);
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
    
    public Complex determinant() {
        int n = this.data.length;
        if (n != this.data[0].length) {
            System.out.println("Определитель можно вычислить только для квадратной матрицы!");
            return new Complex(0.0, 0.0);
        }

        // Базовый случай для матрицы 1x1
        if (n == 1) {
            return this.data[0][0];
        }

        // Базовый случай для матрицы 2x2
        if (n == 2) {
            Complex a = this.data[0][0];
            Complex b = this.data[0][1];
            Complex c = this.data[1][0];
            Complex d = this.data[1][1];

            Complex ad = a.multip(d);
            Complex bc = b.multip(c);

            return new Complex(ad.GetReal() - bc.GetReal(), ad.GetImage() - bc.GetImage());
        }

        // Для матриц большего размера (разложение по первой строке)
        Complex det = new Complex(0.0, 0.0);
        for (int p = 0; p < n; p++) {

            Matrix subMatrix = new Matrix(n - 1, n - 1);
            for (int i = 1; i < n; i++) {
                int subCol = 0;
                for (int j = 0; j < n; j++) {
                    if (j == p) continue;
                    subMatrix.data[i - 1][subCol] = this.data[i][j];
                    subCol++;
                }
            }

            Complex sign = (p % 2 == 0) ? new Complex(1.0, 0.0) : new Complex(-1.0, 0.0);
            Complex term = this.data[0][p].multip(sign).multip(subMatrix.determinant());

            double r = det.GetReal() + term.GetReal();
            double im = det.GetImage() + term.GetImage();
            det = new Complex(r, im);
        }
        return det;
    }

    public Matrix inverse() {
        int n = this.data.length;
        Complex det = this.determinant();

        if (Math.abs(det.GetReal()) < 1e-9 && Math.abs(det.GetImage()) < 1e-9) {
            System.out.println("Определитель равен нулю, обратной матрицы не существует!");
            return null;
        }

        if (n == 1) {
            Matrix inv = new Matrix(1, 1);
            inv.data[0][0] = new Complex(1.0, 0.0).div(this.data[0][0]);
            return inv;
        }

        // Создаем матрицу алгебраических дополнений
        Matrix cofactors = new Matrix(n, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                Matrix subMatrix = new Matrix(n - 1, n - 1);
                int subRow = 0;
                for (int row = 0; row < n; row++) {
                    if (row == i) continue;
                    int subCol = 0;
                    for (int col = 0; col < n; col++) {
                        if (col == j) continue;
                        subMatrix.data[subRow][subCol] = this.data[row][col];
                        subCol++;
                    }
                    subRow++;
                }

                Complex subDet = subMatrix.determinant();

                Complex sign = ((i + j) % 2 == 0) ? new Complex(1.0, 0.0) : new Complex(-1.0, 0.0);
                
                cofactors.data[i][j] = sign.multip(subDet);
            }
        }

        Matrix adjugate = cofactors.Transporation();

        Matrix inv = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inv.data[i][j] = adjugate.data[i][j].div(det);
            }
        }
        return inv;
    }

    public Matrix divide(Matrix other) {
        Matrix invOther = other.inverse();
        if (invOther == null) {
            System.out.println("Деление невозможно: не удалось найти обратную матрицу.");
            return null;
        }
        return this.multiply(invOther);
    }

}