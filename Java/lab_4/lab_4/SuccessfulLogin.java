package lab_4;

// Класс-обработчик успешной авторизации пользовател
public class SuccessfulLogin extends LoginState {
    @Override
    public void getLoginState(LoginState authProvider) {
        if (authProvider instanceof SuccessfulLogin) {
            this.stateHandler();
        } else if (this.nextLoginState != null) {
            this.nextLoginState.getLoginState(authProvider);
        }
    }

    @Override
    public void showMessage() {
        System.out.println("\033[1;34mMSG:\033[0m Welcome!");
    }

    @Override
    public void showActionsLog() {
        System.out.println("\033[1;32m[The user <User@name> is logged in]\033[0m");
    }
}
