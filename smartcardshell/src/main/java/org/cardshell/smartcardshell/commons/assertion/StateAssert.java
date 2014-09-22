/******************************************************************************
 * StateAssert.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.commons.assertion;

import java.util.function.Supplier;

import org.cardshell.smartcardshell.commons.NonNull;

/**
 * Assertion responsible to assert states, meaning asserting boolean expressions to {@code true} or {@code false} .
 *
 * @author Sascha Zak
 * @since 0.1.0
 * @param <T>
 *          type of {@link Throwable} for invalid assertion arguments
 */
public interface StateAssert<T extends Throwable> extends SupplierAssert<T> {

  /**
   * Asserts that the given expression is {@code false}.
   *
   * @param expression
   *          expression to assert
   * @throws T
   *           if the given expression is illegally {@code true}
   */
  @NonNull
  public default void isFalse(final boolean expression) throws T {
    isFalse(expression, "The given expression is illegally true.");
  }

  /**
   * Asserts that the given expression is {@code false}.
   *
   * @param expression
   *          expression to assert
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
   * Asserts that the boolean supplied by the given {@link Supplier} is {@code false} .
   *
   * @param supplier
   *          supplier to assert
   * @throws T
   *           if the boolean supplied is illegally {@code true} or throws itself an {@link Exception}
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
   * Asserts that the given expression is {@true}.
   *
   * @param expression
   *          expression to assert
   * @throws T
   *           if the given expression is {@false}
   */
  @NonNull
  public default void isTrue(final boolean expression) throws T {
    isTrue(expression, "The given expression is false.");
  }

  /**
   * Asserts that the given expession is {@code true}.
   *
   * @param expression
   *          expression to assert
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
   * Asserts that the boolean supplied by the given {@link Supplier} is {@code true}.
   *
   * @param supplier
   *          supplier to assert
   * @throws T
   *           if the boolean supplied is {@false} or throws itself an {@link Exception}
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
