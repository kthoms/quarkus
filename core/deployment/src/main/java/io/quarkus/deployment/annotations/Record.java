package io.quarkus.deployment.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.quarkus.builder.item.BuildItem;

/**
 * Indicates that this {@link BuildStep} method will also output recorded bytecode.
 *
 * If this annotation is present at least one method parameter must be a template object
 * (i.e. a runtime object annotated with {@code @Template}). Any invocations made against
 * this object will be recorded, and written out to bytecode be be invoked at runtime.
 *
 * The {@link #value()} element determines when the generated bytcode is executed. If this
 * is {@link ExecutionTime#STATIC_INIT} then it will be executed from a static init method,
 * so will run at native image generation time.
 *
 * If this is {@link ExecutionTime#RUNTIME_INIT} then it will run from a main method at application
 * start.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Record {

    /**
     * The time to execute the recorded bytecode
     */
    ExecutionTime value();

    /**
     * If this is true then the bytecode produced by this method will be considered to be optional,
     * and will only be created if this build step also produces another {@link BuildItem}
     * that is consumed by the build.
     *
     * If a method is optional it must be capable of producing at least one other item
     *
     */
    boolean optional() default false;

}
