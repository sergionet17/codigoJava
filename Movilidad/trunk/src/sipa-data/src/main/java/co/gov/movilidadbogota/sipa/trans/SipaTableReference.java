package co.gov.movilidadbogota.sipa.trans;

import java.lang.annotation.*;

/**
 * Anotación para las entidades que son tablas de referencia
 *
 * @author lorenzo.pinango
 */
@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface SipaTableReference {
    String name();

    String repository();
}
