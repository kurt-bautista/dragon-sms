package solution;

import java.io.FileNotFoundException;

/**
 * Created by kurtv on 5/12/17.
 */
public interface Reader {
    public String read() throws FileNotFoundException;
    public boolean hasNext();
}
