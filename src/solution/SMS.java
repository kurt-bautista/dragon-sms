package solution;

import framework.AnnotationInvocationHandler;
import framework.Session;
import framework.commands.Processor;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by kurtv on 3/30/17.
 */
public class SMS implements Sender {

    private Session session;
    private static SMS instance;
    private Reader reader;

    ScanResult results = new FastClasspathScanner("RoomCommandManager.room").scan();
    List<String> allResults = results.getNamesOfAllStandardClasses();


    private SMS() {
        this.session = new Session();
        this.reader = new ReadConsole();
    }

//    public SMS(Session session) {
//        this.session = session;
//    }

    public String send(String message) throws Exception {
        if(message.trim().equalsIgnoreCase("quit")) return "quit";
        String[] params = message.trim().split("\\s+");
        return new Processor().process(format(params), session);
    }

    public String read() throws Exception {
        AnnotationInvocationHandler invocationHandler = new AnnotationInvocationHandler(SMS.getInstance());
        Sender proxy = (Sender) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] {Sender.class}, invocationHandler);
        return proxy.send(reader.read());
    }

    private String format(String params[]) {
        if (params.length == 1)
            return params[0].toUpperCase();
        return params[0].toUpperCase() + " " + params[1];
    }

    public static synchronized SMS getInstance() {
        if(instance == null) instance = new SMS();
        return instance;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
