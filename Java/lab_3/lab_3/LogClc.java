package lab_3;

public class LogClc extends Thread {

    float value;
    double log;

    LogClc() {
    }

    LogClc(String name) {
        super(name);
    }

    LogClc(String name, float value) {
        super(name);
        this.value = value;
    }

    public double getLog() {
        return this.log;
    }

    @Override
    public void run() {
        System.out.printf("\033[1;31m[%s]\033[0m: started\n", Thread.currentThread().getName());
        try {
            this.log = Math.log(this.value);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.printf("\033[1;31m[ %s has been interrupted ]\033[0m\n", Thread.currentThread().getName());
        }
        System.out.printf("\033[1;31m[%s]\033[0m : finished\n", Thread.currentThread().getName());
    }
}
