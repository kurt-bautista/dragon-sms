package solution;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by kurtv on 5/12/17.
 */
public class ReadConsole implements Reader {

    private Scanner sc;

    public ReadConsole() {
        this.sc = new Scanner(System.in);
    }

    @Override
    public String read() throws FileNotFoundException {
        return sc.nextLine();
    }

    @Override
    public boolean hasNext() {
        return true;
    }
}
