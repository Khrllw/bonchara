package lab_4;

// класс-обработчик результата авторизации пользователя
public class LoginHandler {
    /*
            объявление шаблона "цепочки ответственности"
            для обработки успешного входа в систему
            и возможных ошибок авторизации пользователя
    */
    private final LoginState chainOfResponsibility;

    LoginHandler() {
        /*
                создание экземпляров классов-обработчиков возможных результатов авторизации
                ( звенья "цепочки ответственности")
         */
        LoginState success = new SuccessfulLogin();
        LoginState uncorrectLogin = new UncorrectLoginError();
        LoginState uncorrectPassword = new UncorrectPasswordError();
        LoginState tooManyTryings = new TooManyAttempsError();
        LoginState accountBlock = new AccountBlockError();

        // определение последовательности обработки
        success.setNext(uncorrectLogin);
        uncorrectLogin.setNext(uncorrectPassword);
        uncorrectPassword.setNext(tooManyTryings);
        tooManyTryings.setNext(accountBlock);

        // объявление начала "цепочки"
        this.chainOfResponsibility = success;
    }

    // функция получения результата авторизации
    public void getState(LoginState loginResult) {
        // запуск "цепочки ответственности"
        chainOfResponsibility.getLoginState(loginResult);
    }
}
