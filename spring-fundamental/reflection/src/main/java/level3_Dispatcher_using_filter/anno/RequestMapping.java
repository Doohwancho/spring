package level3_Dispatcher_using_filter.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RequestMapping {
    String uri(); //@RequestMapping(uri = "/user/join") 아 이렇게 쓰이는구나
}
