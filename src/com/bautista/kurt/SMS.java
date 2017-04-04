package com.bautista.kurt;

import framework.annotations.CheckFormat;
import framework.Session;
import framework.commands.Processor;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kurtv on 3/30/17.
 */
public class SMS {

    private Session session;

    ScanResult results = new FastClasspathScanner("RoomCommandManager.room").scan();
    List<String> allResults = results.getNamesOfAllStandardClasses();


    public SMS() {
        this.session = new Session();
    }

    public SMS(Session session) {
        this.session = session;
    }

    public String send(@CheckFormat(regexp = "(?i)^\\s*\\w+\\s*\\w*\\s*$") String message) throws Exception {
        String[] params = message.trim().split("\\s+");
        String returnMessage = "";
        Processor processor = new Processor();
        switch (params.length) {
            case 1:
                String commandString = format(params[0]);
                returnMessage = processor.process(commandString, session);
                break;
            case 2:
                String[] command = format(params[0], params[1]);
                returnMessage = processor.process(command[0] + " " + command[1], session);
                break;
        }
        return returnMessage;
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

}
