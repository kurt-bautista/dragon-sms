package framework.commands;

import framework.Session;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

/**
 * Created by kurtv on 4/4/17.
 */
public class HintCommand implements Command {

    @Override
    public String process(String command, Session session) throws ClassNotFoundException {
        return printHint(session);
    }

    private String printHint(Session session) throws ClassNotFoundException {
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
}
