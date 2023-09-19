package commands;

import collection.PersonCollection;
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import command_reader.CommandReader;
import data.User;
import user.Auth;

import java.nio.charset.Charset;

public class UserCommand implements ObjectArgCommand {

    private PersonCollection personCollection;
    private String result;
    private boolean isNewUser;
    private String login;
    private String password;


    @Override
    public void setAuth(Auth auth) {

    }

    @Override
    public void execute() {
        if (isNewUser) {
            result = personCollection.addNewUser(login, password);
        } else {
            result = personCollection.checkUser(login, password);
        }
    }

    @Override
    public void setCollection(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public String getResult() {
        return result;
    }


    public void setSimpleArg(String str) {

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

        this.password = hasher(password);
    }

    public boolean setNewUser() {
        return isNewUser;
    }

    public void setNewUser(boolean newUser) {
        isNewUser = newUser;
    }

    @Override
    public void setNeededObjects(CommandReader reader) {
        User user = reader.readUser();
        setNewUser(user.getNewUser());
        setLogin(user.getLogin());
        setPassword(user.getPassword());
    }

    private String hasher(String password) {
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(password, Charsets.UTF_8);
        HashCode sha256 = hasher.hash();
        return sha256.toString();
    }
}
