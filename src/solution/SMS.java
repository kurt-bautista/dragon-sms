package solution;

import framework.Session;
import framework.commands.Processor;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import java.util.List;

/**
 * Created by kurtv on 3/30/17.
 */
public class SMS implements Sender {

    private Session session;
    private static SMS instance;

    ScanResult results = new FastClasspathScanner("RoomCommandManager.room").scan();
    List<String> allResults = results.getNamesOfAllStandardClasses();


    private SMS() {
        this.session = new Session();
    }

//    public SMS(Session session) {
//        this.session = session;
//    }

    public String send(String message) throws Exception {
        String[] params = message.trim().split("\\s+");
        return new Processor().process(format(params), session);
    }

    private String format(String params[]) {
        if (params.length == 1)
            return params[0].toUpperCase();
        return params[0].toUpperCase() + " " + params[1];
    }

    public static synchronized SMS getInstance() {
        if(instance == null) {
            instance = new SMS();
        }
        return instance;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
