/******************************************************************************
 * Nullable.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.commons;

import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Qualifier for a type in a method signature or a local variable declaration: The entity (return value, parameter,
 * local variable) whose type has this annotation is allowed to have the value {@code null} at runtime.
 * <p>
 * This has two consequences:
 * <ol>
 * <li>Binding a {@code null} value to the entity is legal.</li>
 * <li>Dereferencing the entity is unsafe, i.e., a {@link java.lang.NullPointerException NullPointerException} can occur
 * at runtime.</li>
 * </ol>
 * </p>
 * <p>
 * In case of using an IDE, the IDE is resposible to perform these diagnostics, e.g. <a
 * href="http://eclipse.org">Eclipse</a> provides settings under:<br/>
 * <b>Preferences-&gt;Java-&gt;Compiler-&gt;Errors/Warnings</b> in section <b>Null analysis</b>.
 * </p>
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
@Target(value = { LOCAL_VARIABLE, METHOD, PARAMETER })
@Retention(RetentionPolicy.CLASS)
@Documented()
public @interface Nullable {
  // marker annotation with no members
}
