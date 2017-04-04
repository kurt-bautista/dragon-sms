package framework.commands;

import framework.Session;

/**
 * Created by kurtv on 4/4/17.
 */
public class RegisterCommand implements Command {

    @Override
    public String process(String command, Session session) {
        session.setName(command.split("\\s+")[1]);
        session.reset();
        return "Registered as " + session.getName() + " successfully.";
    }
}
