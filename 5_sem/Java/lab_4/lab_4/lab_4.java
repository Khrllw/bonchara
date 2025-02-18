package lab_4;

public class lab_4 {

    public static void main(String[] args) {
        // обработчик результатов авторизации
        LoginHandler handler = new LoginHandler();

        // создание экземпляров классов всех возможных результатов попытки авторизации пользователя
        LoginState success = new SuccessfulLogin();
        LoginState uncorrectLogin = new UncorrectLoginError();
        LoginState uncorrectPassword = new UncorrectPasswordError();
        LoginState tooManyAttemps = new TooManyAttempsError();
        LoginState accountBlock = new AccountBlockError();

        // тестированаие
        handler.getState(success);
        handler.getState(uncorrectLogin);
        handler.getState(uncorrectPassword);
        handler.getState(tooManyAttemps);
        handler.getState(accountBlock);
    }
}
