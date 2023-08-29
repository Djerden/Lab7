package commands;

import collection.PersonCollection;

public class UserCommand implements  SimpleArgCommand {

    private String result;
    private String login;
    private String password;


    @Override
    public void execute() {

    }

    @Override
    public void setCollection(PersonCollection personCollection) {

    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public void setSimpleArg(String str) {
        String[] strings = str.split("\\s+");
        setLogin(strings[0]);
        setPassword(strings[1]);
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
}
