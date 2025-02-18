//Данные, общие для всех клиентских сессий
public class Context {
    private final SessionsManager sessionManager;
    public boolean stopFlag;

    public Context() {
        this.stopFlag = false;
        this.sessionManager = new SessionsManager();
    }

    public SessionsManager getSessionsManger() {
        return this.sessionManager;
    }
}