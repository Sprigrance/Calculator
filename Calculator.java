import java.util.Scanner;

public class Calculator {

    static String expression;
    static int a, b;

    public static void main(String[] args) throws MyExceptions {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            expression = scanner.nextLine();
            findResult(expression);
        }
    }

    public static void findResult(String expression) throws MyExceptions {
        FullCheck checking = new FullCheck(expression);
        checking.checkCondition();
        String firstValue = checking.findFirstValue();
        String secondValue = checking.findSecondValue();
        a = checking.checkRange(firstValue);
        b = checking.checkRange(secondValue);
        checking.showResult(a, b);
    }
}
