/******************************************************************************
 * StateValidate.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.commons;

import java.util.function.Supplier;

/**
 * Validator responsible to validate states, meaning validation boolean expressions to {@code true} or {@code false} .
 *
 * @author Sascha Zak
 * @since 0.1.0
 * @param <T>
 *          type of {@link Throwable} for invalid arguments
 */
public interface StateValidate<T extends Throwable> extends SupplierValidate<T> {

  /**
   * Validates that the given expression is {@code false}.
   *
   * @param expression
   *          expression to validate
   * @throws T
   *           if the given expression is illegally {@code true}
   */
  @NonNull
  public default void isFalse(final boolean expression) throws T {
    isFalse(expression, "The given expression is illegally true.");
  }

  /**
   * Validates that the given expression is {@code false}.
   *
   * @param expression
   *          expression to validate
   * @param reason
   *          reason to be delegated to the thrown {@link Throwable} if the expression is illegally {@code true}.
   * @throws T
   *           if the expression is illegally {@code true}
   */
  @NonNull
  public default void isFalse(final boolean expression, final String reason) throws T {
    if (expression) {
      throw invalid(reason);
    }
  }

  /**
   * Validates that the boolean supplied by the given {@link Supplier} is {@code false} .
   *
   * @param supplier
   *          supplier to validate
   * @throws T
   *           if the boolean supplied is illegally {@code true} or throws itself and {@link Exception}
   */
  @NonNull
  public default void isFalse(final Supplier<Boolean> supplier) throws T {
    final Boolean expression;
    try {
      expression = supplier.get();
    } catch (final Exception any) {
      throw invalid("The given supplier is invalid.", any);
    }
    isFalse(expression);
  }

  /**
   * Validates that the given expression is {@true}.
   *
   * @param expression
   *          expression to validate
   * @throws T
   *           if the given expression is {@false}
   */
  @NonNull
  public default void isTrue(final boolean expression) throws T {
    isTrue(expression, "The given expression is false.");
  }

  /**
   * Validates that the given expession is {@code true}.
   *
   * @param expression
   *          expression to validate
   * @param reason
   *          reason to be delegated to the thrown {@link Throwable} if the expression is {@code false}
   * @throws T
   *           if the given expression is {@code false}
   */
  @NonNull
  public default void isTrue(final boolean expression, final String reason) throws T {
    if (!expression) {
      throw invalid(reason);
    }
  }

  /**
   * Validates that the boolean supplied by the given {@link Supplier} is {@code true}.
   *
   * @param supplier
   *          supplier to validate
   * @throws T
   *           if the boolean supplied is {@false} or throws itself and {@link Exception}
   */
  @NonNull
  public default void isTrue(final Supplier<Boolean> supplier) throws T {
    final Boolean expression;
    try {
      expression = supplier.get();
    } catch (final Exception any) {
      throw invalid("The given supplier is invalid.", any);
    }
    isTrue(expression);
  }
}
