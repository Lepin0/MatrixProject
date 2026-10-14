import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Для удобства сохраним две матрицы, с которыми пользователь сможет производить операции
        Matrix m1 = null;
        Matrix m2 = null;

        while (true) {
            System.out.println("\n========================================");
            System.out.println("   МЕНЮ УПРАВЛЕНИЯ КОМПЛЕКСНЫМИ МАТРИЦАМИ");
            System.out.println("========================================");
            System.out.println("1. Создать/ввести матрицу M1");
            System.out.println("2. Создать/ввести матрицу M2");
            System.out.println("3. Вывести матрицу M1 и M2 на консоль");
            System.out.println("4. Сложение матриц (M1 + M2)");
            System.out.println("5. Вычитание матриц (M1 - M2)");
            System.out.println("6. Транспонирование матрицы M1");
            System.out.println("7. Умножение матрицы M1 на комплексное число (скаляр)");
            System.out.println("8. Умножение матриц (M1 * M2)");
            System.out.println("9. Вычислить определитель матрицы M1");
            System.out.println("10. Найти обратную матрицу для M1");
            System.out.println("11. Деление матриц (M1 / M2)");
            System.out.println("0. Выход");
            System.out.print("Выберите пункт меню: ");

            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    m1 = inputMatrix(scanner, "M1");
                    System.out.println("Матрица M1 успешно создана!");
                    break;

                case 2:
                    m2 = inputMatrix(scanner, "M2");
                    System.out.println("Матрица M2 успешно создана!");
                    break;

                case 3:
                    System.out.println("Матрица M1:");
                    if (m1 != null) m1.PrinttoConsole(); else System.out.println("не задана");
                    System.out.println("\nМатрица M2:");
                    if (m2 != null) m2.PrinttoConsole(); else System.out.println("не задана");
                    break;

                case 4:
                    if (checkMatrices(m1, m2)) {
                        Matrix res = m1.add(m2);
                        if (res != null) {
                            System.out.println("Результат сложения (M1 + M2):");
                            res.PrinttoConsole();
                        }
                    }
                    break;

                case 5:
                    if (checkMatrices(m1, m2)) {
                        Matrix res = m1.subtract(m2);
                        if (res != null) {
                            System.out.println("Результат вычитания (M1 - M2):");
                            res.PrinttoConsole();
                        }
                    }
                    break;

                case 6:
                    if (m1 != null) {
                        System.out.println("Результат транспонирования M1:");
                        m1.Transporation().PrinttoConsole();
                    } else {
                        System.out.println("Сначала создайте матрицу M1!");
                    }
                    break;

                case 7:
                    if (m1 != null) {
                        System.out.print("Введите действительную часть скаляра: ");
                        double r = scanner.nextDouble();
                        System.out.print("Введите мнимую часть скаляра: ");
                        double im = scanner.nextDouble();
                        Complex scalar = new Complex(r, im);

                        Matrix res = m1.multip_copmplex(scalar);
                        System.out.println("Результат умножения M1 на скаляр " + scalar + ":");
                        res.PrinttoConsole();
                    } else {
                        System.out.println("Сначала создайте матрицу M1!");
                    }
                    break;

                case 8:
                    if (checkMatrices(m1, m2)) {
                        Matrix res = m1.multiply(m2);
                        if (res != null) {
                            System.out.println("Результат умножения матриц (M1 * M2):");
                            res.PrinttoConsole();
                        }
                    }
                    break;

                case 9:
                    if (m1 != null) {
                        Complex det = m1.determinant();
                        System.out.println("Определитель матрицы M1 = " + det);
                    } else {
                        System.out.println("Сначала создайте матрицу M1!");
                    }
                    break;

                case 10:
                    if (m1 != null) {
                        Matrix inv = m1.inverse();
                        if (inv != null) {
                            System.out.println("Обратная матрица для M1 (M1^(-1)):");
                            inv.PrinttoConsole();
                        }
                    } else {
                        System.out.println("Сначала создайте матрицу M1!");
                    }
                    break;

                case 11:
                    if (checkMatrices(m1, m2)) {
                        Matrix res = m1.divide(m2);
                        if (res != null) {
                            System.out.println("Результат деления матриц (M1 / M2):");
                            res.PrinttoConsole();
                        }
                    }
                    break;

                case 0:
                    System.out.println("Выход из программы. До свидания!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный выбор! Пожалуйста, выберите пункт от 0 до 11.");
            }
        }
    }

    // Вспомогательный метод для ввода матрицы с клавиатуры
    private static Matrix inputMatrix(Scanner scanner, String name) {
        System.out.println("--- Ввод матрицы " + name + " ---");
        System.out.print("Введите количество строк: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов: ");
        int cols = scanner.nextInt();

        Matrix matrix = new Matrix(rows, cols);
        System.out.println("Введите элементы (реальная и мнимая часть через пробел для каждого элемента):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Элемент [" + i + "][" + j + "]: ");
                double r = scanner.nextDouble();
                double im = scanner.nextDouble();
                matrix.setElement(i, j, new Complex(r, im));
            }
        }
        return matrix;
    }

    // Вспомогательная проверка, что обе матрицы заданы
    private static boolean checkMatrices(Matrix m1, Matrix m2) {
        if (m1 == null || m2 == null) {
            System.out.println("Ошибка: Сначала нужно создать обе матрицы (M1 и M2)!");
            return false;
        }
        return true;
    }
}