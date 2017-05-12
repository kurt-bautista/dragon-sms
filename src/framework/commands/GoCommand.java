package framework.commands;

import framework.Session;
import framework.annotations.CommandAnnotation;
import room.GameState;

/**
 * Created by kurtv on 4/4/17.
 */
@CommandAnnotation(commandName = "GO")
public class GoCommand implements Command {

    @Override
    public String process(String command, Session session) {
        if((GameState.DEAD & session.getGameState()) == GameState.DEAD)
            return "You are dead. Please use the START command to restart your game or LOAD to go back to a previously saved state.";
        String[] parts = command.split("\\s+");
        parts[1] = "Room" + parts[1].substring(4);
        if (parts[1].matches("(?i)(room[1-5]{1})")) {
            session.setCurrentRoom(parts[1]);
            return "You are now in " + session.getCurrentRoom() + ".";
        }
        return "Room does not exist.";
    }
}
