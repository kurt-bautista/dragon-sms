package framework.commands;

import framework.Session;
import framework.annotations.CommandAnnotation;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

import java.util.HashMap;
import java.util.List;

/**
 * Created by kurtv on 4/4/17.
 */
public class Processor {
    private HashMap<String, Command> map = new HashMap<>();

    public Processor() throws Exception {
        ScanResult results = new FastClasspathScanner("framework.commands").scan();
        List<String> allResults = results.getNamesOfClassesWithAnnotation(CommandAnnotation.class);
        for (String s : allResults)
        {
            Class c = Class.forName(s);
            CommandAnnotation commandAnnotation = (CommandAnnotation) c.getAnnotation(CommandAnnotation.class);
            map.put(commandAnnotation.commandName(), (Command) c.newInstance());
        }
    }

    public String process(String command, Session session) throws Exception {
        if (map.containsKey(command.split("\\s+")[0])) return map.get(command.split("\\s+")[0]).process(command, session);
        return map.get("ROOM").process(command, session);
    }
}
