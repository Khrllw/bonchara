package lab_1;

import java.util.Scanner;

public class MarketModel {
    // Коэфициенты функций спроса и предложения
    float a, b, c, d;

    // Цены и количество товаров
    float init_price, balance_price, balance_quantity;
    float init_quantity;

    // Точность
    static float accuracy = 0.001F;


    // Расчёт функции предложения
    float clcSQuantity(float price) {
        // Q_s = a - b * P
        return this.a - this.b * price;
    }

    // Расчёт функции для поиска равновесной цены
    float clcBalanceQuantity(float price) {
        // f(p) = a - c + (d + b) * P
        return (this.a - this.c) - (this.d + this.b) * price;
    }

    // Поиск равновесного количества товара
    void searchBalanceQuantity() {
        // Если не была найдена равновесная цена
        if (this.balance_price == 0) {
            searchBalancePrice((this.a - this.c + this.d - this.b), -(this.a - this.c + this.d - this.b));
        }
        this.balance_quantity = clcSQuantity(this.balance_price);
    }

    // Поиск равновесной цены
    void searchBalancePrice(float a, float b) {
        float price = (a + b) / 2;
        // Если выполнено условие точности -> ответ найден
        if (Math.abs(b - a) < accuracy) {
            this.balance_price = price;
            return;
        }
        // Поиск нужного интервала по методу бисекции
        if (clcBalanceQuantity(price) * clcBalanceQuantity(b) <= 0) {
            searchBalancePrice(price, b);
        } else searchBalancePrice(a, price);
    }

    // Форматированный вывод всех значений объекта
    void printValues() {
        System.out.print("\n------------------- \033[1;31mOUT\033[0m -------------------\n");
        System.out.printf("""
                         Your initial price: %.3f
                        \033[1;31m< Balanced price %.3f >\033[0m
                        
                        Your initial quantity: %.3f
                        \033[1;31m< Balanced quantity %.3f >\033[0m
                        """,
                this.init_price, this.balance_price, this.init_quantity, this.balance_quantity);
    }

    // Ввод данных и обработка ошибок
    boolean inputValues() {
        if (inputDemand()) {
            if (inputOffer()) {
                if (inputInitialData()) {
                    return true;
                } else System.out.print("\033[1;31m[ Error: Invalid initial data ]\033[0m");
            } else System.out.print("\033[1;31m[ Error: Invalid offer coefficient ]\033[0m");
        } else System.out.print("\033[1;31m[ Error: Invalid demand coefficient ]\033[0m");
        return false;
    }

    // Ввод функции предложения
    boolean inputDemand() {
        Scanner in = new Scanner(System.in);
        System.out.println("""
                Enter the coefficients for the \033[1;31mdemand function\033[0m:
                \033[1;31mQ_d = < a > - < b > * P\033[0m, where
                \033[1;31ma\033[0m - max possible demand for the product
                \033[1;31mb\033[0m - the coefficient of change in demand
                    when the price changes
                """);
        System.out.print("a = ");
        this.a = in.nextFloat();
        // Обработка ввода некорректных данных
        if (this.a <= 0) {
            in.close();
            return false;
        }
        System.out.print("b = ");
        this.b = in.nextFloat();
        System.out.printf("Your demand func: \033[1;31mQ_d = %.3f - %.3f * P\033[0m\n\n", a, b);
        return true;
    }

    // Ввод функции спроса
    boolean inputOffer() {
        Scanner in = new Scanner(System.in);
        System.out.println("""
                Enter the coefficients for the \033[1;31moffer function:\033[0m
                \033[1;31mQ_s = < c > + < d > * P\033[0m, where
                \033[1;31mc\033[0m - value of the offer at zero price
                \033[1;31md\033[0m - percentage of the change in the volume of supply
                    when the price changes
                """);
        System.out.print("c = ");
        this.c = in.nextFloat();
        // Обработка ввода некорректных данных
        if (this.c <= 0) {
            in.close();
            return false;
        }
        System.out.print("d = ");
        this.d = in.nextFloat();
        System.out.printf("Your offer func: \033[1;31mQ_s = %.3f + %.3f * P\033[0m\n\n", c, d);
        return true;
    }

    // Ввод исходных значений цены и количества товара
    boolean inputInitialData() {
        Scanner in = new Scanner(System.in);
        System.out.print("Input initial price < \033[1;31mP\033[0m > = ");
        this.init_price = in.nextFloat();
        // Обработка ввода некорректных данных
        if (this.init_price <= 0) {
            in.close();
            return false;
        }
        System.out.print("Input quantity of the product < \033[1;31mQ\033[0m > = ");
        this.init_quantity = in.nextInt();
        in.close();
        // Обработка ввода некорректных данных
        return !(this.init_quantity <= 0);
    }
}
