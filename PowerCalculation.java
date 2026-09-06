import java.util.Scanner;

public class PowerCalculation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter base: ");
        double base = scanner.nextDouble();

        System.out.print("Enter exponent (e.g. 2, 3, etc.): ");
        double exponent = scanner.nextDouble();
        double result = base;
        double power = base;

        System.out.println("Base: " + base + ", Exponent: " + exponent);
        System.out.println("Base * Base (Square): " + (base * base));

        scanner.close();
    }
}