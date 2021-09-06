public class FullCheck {

    String expression;
    char[] dividedExpression;
    int indexOfFirstTab = 0;
    int indexOfSecondTab = 0;
    String firstValue;
    String secondValue;
    int result;

    static String[] romanValues = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    int countOfCalculus = 1;
    boolean isRoman;

    public FullCheck(String expression) {
        this.expression = expression;
        dividedExpression = expression.toCharArray();
    }

    public void checkCondition() throws MyExceptions {
        int countOfTab = 0;
        for (char c : dividedExpression) {
            if (c == ' ') {
                countOfTab++;
            }
        }
        if (countOfTab <= 1) {
            throw new MyExceptions("Cтрока не является математической операцией");
        } else if (countOfTab > 2) {
            throw new MyExceptions("Формат математической операции не удовлетворяет заданию");
        }
    }

    public String findFirstValue() throws MyExceptions {
        for (int i = 0; i < dividedExpression.length; i++) {
            if (dividedExpression[i] == ' ') {
                indexOfFirstTab = i;
                if (i == 0) {
                    throw new MyExceptions("Значение A не определено");
                }
                break;
            }
        }
        firstValue = expression.substring(0, indexOfFirstTab);
        return firstValue;
    }

    public String findSecondValue() throws MyExceptions {
        for (int i = indexOfFirstTab + 1; i < dividedExpression.length; i++) {
            if (dividedExpression[i] == ' ') {
                indexOfSecondTab = i;
                if (indexOfSecondTab + 1 == dividedExpression.length) {
                    throw new MyExceptions("Значение B не определено");
                }
                break;
            }
        }
        secondValue = expression.substring(indexOfSecondTab + 1);
        return secondValue;
    }

    public int checkRange(String value) throws MyExceptions {
        int digit = 0;
        try {
            digit = Integer.parseInt(value.trim());
            if (digit < 1 || digit > 10) {
                throw new MyExceptions("Введено значение вне диапазона");
            }
        } catch (NumberFormatException e) {
            for (int i = 0; i < romanValues.length; i++) {
                if (value.equals(romanValues[i])) {
                    countOfCalculus++;
                    isRoman = true;
                    digit = i + 1;
                }
            }
        }
        return digit;
    }

    public void showResult(int a, int b) throws MyExceptions {
        for (char symbol : dividedExpression) {
            if (countOfCalculus == 2) {
                throw new MyExceptions("Используются разные системы счисления одновременно");
            }
            switch (symbol) {
                case '+':
                    result = a + b;
                    if (isRoman) {
                        System.out.println("Результат сложения = " + getRomanResult());
                    } else {
                        System.out.println("Результат сложения = " + result);
                    }
                    break;
                case '-':
                    result = a - b;
                    if (result > 0) {
                        if (isRoman) {
                            System.out.println("Результат вычитания = " + getRomanResult());
                        } else {
                            System.out.println(result);
                        }
                    } else if (result < 1 && !isRoman) {
                        System.out.println("Результат вычитания = " + result);
                    } else if (result < 1 && isRoman) {
                        throw new MyExceptions("В римской системе нет отрицательных чисел");
                    }
                    break;
                case '*':
                    result = a * b;
                    if (isRoman) {
                        System.out.println("Результат умножения = " + getRomanResult());
                    } else {
                        System.out.println("Результат умножения = " + result);
                    }
                    break;
                case '/':
                    result = a / b;
                    if (isRoman) {
                        System.out.println("Результат деления = " + getRomanResult());
                    } else {
                        System.out.println("Результат деления = " + result);
                    }
                    break;
            }
        }
    }

    public String getRomanResult() {
        StringBuilder romanResult = new StringBuilder("");
        while (result / 100 > 0) {
            romanResult.append("C");
            result -= 100;
        }
        while (result / 90 > 0) {
            romanResult.append("XC");
            result -= 90;
        }
        while (result / 50 > 0) {
            romanResult.append("L");
            result -= 50;
        }
        while (result / 40 > 0) {
            romanResult.append("XL");
            result -= 40;
        }
        while (result / 10 > 0) {
            romanResult.append("X");
            result -= 10;
        }
        if (result != 0) {
            for (int i = 0; i < romanValues.length; i++) {
                if (result == i) {
                    romanResult.append(romanValues[i - 1]);
                }
            }
        }
        return romanResult.toString();
    }
}
