package lab_4;

// Класс-обработчик блокировки аккаунта пользователя
// из-за большого количества неуспешных попыток входа в аккаунт
public class AccountBlockError extends LoginState {
    @Override
    public void getLoginState(LoginState authProvider) {
        if (authProvider instanceof AccountBlockError) {
            this.stateHandler();
        } else if (this.nextLoginState != null) {
            this.nextLoginState.getLoginState(authProvider);
        }
    }

    @Override
    public void stateHandler() {
        this.showMessage();
        this.showActionsLog();
    }

    @Override
    public void showMessage() {
        System.out.println("\033[1;34mMSG:\033[0m Your account was blocked. Try again later.");
    }

    @Override
    public void showActionsLog() {
        System.out.println("\033[1;31m[Error: AccountBlockError]\033[0m");
        System.out.println("\tAccount has been blocked for 12 hours\n" +
                "\tNotification letter has been sent to the user's email");
    }
}
