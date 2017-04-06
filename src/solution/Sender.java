package solution;

import framework.annotations.CheckFormat;

/**
 * Created by kurtv on 4/5/17.
 */
public interface Sender {
    String send(@CheckFormat(regexp = "(?i)^\\s*\\w+\\s*\\w*\\s*$") String message) throws Exception;
}
