package framework.commands;

import framework.Session;

/**
 * Created by kurtv on 4/4/17.
 */
public class StartCommand implements Command {

    @Override
    public String process(String command, Session session) {
        session.reset();
        return "Your session was reset.";
    }
}
