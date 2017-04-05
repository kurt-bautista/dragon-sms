package validation;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;

import framework.annotations.ValidationAnnotation;
import framework.validation.ValidationHandler;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

public class Validator
{

    private HashMap<Class, ValidationHandler> map = new HashMap<>();


    public Validator() throws Exception
    {
        // init map by scanning
        ScanResult results = new FastClasspathScanner("validation").scan();
        List<String> allResults = results.getNamesOfClassesWithAnnotation(ValidationAnnotation.class);
        for (String s : allResults)
        {
            Class c = Class.forName(s);
            ValidationAnnotation va = (ValidationAnnotation) c.getAnnotation(ValidationAnnotation.class);
            System.out.println(va);
            map.put(va.target(), (ValidationHandler) c.newInstance());
        }

    }

    public Validator(String path) throws Exception
    {
        // init map by scanning
        ScanResult results = new FastClasspathScanner("validation", path).scan();
        List<String> allResults = results.getNamesOfClassesWithAnnotation(ValidationAnnotation.class);
        for (String s : allResults)
        {
            Class c = Class.forName(s);
            ValidationAnnotation va = (ValidationAnnotation) c.getAnnotation(ValidationAnnotation.class);
            System.out.println(va);
            map.put(va.target(), (ValidationHandler) c.newInstance());

        }

    }


    public void validate(Object o,Object[] inv, Class clazz)
    {
        for (Method m: clazz.getDeclaredMethods())
        {
            for(Parameter p : m.getParameters()){

                Annotation[] alist = p.getAnnotations();

                for (Annotation a : alist)
                {
                    ValidationHandler vh = map.get(a.annotationType());
                    if (vh!=null)
                    {
                        vh.process(o, p, inv);
                    }
                }
            }
        }
    }
}
