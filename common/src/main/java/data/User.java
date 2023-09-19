package data;

public class User {
    private String login;
    private String password;
    private boolean newUser;

    public User() {

    }

    public User(String login, String password, boolean newUser) {
        this.login = login;
        this.password = password;
        this.newUser = newUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getNewUser() {
        return newUser;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }
}
