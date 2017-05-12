package framework.commands;

import framework.SaveStateHandler;
import framework.Session;
import framework.annotations.CommandAnnotation;

/**
 * Created by kurtv on 5/12/17.
 */
@CommandAnnotation(commandName = "LOAD")
public class LoadCommand implements Command {
    @Override
    public String process(String command, Session session) throws Exception {
        if(SaveStateHandler.empty()) return "No save states found.";
        session.setGameState(SaveStateHandler.getInstance().loadState());
        return "Saved state loaded. Please use the SAVE command if you wish to save the current state.";
    }
}
