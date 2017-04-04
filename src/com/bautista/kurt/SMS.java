package com.bautista.kurt;

import framework.CheckFormat;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import room.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kurtv on 3/30/17.
 */
public class SMS {

    private RoomCommandManager rcm;
    private Session session;

    ScanResult results = new FastClasspathScanner("RoomCommandManager.room").scan();
    List<String> allResults = results.getNamesOfAllStandardClasses();


    public SMS() {
        this.rcm = new RoomCommandManager();
    }

    public SMS(Session session) {
        this.session = session;
    }

    public String send(@CheckFormat(regexp = "(?i)^\\s*\\w+\\s*\\w*\\s*$") String message) throws ClassNotFoundException {
        String[] params = message.trim().split("\\s+");
        String returnMessage = "";
        switch (params.length) {
            case 1:
                String commandString = format(params[0]);
                switch (commandString) {
                    case "START":
                        session.setCurrentRoom("Room1");
                        session.setGameState(0);
                        returnMessage = "Your session was reset.";
                        break;
                    case "HINT":
                        returnMessage = printHint();
                        break;
                    default:
                        HashMap<String, Object> ret = rcm.processRoom(session.getCurrentRoom(), session.getGameState(), commandString);
                        session.setGameState((Integer) ret.get("status"));
                        returnMessage = String.valueOf(ret.get("message"));
                        break;
                }
                break;
            case 2:
                String[] command = format(params[0], params[1]);
                switch (command[0]) {
                    case "REGISTER":
                        session = new Session();
                        session.setName(command[1]);
                        returnMessage = "Registered as " + session.getName() + " successfully.";
                        break;
                    case "GO":
                        if (command[1].matches("(?i)(room[1-5]{1})")) {
                            session.setCurrentRoom(command[1]);
                            returnMessage = "You are now in " + session.getCurrentRoom();
                        }
                        else returnMessage = "Room does not exist";
                        break;
                    default:
                    HashMap<String, Object> ret = rcm.processRoom(session.getCurrentRoom(), session.getGameState(), command[0] + " " + command[1]);
                    session.setGameState((Integer) ret.get("status"));
                    returnMessage = String.valueOf(ret.get("message"));
                    break;
                }
                break;
        }
        return returnMessage;
    }

    private String printHint() throws ClassNotFoundException {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println("Available commands:");
        for (Method m :
                Class.forName("room." + session.getCurrentRoom()).getMethods()) {
            if (m.getDeclaringClass() == Class.forName("room." + session.getCurrentRoom()))
                pw.println(m.getName());
        }
        return sw.toString();
    }

    private String format(String command) throws ClassNotFoundException {
        String ret = command.toUpperCase();
        HashMap<String, String> methods = new HashMap<>();
        for (Method m :
                Class.forName("room." + session.getCurrentRoom()).getMethods()) {
            if (m.getDeclaringClass() == Class.forName("room." + session.getCurrentRoom()))
                methods.put(m.getName().toUpperCase(), m.getName());
        }
        if (methods.containsKey(ret)) ret = methods.get(ret);
        return ret;
    }

    private String[] format(String command, String parameter) throws ClassNotFoundException {
        String[] parts = {command.toUpperCase(), parameter};
        switch (parts[0]) {
            case "REGISTER":
                break;
            case "GO":
                parts[1] = "Room" + parameter.substring(4);
                break;
            default:
                HashMap<String, String> methods = new HashMap<>();
                for (Method m :
                        Class.forName("room." + session.getCurrentRoom()).getMethods()) {
                    if (m.getDeclaringClass() == Class.forName("room." + session.getCurrentRoom()))
                        methods.put(m.getName().toUpperCase(), m.getName());
                }
                parts[0] = methods.get(parts[0]);
                break;
        }
        return parts;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    //    ^\s*(go)\s+(room[1-5]{1})\s*$
//    ^\s*(register)\s*\w+\s*$
}
