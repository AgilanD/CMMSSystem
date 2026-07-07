package cmms.system.userContext;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface RequireRole {
    String[] value();
}
