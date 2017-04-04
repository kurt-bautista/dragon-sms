package framework.commands;

import framework.Session;

/**
 * Created by kurtv on 4/4/17.
 */
public class GoCommand implements Command {

    @Override
    public String process(String command, Session session) {
        if (command.split("\\s+")[1].matches("(?i)(room[1-5]{1})")) {
            session.setCurrentRoom(command.split("\\s+")[1]);
            return "You are now in " + session.getCurrentRoom() + ".";
        }
        return "Room does not exist.";
    }
}
