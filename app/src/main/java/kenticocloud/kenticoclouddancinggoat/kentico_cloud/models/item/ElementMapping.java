package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Can be placed on fields to indicate that it should be mapped to an element
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementMapping {
    String value();
}
