package data;

public class User {
    private int accessRights;
    private String userName;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String passwordHashed;

    public User(int accessRights, String userName, String laseName, String firstName, String patronymic, String passwordHashed) {
        this.accessRights = accessRights;
        this.userName = userName;
        this.lastName = laseName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.passwordHashed = passwordHashed;
    }

    public User( String accessRights, String username, String passwordHashed, String lastname, String firstname, String patronymic) {
        this.accessRights = Integer.parseInt(accessRights);
        this.userName = username;
        this.lastName = lastname;
        this.firstName = firstname;
        this.patronymic = patronymic;
        this.passwordHashed = passwordHashed;
    }

    public int getAccessRights() {
        return accessRights;
    }

    public String getUserName() {
        return userName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPasswordHashed() {
        return passwordHashed;
    }

    public void setAccessRights(int accessRights) {
        this.accessRights = accessRights;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLaseName(String laseName) {
        this.lastName = laseName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setPasswordHashed(String passwordHashed) {
        this.passwordHashed = passwordHashed;
    }
}
