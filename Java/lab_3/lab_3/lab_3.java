package lab_3;

import java.util.Scanner;

public class lab_3 {
    public static void main(String[] args) throws InterruptedException {
        System.out.print("-------------- \033[1;31mLOG THREADS\033[0m --------------\n");
        System.out.print("Enter values:\n\t");
        // считывание данных
        Scanner in = new Scanner(System.in);
        String[] inp_data = (in.nextLine()).split("\\s*,\\s*");
        in.close();

        // обработка введенных чисел
        double[] result = new double[inp_data.length];
        LogClc new_thread;
        int i = 0;
        //8, 43857, 4357, 3, 2, 0, 743, 3
        do {
            new_thread = new LogClc("LogClc " + inp_data[i], Integer.parseInt(inp_data[i]));
            new_thread.start();
            new_thread.join();
            result[i] = new_thread.log;
            i++;
        } while (i < inp_data.length);
        System.out.println("\033[1;31m[ Calculations finished... ]\033[0m");

        // вывод результата
        System.out.print("\n-------------- \033[1;31mCALCULATED LOGS\033[0m --------------\n");
        System.out.print("{ ");
        for (i = 0; i < inp_data.length - 1; i++) {
          System.out.printf("%s: %f, ", inp_data[i], result[i]);
        }
        System.out.printf("%s: %f", inp_data[inp_data.length - 1], result[inp_data.length - 1]);
        System.out.print(" }");
    }
}
