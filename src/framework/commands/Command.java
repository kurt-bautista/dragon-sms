package framework.commands;

import framework.Session;

/**
 * Created by kurtv on 4/4/17.
 */
public interface Command {
    public String process(String command, Session session) throws Exception;
}
