package sample;

/*
        A = Solar Panel Cost (10$per sq ft)
        B = Square Footage ~500
        C = Production cost (constant 1mil per)
        D = Fuel Cost(1.65$ per gallon 3ton 770g/t)
        E = Launch Cost (Constant 20mil)
*/


import java.util.Scanner;


public class Main {

    static double a, b, c, d;

    public static void main(String[] args) {
        Main.getA();
    }

    public static void getA() {
            Scanner inputA = new Scanner(System.in);
            System.out.println("How many Solar Panels do you want per sq foot? ");
            a = inputA.nextDouble();
            inputA.close();

            System.out.println("Panels: " + a * 10);

            Main.getB();
        }

        public static void getB() {
            Scanner inputB = new Scanner(System.in);
            System.out.println("Solar Panels cost around 500$ how much would you like them to cost? ");
            b = inputB.nextDouble();
            inputB.close();

            System.out.print("Cost of all Panels " + b * a);

            Main.getC();
        }
        public static void getC() {
            Scanner inputC = new Scanner(System.in);
            System.out.println("Production cost around 1 million, how much would you like it to cost? ");
            c = inputC.nextDouble();
            inputC.close();

            System.out.print("C: " + c + b);

            Main.getD();
        }

    public static void getD() {
        Scanner inputB = new Scanner(System.in);
        System.out.println("Launch Costs at 20 million, plus fuel costs, how much will your satellite hold?  ");
        d = inputB.nextDouble();
        inputB.close();

        System.out.print("D: " + 20000000 + (d * 1.67));

    }

}
