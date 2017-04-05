package framework.validation;

import java.lang.reflect.Parameter;

/**
 * Created by kurtv on 4/5/17.
 */
public interface ValidationHandler {
    void process(Object o, Parameter p, Object[] args);
}
