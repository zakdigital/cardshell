/******************************************************************************
 * NonNull.java
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
 * local variable) whose type has this annotation can never have the value {@code null} at runtime.
 * <p>
 * This has two consequences:
 * <ol>
 * <li>Dereferencing the entity is safe, i.e., no {@link java.lang.NullPointerException NullPointerException} can occur
 * at runtime.</li>
 * <li>An attempt to bind a {@code null} value to the entity is a compile time error.</li>
 * </ol>
 * For the second case, diagnostics issued by the compiler should distinguish three situations:
 * <ol>
 * <li>Nullness of the value can be statically determined, the entity is definitely bound from either of:
 * <ul>
 * <li>the value {@code null}, or</li>
 * <li>an entity with a {@link Nullable @Nullable} type.</li>
 * </ul>
 * </li>
 * <li>Nullness cannot definitely be determined, because different code branches yield different results.</li>
 * <li>Nullness cannot be determined, because other program elements are involved for which {@code null} annotations are
 * lacking.</li>
 * </ol>
 * </p>
 * <p>
 * In case of using an IDE, the IDE is resposible to perform these diagnostics, e.g. <a
 * href="http://eclipse.org">Eclipse</a> provides settings under:<br/>
 * <b>Preferences-&gt;Java-&gt;Compiler-&gt;Errors/Warnings</b> in section <b>Null analysis</b>.
 * </p>
 *
 * @author Sascha Zak (SZA)
 * @since 0.1.0
 */
@Target(value = { LOCAL_VARIABLE, METHOD, PARAMETER })
@Retention(RetentionPolicy.CLASS)
@Documented()
public @interface NonNull {
  // marker annotation with no members
}
