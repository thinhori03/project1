package util;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataParam {

    /**
     * name of parameter
    */
    String name();
}
