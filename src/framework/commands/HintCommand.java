package framework.commands;

import framework.Session;
import framework.annotations.CommandAnnotation;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

/**
 * Created by kurtv on 4/4/17.
 */
@CommandAnnotation(commandName = "HINT")
public class HintCommand implements Command {

    @Override
    public String process(String command, Session session) throws ClassNotFoundException {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println("Available commands:");
        for (Method m :
                Class.forName("room." + session.getCurrentRoom()).getMethods()) {
            if (m.getDeclaringClass() == Class.forName("room." + session.getCurrentRoom()))
                pw.println(m.getName());
        }
        pw.print("start\nsave\nload\nquit\n");
        return sw.toString();
    }

}
