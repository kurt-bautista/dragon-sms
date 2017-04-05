package framework.commands;

import framework.Session;
import framework.annotations.CommandAnnotation;

/**
 * Created by kurtv on 4/4/17.
 */
@CommandAnnotation(commandName = "START")
public class StartCommand implements Command {

    @Override
    public String process(String command, Session session) {
        session.reset();
        return "Your session was reset.";
    }
}
