
public class Main {
    public static void main(String[] args) {
        // Создаем прямоугольную матрицу 2 на 3
        Matrix m1 = new Matrix(2, 3);
        
        // Заполняем ее тестовыми комплексными числами
        m1.setElement(0, 0, new Complex(1.0, 0.0));
        m1.setElement(0, 1, new Complex(2.0, 1.0));
        m1.setElement(0, 2, new Complex(3.0, -1.0));
        
        m1.setElement(1, 0, new Complex(4.0, 2.0));
        m1.setElement(1, 1, new Complex(5.0, 0.0));
        m1.setElement(1, 2, new Complex(6.0, -3.0));

        System.out.println("Исходная матрица (2x3):");
        m1.PrinttoConsole();
        System.out.println();

        // Транспонируем матрицу
        Matrix transposed = m1.Transporation();

        System.out.println("Транспонированная матрица (3x2):");
        transposed.PrinttoConsole();
    }
}