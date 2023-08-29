package user;

import java.io.Serializable;
import java.util.Objects;

public class Auth implements Serializable {
    private final String login;
    private final String password;

    public Auth(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public static Auth parse(String s) {
        if (s == null) throw new IllegalArgumentException();
        String[] loginPassword = s.split("\\s+");
        if (loginPassword.length != 2 || loginPassword[0].length() < 2 || loginPassword[0].length() > 20)
            throw new IllegalArgumentException();
        return new Auth(loginPassword[0], loginPassword[1]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auth auth = (Auth) o;
        return login.equals(auth.login) && password.equals(auth.password);
    }
    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "Auth{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
