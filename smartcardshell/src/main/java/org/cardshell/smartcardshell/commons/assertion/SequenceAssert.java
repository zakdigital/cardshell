/******************************************************************************
 * SequenceAssert.java
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.cardshell.smartcardshell.commons.NonNull;
import org.cardshell.smartcardshell.commons.Nullable;

/**
 * Assertion responsible to assert {@link CharSequence}s and {@link String}s
 *
 * @author Sascha Zak
 * @since 0.1.0
 * @param <T>
 *          type of {@link Throwable} for invalid assertion arguments
 */
public interface SequenceAssert<T extends Throwable> extends SupplierAssert<T> {

  /**
   * Asserts that the given {@link String} isn't blank, meaning it is not {@code null} and not {@link String#isEmpty()}
   * .
   *
   * @param string
   *          {@link String} to assert
   * @return the asserted {@link String}
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
   * Asserts that the supplied {@link String} isn't blank, meaning it is not {@code null} and not
   * {@link String#isEmpty()} .
   *
   * @param supplier
   *          supplies the {@link String} to be asserted
   * @return the asserted {@link String}
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
   * Asserts that the given {@link CharSequence} matches the given pattern of a regular expression. Therefore the
   * {@link CharSequence} is also asserted to be not {@code null}.
   *
   * @param sequence
   *          {@link CharSequence} to assert
   * @param pattern
   *          the pattern to be asserted
   * @return the asserted {@link CharSequence}
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
