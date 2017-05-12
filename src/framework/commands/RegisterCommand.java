package framework.commands;

import framework.Session;
import framework.annotations.CommandAnnotation;
import room.GameState;

/**
 * Created by kurtv on 4/4/17.
 */
@CommandAnnotation(commandName = "REGISTER")
public class RegisterCommand implements Command {

    @Override
    public String process(String command, Session session) {
        if((GameState.DEAD & session.getGameState()) == GameState.DEAD)
            return "You are dead. Please use the START command to restart your game.";
        String[] parts = command.split("\\s+");
        session.setName(parts[1]);
        session.reset();
        return "Registered as " + session.getName() + " successfully.";
    }
}
