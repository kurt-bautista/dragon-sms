package com.bautista.kurt;

import framework.CheckFormat;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import room.RoomCommandManager;

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

    public String send(@CheckFormat(regexp = "(?i)^\\s*\\w+\\s*\\w*\\s*$") String message) {
        String[] params = format(message);
        rcm.processRoom(session.getCurrentRoom(), session.getGameState(), params[0]);
        return "";
    }

    private String[] format(String command) {
        String[] ret = command.split("\\s*");
        for (String s :
                ret) {
            s.toUpperCase();
        }
        return ret;
    }

//    ^\s*(go)\s+(room[1-5]{1})\s*$
//    ^\s*(register)\s*\w+\s*$
}
