package co.gov.movilidadbogota.sipa.trans;

import java.lang.annotation.*;

/**
 * Anotación para las entidades que son tablas de referencia
 *
 * @author lorenzo.pinango
 */
@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface SipaFieldTableReference {
    String title();

    String fieldRoute() default "";

    String repository() default "";

    int numberColumn();
}
