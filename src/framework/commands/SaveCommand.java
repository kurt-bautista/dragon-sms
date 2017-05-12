package framework.commands;

import framework.SaveStateHandler;
import framework.Session;
import framework.annotations.CommandAnnotation;

/**
 * Created by kurtv on 5/12/17.
 */
@CommandAnnotation(commandName = "SAVE")
public class SaveCommand implements Command {
    @Override
    public String process(String command, Session session) throws Exception {
        SaveStateHandler.getInstance().saveState(session.getGameState());
        return "Saved state.";
    }
}
