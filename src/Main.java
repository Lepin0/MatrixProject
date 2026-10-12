
public class Main {
    public static void main(String[] args) {
        Complex c1 = new Complex(1.0, 0.0);
        Complex c2 = new Complex(3.0, -2.0);

        Matrix m1 = new Matrix(1,1);
        Matrix m2 = new Matrix(1,1);
        m1.setElement(0, 0, c1);
        m2.setElement(0, 0, c2);
        System.out.println("Первая матрица: ");
        m1.PrinttoConsole();
        System.out.println();
        System.out.println("Вторая матрица: ");
        m2.PrinttoConsole();
        System.out.println();
        Matrix result = m1.add(m2);
        System.out.println("Результат сложения: ");
        result.PrinttoConsole();
    }
}
