package framework.commands;

import framework.Session;
import room.RoomCommandManager;

import java.util.HashMap;

/**
 * Created by kurtv on 4/4/17.
 */
public class RoomCommand implements Command {

    @Override
    public String process(String command, Session session) {
        RoomCommandManager rcm = new RoomCommandManager();
        HashMap<String, Object> ret = rcm.processRoom(session.getCurrentRoom(), session.getGameState(), command);
        session.setGameState((Integer) ret.get("status"));
        return String.valueOf(ret.get("message"));
    }
}
