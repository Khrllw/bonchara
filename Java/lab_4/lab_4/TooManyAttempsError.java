package lab_4;

// Класс-обработчик большого количства неуспешных попыток входа в аккаунт
public class TooManyAttempsError extends LoginState {
    @Override
    public void getLoginState(LoginState authProvider) {
        if (authProvider instanceof TooManyAttempsError) {
            this.stateHandler();
        } else if (this.nextLoginState != null) {
            this.nextLoginState.getLoginState(authProvider);
        }
    }

    @Override
    public void showMessage() {
        System.out.println("\033[1;34mMSG:\033[0m Too many attempts. Confirm your email address.");
    }

    @Override
    public void showActionsLog() {
        System.out.println("\033[1;31m[Error: TooManyAttempsError]\033[0m");
        System.out.println("\tConfirm letter has been sent to the user's email");
    }
}
