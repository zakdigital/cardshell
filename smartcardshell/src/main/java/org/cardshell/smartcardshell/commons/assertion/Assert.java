/******************************************************************************
 * Assert.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.commons.assertion;

import org.cardshell.smartcardshell.commons.NonNull;
import org.cardshell.smartcardshell.commons.Nullable;

/**
 * Collection of assertions providing constans for different purposes with specialized exceptions to be thrown due to
 * invalid assertion arguments.
 *
 * @author Sascha Zak
 * @since 0.1.0
 * @param <T>
 *          type of {@link Throwable} thrown on invalid assertion arguments
 */
public abstract interface Assert<T extends Throwable> {

  /**
   * Object assertion to be used within any method context to check local variables.
   */
  public static final ObjectAssertion OBJ = new ObjectAssertion();

  /**
   * Argument assertion to be used to check method arguments.
   */
  public static final ArgumentAssertion ARG = new ArgumentAssertion();

  /**
   * Number assertion to be used within any method context to check local variables.
   */
  public static final NumberAssertion NUMBER = new NumberAssertion();

  /**
   * State assertions to be used to check expressions representing states within any context.
   */
  public static final StateAssert<IllegalStateException> STATE = new StateAssertion();

  /**
   * Returns a specialized {@link Throwable} and is called, if an assertion failed.
   *
   * @param reason
   *          reason describing why assertion failed
   * @return the {@link Throwable} to indicate invalid assertion arguments
   */
  @NonNull
  public abstract T invalid(@NonNull final String reason);

  /**
   * Asserts that the given subject is not {@code null}.
   *
   * @param subject
   *          subject to assert
   * @return asserted subject
   * @throws T
   *           if the subject is {@code null}
   */
  @NonNull
  public default <S> S isNotNull(@Nullable final S subject) throws T {
    if (subject == null) {
      throw invalid("The given subject is illegally null!");
    }
    return subject;
  }

  /**
   * Assertion implementation providing all checks while throwing {@link IllegalArgumentException}s. This assertion is
   * intended to be used to assert method arguments.
   *
   * @author Sascha Zak
   * @since 0.1.0
   */
  public static final class ArgumentAssertion implements SequenceAssert<IllegalArgumentException>,
  CollectionAssert<IllegalArgumentException>, StateAssert<IllegalArgumentException>,
      NumberAssert<IllegalArgumentException> {

    /**
     * {@inheritDoc}
     *
     * @see org.cardshell.smartcardshell.commons.assertion.Assert#invalid(java.lang.String)
     */
    @Override
    public IllegalArgumentException invalid(@NonNull final String reason) {
      return new IllegalArgumentException(reason);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.cardshell.smartcardshell.commons.assertion.SupplierAssert#invalid(java.lang.String, java.lang.Throwable)
     */
    @Override
    public IllegalArgumentException invalid(@NonNull final String reason, final Throwable cause) {
      return new IllegalArgumentException(reason, cause);
    }
  }

  /**
   * Assertion implementation providing number format or range checks throwing {@link NumberFormatException}s. This
   * assertion is intended to be used to assert local variables.
   *
   * @author Sascha Zak
   * @since 0.1.0
   */
  public static final class NumberAssertion implements NumberAssert<NumberFormatException> {

    /**
     * {@inheritDoc}
     *
     * @see org.cardshell.smartcardshell.commons.assertion.Assert#invalid(java.lang.String)
     */
    @Override
    public NumberFormatException invalid(final String reason) {
      return new NumberFormatException(reason);
    }
  }

  /**
   * Assertion implementation providing {@code null}-checks throwing {@link NullPointerException}s. This assertion is
   * intended to be used to assert local variables.
   *
   * @author Sascha Zak
   * @since 0.1.0
   */
  public static final class ObjectAssertion implements Assert<NullPointerException> {

    /**
     * {@inheritDoc}
     *
     * @see org.cardshell.smartcardshell.commons.assertion.Assert#invalid(java.lang.String)
     */
    @Override
    public NullPointerException invalid(@NonNull final String reason) {
      return new NullPointerException(reason);
    }
  }

  /**
   * Assertion implementation providing expression checks throwing {@link IllegalStateException}s. This assertion is
   * intended to be used to assert expressions representing states in any context.
   *
   * @author Sascha Zak
   * @since 0.1.0
   */
  public static final class StateAssertion implements StateAssert<IllegalStateException> {

    /**
     * {@inheritDoc}
     *
     * @see org.cardshell.smartcardshell.commons.assertion.Assert#invalid(java.lang.String)
     */
    @Override
    public IllegalStateException invalid(final String reason) {
      return new IllegalStateException(reason);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.cardshell.smartcardshell.commons.assertion.SupplierAssert#invalid(java.lang.String, java.lang.Throwable)
     */
    @Override
    public IllegalStateException invalid(@NonNull final String reason, final Throwable cause) {
      return new IllegalStateException(reason, cause);
    }
  }
}
