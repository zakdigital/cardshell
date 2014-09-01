/******************************************************************************
 * Validate.java
 *
 * Author: Sascha Zak
 * Date  : 29.08.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.commons;


/**
 * Collection of validators providing constans for different purposes with specialized exceptions to be thrown due to
 * invalid validator arguments.
 *
 * @author Sascha Zak
 * @since 0.1.0
 * @param <T>
 *          type of {@link Throwable} thrown on invalid validation arguments
 */
public abstract interface Validate<T extends Throwable> {

  /**
   * Object validator to be used within any method context to validat local variables.
   */
  public static final ObjectValidator OBJECT = new ObjectValidator();

  /**
   * Argument validator to be used to validate method arguments.
   */
  public static final ArgumentValidator ARG = new ArgumentValidator();

  /**
   * State validator to be used to validate expressions representing states within any context.
   */
  public static final StateValidate<IllegalStateException> STATE = new StateValidator();

  /**
   * Returns a specialized {@link Throwable} and is called, if validation failed.
   *
   * @param reason
   *          reason describing why validation failed
   * @return the {@link Throwable} to indicate invalid validation arguments
   */
  @NonNull
  public abstract T invalid(@NonNull final String reason);

  /**
   * Validates that the given subject is not {@code null}.
   *
   * @param subject
   *          subject to validate
   * @return validated subject
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
   * Validator implementation providing all checks throwing {@link IllegalArgumentException}s. This validator is
   * intended to be used to validate method arguments.
   *
   * @author Sascha Zak
   * @since 0.1.0
   */
  public static final class ArgumentValidator implements SequenceValidate<IllegalArgumentException>,
  CollectionValidate<IllegalArgumentException>, StateValidate<IllegalArgumentException> {

    /**
     * {@inheritDoc}
     *
     * @see de.adesso.webcard.commons.validate.Validate#invalid(java.lang.String)
     */
    @Override
    public IllegalArgumentException invalid(@NonNull final String reason) {
      return new IllegalArgumentException(reason);
    }

    /**
     * {@inheritDoc}
     *
     * @see de.adesso.webcard.commons.validate.SupplierValidate#invalid(java.lang.String, java.lang.Throwable)
     */
    @Override
    public IllegalArgumentException invalid(@NonNull final String reason, final Throwable cause) {
      return new IllegalArgumentException(reason, cause);
    }

  }

  /**
   * Validator implementation providing {@code null} checks throwing {@link NullPointerException}s. This validator is
   * intended to be used to validate local variables.
   *
   * @author Sascha Zak
   * @since 0.1.0
   */
  public static final class ObjectValidator implements Validate<NullPointerException> {

    /**
     * {@inheritDoc}
     *
     * @see de.adesso.webcard.commons.validate.Validate#invalid(java.lang.String)
     */
    @Override
    public NullPointerException invalid(@NonNull final String reason) {
      return new NullPointerException(reason);
    }
  }

  /**
   * Validator implementation providing expression checks throwing {@link IllegalStateException}s. This validator is
   * intended to be used to validate expressions representing states in any context.
   *
   * @author Sascha Zak
   * @since 0.1.0
   */
  public static final class StateValidator implements StateValidate<IllegalStateException> {

    /**
     * {@inheritDoc}
     *
     * @see de.adesso.webcard.commons.validate.Validate#invalid(java.lang.String)
     */
    @Override
    public IllegalStateException invalid(final String reason) {
      return new IllegalStateException(reason);
    }

    /**
     * {@inheritDoc}
     *
     * @see de.adesso.webcard.commons.validate.SupplierValidate#invalid(java.lang.String, java.lang.Throwable)
     */
    @Override
    public IllegalStateException invalid(@NonNull final String reason, final Throwable cause) {
      return new IllegalStateException(reason, cause);
    }
  }
}
