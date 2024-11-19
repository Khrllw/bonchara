package lab_4;

// абстрактный класс-результат авторизации пользователя
// (звено "цепочки отвественности")
public abstract class LoginState {
    // переменная, указывающая на следущее звено
    // "цепочки отвественности"
    public LoginState nextLoginState;

    // функция поиска и обработки нужного звена
    // "цепочки отвественности"
    abstract public void getLoginState(LoginState authProvider);

    // задание следующего возможного звена
    // "цепочки отвественности"
    public void setNext(LoginState nextHandler) {
        this.nextLoginState = nextHandler;
    }

    // обработчик результата авторизации
    public void stateHandler() {
        showMessage();
        showActionsLog();
        System.out.print('\n');
    }

    // вывод сообщения для пользователя
    abstract public void showMessage();

    // вывод логов дальнейших действий
    abstract public void showActionsLog();
}
