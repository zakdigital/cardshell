/******************************************************************************
 * SequenceValidate.java
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator responsible to validate {@link CharSequence}s and {@link String}s
 *
 * @author Sascha Zak
 * @since 0.1.0
 * @param <T>
 *          type of {@link Throwable} for invalid arguments
 */
public interface SequenceValidate<T extends Throwable> extends SupplierValidate<T> {

  /**
   * Validates that the given {@link String} isn't blank, meaning it is not {@code null} and not
   * {@link String#isEmpty()} .
   *
   * @param string
   *          {@link String} to validate
   * @return the validated {@link String}
   * @throws T
   *           if the given {@link String} is blank
   */
  @NonNull
  public default String isNotBlank(@Nullable final String string) throws T {
    isNotNull(string);
    if (string.trim().isEmpty()) {
      throw invalid(String.format("The string [%s] is blank.", string));
    }
    return string;
  }

  /**
   * Validates that the supplied {@link String} isn't blank, meaning it is not {@code null} and not
   * {@link String#isEmpty()} .
   *
   * @param supplier
   *          supplies the {@link String} to be validated
   * @return the validated {@link String}
   * @throws T
   *           if the given {@link Supplier} is invalid or the supplied {@link String} is blank
   */
  @NonNull
  public default String isNotBlank(@Nullable final Supplier<String> supplier) throws T {
    final String string;
    try {
      string = supplier.get();
    } catch (final Exception any) {
      throw invalid("The given supplier is invalid.", any);
    }
    return isNotBlank(string);
  }

  /**
   * Validates that the given {@link CharSequence} matches the given pattern of a regular expression. Therefore the
   * {@link CharSequence} is also validated to be not {@code null}
   *
   * @param sequence
   *          {@link CharSequence} to validate
   * @param pattern
   *          the pattern to validate againt
   * @return the validate {@link CharSequence}
   * @throws T
   *           if the given {@link CharSequence} doesn't match the given pattern
   */
  @NonNull
  public default <S extends CharSequence> S matches(@Nullable final S sequence, @NonNull final Pattern pattern)
      throws T {
    isNotNull(sequence);
    final Matcher matcher = pattern.matcher(sequence);
    if (matcher.matches() == false) {
      throw invalid(String.format("The character sequence [%s] does not match the pattern [%s].", sequence, pattern));
    }
    return sequence;
  }
}
