package lab_4;

// Класс-обработчик пользовательского ввода некорректного пароля
public class UncorrectPasswordError extends LoginState {

    @Override
    public void getLoginState(LoginState authProvider) {
        if (authProvider instanceof UncorrectPasswordError) {
            this.stateHandler();
        } else if (this.nextLoginState != null) {
            this.nextLoginState.getLoginState(authProvider);
        }
    }

    @Override
    public void showMessage() {
        System.out.println("\033[1;34mMSG:\033[0m Your password is uncorrect. Try again.");
    }

    @Override
    public void showActionsLog() {
        System.out.println("\033[1;31m[Error: UncorrectPasswordError]\033[0m");
        System.out.println("\tPop-up window has been created");
    }
}
