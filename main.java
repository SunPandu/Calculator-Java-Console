

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 * ==========================================
 * WORLD CLASS PROFESSIONAL CALCULATOR
 * Java 7 Compatible
 * Clean Code + Modular + Stable
 * ==========================================
 */
public final class Main {

    private static final Scanner SCANNER =
	new Scanner(System.in).useLocale(Locale.US);

    private static final String LINE =
	"==================================================";

    public static void main(String[] args) {

        showHeader();

        while (true) {

            double firstNumber  = readNumber("Enter first number  : ");
            Operator operator   = readOperator();
            double secondNumber = readNumber("Enter second number : ");

            printOperation(firstNumber, operator, secondNumber);

            try {
                double result = operator.calculate(firstNumber, secondNumber);
                printResult(result);

            } catch (ArithmeticException e) {
                System.out.println("Error : " + e.getMessage());
            }

            if (!askRepeat()) {
                break;
            }
        }

        shutdown();
    }

    /* ==============================
	 HEADER
	 ============================== */
    private static void showHeader() {

        System.out.println(LINE);
        System.out.println("      PROFESSIONAL CONSOLE CALCULATOR");
        System.out.println("      Designed with Clean Architecture");
        System.out.println(LINE);
        System.out.println("Supported Operators:");
        System.out.println("+  Addition");
        System.out.println("-  Subtraction");
        System.out.println("*  Multiplication");
        System.out.println("/  Division");
        System.out.println("%  Modulus");
        System.out.println("^  Power");
        System.out.println(LINE);
    }

    /* ==============================
	 READ NUMBER SAFELY
	 ============================== */
    private static double readNumber(String message) {

        while (true) {

            try {
                System.out.print(message);
                return SCANNER.nextDouble();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                SCANNER.nextLine();
            }
        }
    }

    /* ==============================
	 READ OPERATOR SAFELY
	 ============================== */
    private static Operator readOperator() {

        while (true) {

            System.out.print("Enter operator       : ");
            String input = SCANNER.next();

            if (input.length() == 1) {

                Operator operator =
					Operator.fromSymbol(input.charAt(0));

                if (operator != null) {
                    return operator;
                }
            }

            System.out.println("Unsupported operator.");
        }
    }

    /* ==============================
	 DISPLAY INPUT
	 ============================== */
    private static void printOperation(
		double a,
		Operator operator,
		double b) {

        System.out.println(LINE);
        System.out.println("Expression : "
						   + format(a) + " "
						   + operator.getSymbol() + " "
						   + format(b));
    }

    /* ==============================
	 DISPLAY RESULT
	 ============================== */
    private static void printResult(double value) {

        System.out.println("Result     : " + format(value));
        System.out.println(LINE);
    }

    /* ==============================
	 ASK USER REPEAT
	 ============================== */
    private static boolean askRepeat() {

        while (true) {

            System.out.print("Calculate again? (Y/N): ");
            String answer = SCANNER.next();

            if (answer.equalsIgnoreCase("Y")) {
                System.out.println();
                return true;
            }

            if (answer.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println("Please enter Y or N.");
        }
    }

    /* ==============================
	 FORMAT NUMBER
	 ============================== */
    private static String format(double value) {

        if (value == (long) value) {
            return String.format("%.0f", value);
        }

        return String.format("%.6f", value);
    }

    /* ==============================
	 CLOSE APP
	 ============================== */
    private static void shutdown() {

        System.out.println("Application terminated successfully.");
        System.out.println("Thank you for using this calculator.");
        SCANNER.close();
    }

    /* ==============================
	 ENUM OPERATOR
	 ============================== */
    private enum Operator {

        ADD('+') {
            public double calculate(double a, double b) {
                return a + b;
            }
        },

        SUBTRACT('-') {
            public double calculate(double a, double b) {
                return a - b;
            }
        },

        MULTIPLY('*') {
            public double calculate(double a, double b) {
                return a * b;
            }
        },

        DIVIDE('/') {
            public double calculate(double a, double b) {

                if (b == 0) {
                    throw new ArithmeticException(
						"Division by zero is not allowed.");
                }

                return a / b;
            }
        },

        MOD('%') {
            public double calculate(double a, double b) {

                if (b == 0) {
                    throw new ArithmeticException(
						"Modulus by zero is not allowed.");
                }

                return a % b;
            }
        },

        POWER('^') {
            public double calculate(double a, double b) {
                return Math.pow(a, b);
            }
        };

        private final char symbol;

        Operator(char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }

        public abstract double calculate(
			double a,
			double b
        );

        public static Operator fromSymbol(char symbol) {

            for (Operator op : values()) {
                if (op.symbol == symbol) {
                    return op;
                }
            }

            return null;
        }
    }
}