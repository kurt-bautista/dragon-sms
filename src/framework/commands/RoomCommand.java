package framework.commands;

import framework.Session;
import framework.annotations.CommandAnnotation;
import room.GameState;
import room.RoomCommandManager;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by kurtv on 4/4/17.
 */
@CommandAnnotation
public class RoomCommand implements Command {

    @Override
    public String process(String command, Session session) throws ClassNotFoundException {
        if((GameState.DEAD & session.getGameState()) == GameState.DEAD)
            return "You are dead. Please use the START command to restart your game or LOAD to go back to a previously saved state.";
        RoomCommandManager rcm = new RoomCommandManager();
        HashMap<String, Object> ret;
        String[] parts = command.split("\\s+");
        HashMap<String, String> methods = new HashMap<>();
        for (Method m :
                Class.forName("room." + session.getCurrentRoom()).getMethods()) {
            if (m.getDeclaringClass() == Class.forName("room." + session.getCurrentRoom())) {
                methods.put(m.getName().toUpperCase(), m.getName());
            }
        }
        if (methods.containsKey(parts[0])) {
            if (parts.length == 1)
                ret = rcm.processRoom(session.getCurrentRoom(), session.getGameState(), methods.get(parts[0]));
            else
                ret = rcm.processRoom(session.getCurrentRoom(), session.getGameState(), methods.get(parts[0]) + " " + parts[1]);
            session.setGameState((Integer) ret.get("status"));
            return String.valueOf(ret.get("message"));
        }
        return "Command not found. You may try using the 'hint' command.";
    }
}
