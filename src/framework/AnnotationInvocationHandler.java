package framework;

import solution.Sender;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import framework.validation.Validator;
/**
 * Created by kurtv on 4/5/17.
 */
public class AnnotationInvocationHandler implements InvocationHandler {

    private Sender sender;

    public AnnotationInvocationHandler(Sender sender) {
        this.sender = sender;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        new Validator().validate(proxy, args, Sender.class);
        return method.invoke(sender, args);
    }
}
