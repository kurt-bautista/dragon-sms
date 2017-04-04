package framework.commands;

import framework.Session;

import java.util.HashMap;

/**
 * Created by kurtv on 4/4/17.
 */
public class Processor {
    private HashMap<String, Command> map = new HashMap<>();

    public Processor() {
        map.put("REGISTER", new RegisterCommand());
        map.put("START", new StartCommand());
        map.put("GO", new GoCommand());
        map.put("HINT", new HintCommand());
        map.put("ROOM", new RoomCommand());
    }

    public String process(String command, Session session) throws Exception {
        String ret = "";
        if (map.containsKey(command.split("\\s+")[0])) ret = map.get(command.split("\\s+")[0]).process(command, session);
        else ret = map.get("ROOM").process(command, session);
        return ret;
    }
}
