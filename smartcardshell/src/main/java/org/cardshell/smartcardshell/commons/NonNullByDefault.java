/******************************************************************************
 * NonNullByDefault.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.commons;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Qualifier for package, type, method or constructor in order to define that all contained entities for which a null
 * annotation is otherwise lacking should be considered as {@link NonNull @NonNull}.
 * <dl>
 * <dt>Canceling a default</dt>
 * <dd>By using a {@link @NonNullByDefault @NonNullByDefault} annotation with the argument {@code false}, a default from
 * any enclosing scope can be canceled for the element being annotated.
 * <dt>Nested defaults</dt>
 * <dd>If a {@link @NonNullByDefault @NonNullByDefault} annotation is used within the scope of another {@link
 * @NonNullByDefault @NonNullByDefault} annotation or a project-wide default setting, the innermost annotation defines
 * the default applicable at any given position (depending on the parameter {@link #value()}).</dd>
 * </dl>
 * Note that for applying an annotation to a package, a file by the name {@code package-info.java} is used.
 * <p>
 * In case of using an IDE, the IDE is resposible to perform these diagnostics, e.g. <a
 * href="http://eclipse.org">Eclipse</a> provides settings under:<br/>
 * <b>Preferences-&gt;Java-&gt;Compiler-&gt;Errors/Warnings</b> in section <b>Null analysis</b>.
 * </p>
 *
 * @author Sascha Zak (SZA)
 * @since 0.1.0
 *
 */
@Target(value = { METHOD, PACKAGE, TYPE, CONSTRUCTOR })
@Retention(RetentionPolicy.CLASS)
@Documented()
public @interface NonNullByDefault {

  /**
   * When parameterized with false, the annotation specifies that the current element should not apply any default to
   * un-annotated types.
   *
   * @author Sascha Zak (SZA)
   * @since 18.06.2014
   */
  boolean value() default true;
}
