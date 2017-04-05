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
public class SMS implements Sender {

    private Session session;

    ScanResult results = new FastClasspathScanner("RoomCommandManager.room").scan();
    List<String> allResults = results.getNamesOfAllStandardClasses();


    public SMS() {
        this.session = new Session();
    }

    public SMS(Session session) {
        this.session = session;
    }

    public String send(String message) throws Exception {
        String[] params = message.trim().split("\\s+");
        Processor processor = new Processor();
        return processor.process(format(params), session);
    }

    private String format(String params[]) {
        switch (params.length) {
            case 1:
                return params[0].toUpperCase();
            default:
                return params[0].toUpperCase() + " " + params[1];
        }
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
