package solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by kurtv on 5/12/17.
 */
public class ReadFile implements Reader {

    private Scanner sc;

    public ReadFile(File file) throws FileNotFoundException {
        this.sc = new Scanner(file);
    }

    public boolean hasNext() {
        return sc.hasNext();
    }

    @Override
    public String read() throws FileNotFoundException {
        return sc.nextLine();
    }
}
