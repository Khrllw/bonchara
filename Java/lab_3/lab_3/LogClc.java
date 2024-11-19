package lab_3;

// класс-обработчик потока
public class LogClc extends Thread {
    float value;
    double log;

    // конструктор класса
    // задание имени потока и значения для вычисления
    LogClc(String name, float value) {
        super(name);
        this.value = value;
    }

    // получение вычисленного значения
    public double getLog() {
        return this.log;
    }

    // функция вычисления логарифма
    // исполняется при запуске потока
    @Override
    public void run() {
        System.out.printf("\033[1;31m[%s]\033[0m: started\n", Thread.currentThread().getName());

        // блок для обработки прерываний потока
        try {
            this.log = Math.log(this.value);
            Thread.sleep(0);
        } catch (InterruptedException e) {
            System.out.printf("\033[1;31m[ %s has been interrupted ]\033[0m\n", Thread.currentThread().getName());
        }

        System.out.printf("\033[1;31m[%s]\033[0m: finished\n", Thread.currentThread().getName());
    }
}
