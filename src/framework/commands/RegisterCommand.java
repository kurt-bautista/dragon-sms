package framework.commands;

import framework.Session;
import framework.annotations.CommandAnnotation;

/**
 * Created by kurtv on 4/4/17.
 */
@CommandAnnotation(commandName = "REGISTER")
public class RegisterCommand implements Command {

    @Override
    public String process(String command, Session session) {
        String[] parts = command.split("\\s+");
        session.setName(parts[1]);
        session.reset();
        return "Registered as " + session.getName() + " successfully.";
    }
}
