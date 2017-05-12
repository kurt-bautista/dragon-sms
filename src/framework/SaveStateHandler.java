package framework;

import java.util.Stack;

/**
 * Created by kurtv on 5/12/17.
 */
public class SaveStateHandler {

    private static Stack<Integer> states = new Stack<>();
    private static SaveStateHandler saveStateHandler;

    private SaveStateHandler() {

    }

    public static synchronized SaveStateHandler getInstance() {
        if(saveStateHandler == null) saveStateHandler = new SaveStateHandler();
        return saveStateHandler;
    }

    public void saveState(int i) {
        states.push(i);
    }

    public int loadState() {
        return states.pop();
    }

    public static void clearSaves() {
        states.clear();
    }

    public static boolean empty() {
        if(states.size() == 0) return true;
        return false;
    }
}
