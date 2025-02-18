
public class Quote {
    String author;
    String quote;

    void define(String data) {
        // парсинг входных данных (удаление исходных кавычек +
        //                         разделение по знаку "-")
        data = data.replace('\"', ' ').replace('\'', ' ');
        String[] dt_splt = data.split("-");

        // проверка корректности ввода
        if (dt_splt.length < 2 || dt_splt[0].length() == 0 || dt_splt[dt_splt.length - 1].length() == 0) {
            System.out.print("[ Error: Incorrect input ]\n");
            return;
        }

        // очистка переменной
        this.quote = "";

        // создание объекта для удобной работы со строками
        StringBuilder build_quote = new StringBuilder(this.quote);

        // обработка доп.случая: тире - встречается в цитате
        if (dt_splt.length > 2) {
            for (int i = 0; i < dt_splt.length - 2; i++) {
                // склейка строк цитаты + "-"
                build_quote.append(dt_splt[i]).append("-");
            }
            // склейка последней части цитаты +
            // удаление лишних пробелов
            build_quote.append(dt_splt[dt_splt.length - 2].trim());
            this.quote = build_quote.toString();
        } else this.quote = this.quote + dt_splt[0].trim();

        // очистка переменной + запись без лишних пробелов
        this.author = "";
        this.author = dt_splt[dt_splt.length - 1].trim();
    }

    void printQuote() {
        // вывод имени объекта
        System.out.print("< QUOTE >:\n");

        // форматированный вывод цитаты
        System.out.printf("\t\033[1;31m\" %s \"\033[0m\n", this.quote);

        // форматированный вывод автора
        if (quote != null) {
            for (int i = 0; i < this.quote.length(); i++) {
                System.out.print(" ");
            }
            System.out.printf("%s\n", this.author);
        }
    }
}
