package lab_4;

public class UncorrectLoginError extends LoginState {
    @Override
    public void getLoginState(LoginState authProvider) {
        if (authProvider instanceof UncorrectLoginError) {
            this.stateHandler();
        } else if (this.nextLoginState != null) {
            this.nextLoginState.getLoginState(authProvider);
        }
    }

    @Override
    public void showMessage() {
        System.out.println("\033[1;34mMSG:\033[0m Non-existent login has been entered. Try again.");
    }

    @Override
    public void showActionsLog() {
        System.out.println("\033[1;31m[Error: UncorrectLoginError]\033[0m");
        System.out.println("\tPop-up window has been created");
    }
}
