package framework.validation;

import framework.annotations.CheckFormat;
import framework.annotations.ValidationAnnotation;

import java.lang.reflect.Parameter;

/**
 * Created by kurtv on 4/5/17.
 */
@ValidationAnnotation(target = CheckFormat.class)
public class CheckFormatValidator implements ValidationHandler {
    public void process(Object o, Parameter p, Object[] args) {
        CheckFormat cf = p.getAnnotation(CheckFormat.class);
        String userInput = args[0].toString();
        if(!userInput.matches(cf.regexp()))
            throw new RuntimeException("Invalid input format.");
    }
}
