package com.bautista.kurt;

import framework.CheckFormat;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import room.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
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

    public String send(@CheckFormat(regexp = "(?i)^\\s*\\w+\\s*\\w*\\s*$") String message) throws ClassNotFoundException {
        String[] params = message.split("\\s");
        switch (params.length) {
            case 1:
                rcm.processRoom(session.getCurrentRoom(), session.getGameState(), format(params[0]));
                break;
            case 2:
                rcm.processRoom(session.getCurrentRoom(), session.getGameState(), format(params[0], params[1]));
                break;
        }
        return "";
    }

    private String format(String command) {
        String ret = command.toUpperCase();
        return ret;
    }

    private String format(String command, String parameter) throws ClassNotFoundException {
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
                        Class.forName(session.getCurrentRoom()).getMethods()) {
                    methods.put(m.getName().toUpperCase(), m.getName());
                }
                parts[0] = methods.get(parts[0]);
                break;
        }
        return parts[0] + " " + parts[1];
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
