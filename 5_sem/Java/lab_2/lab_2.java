import java.util.Scanner;
public class lab_2 {
    public static void main(String[] args) {
        // создание объекта класса
        Quote data = new Quote();

        // задание значений объекта
        data.define("Эйнштейн однажды сказал: «Привыше всего я боюсь стати». - Альберт Эйнштейн");

        // форматированный вывод объекта
        data.printQuote();

        // переопределеение значений объекта
        data.define("Недостаточно просто знать, нужно использовать знания. " +
                "Мало хотеть чего-то, нужно делать. - Леонардо Да Винчи");
        data.printQuote();

        // ввод данных с консоли +
        // создание нового объекта с введенными данными
        Scanner in = new Scanner(System.in);
        String inp_data = in.nextLine();
        Quote data_2 = new Quote();
        data_2.define(inp_data);
        data_2.printQuote();
        in.close();
    }
}
