/******************************************************************************
 * NumberAssert.java
 *
 * Author: Sascha Zak
 * Date  : 09.09.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.commons.assertion;

import org.cardshell.smartcardshell.commons.NonNull;

/**
 * Assertion responsible to assert properties on {@link Number#s}.
 *
 * @author Sascha Zak
 * @since 0.1.0
 * @param <T>
 *          type of {@link Throwable} for invalid assertion arguments
 */
public interface NumberAssert<T extends Throwable> extends Assert<T> {

  /**
   * Asserts that the given {@link Byte}-number is greater than a given {@link Byte}-reference.
   *
   * @param number
   *          number to assert
   * @param reference
   *          reference number
   * @throws T
   *           if the given number is smaller or equal to the given refenrence
   */
  public default void isGreater(@NonNull final Byte number, @NonNull final Byte reference) throws T {
    if (Assert.ARG.isNotNull(number) <= Assert.ARG.isNotNull(reference)) {
      throw invalid("[%s] is not greater than [%s]");
    }
  }

  /**
   * Asserts that the given {@link Double}-number is greater than a given {@link Double}-reference.
   *
   * @param number
   *          number to assert
   * @param reference
   *          reference number
   * @throws T
   *           if the given number is smaller or equal to the given refenrence
   */
  public default void isGreater(@NonNull final Double number, @NonNull final Double reference) throws T {
    if (Assert.ARG.isNotNull(number) <= Assert.ARG.isNotNull(reference)) {
      throw invalid("[%s] is not greater than [%s]");
    }
  }

  /**
   * Asserts that the given {@link Float}-number is greater than a given {@link Float}-reference.
   *
   * @param number
   *          number to assert
   * @param reference
   *          reference number
   * @throws T
   *           if the given number is smaller or equal to the given refenrence
   */
  public default void isGreater(@NonNull final Float number, @NonNull final Float reference) throws T {
    if (Assert.ARG.isNotNull(number) <= Assert.ARG.isNotNull(reference)) {
      throw invalid("[%s] is not greater than [%s]");
    }
  }

  /**
   * Asserts that the given {@link Integer}-number is greater than a given {@link Integer}-reference.
   *
   * @param number
   *          number to assert
   * @param reference
   *          reference number
   * @throws T
   *           if the given number is smaller or equal to the given refenrence
   */
  public default void isGreater(@NonNull final Integer number, @NonNull final Integer reference) throws T {
    if (Assert.ARG.isNotNull(number) <= Assert.ARG.isNotNull(reference)) {
      throw invalid("[%s] is not greater than [%s]");
    }
  }

  /**
   * Asserts that the given {@link Long}-number is greater than a given {@link Long}-reference.
   *
   * @param number
   *          number to assert
   * @param reference
   *          reference number
   * @throws T
   *           if the given number is smaller or equal to the given refenrence
   */
  public default void isGreater(@NonNull final Long number, @NonNull final Long reference) throws T {
    if (Assert.ARG.isNotNull(number) <= Assert.ARG.isNotNull(reference)) {
      throw invalid("[%s] is not greater than [%s]");
    }
  }

  /**
   * Asserts that the given {@link Short}-number is greater than a given {@link Short}-reference.
   *
   * @param number
   *          number to assert
   * @param reference
   *          reference number
   * @throws T
   *           if the given number is smaller or equal to the given refenrence
   */
  public default void isGreater(@NonNull final Short number, @NonNull final Short reference) throws T {
    if (Assert.ARG.isNotNull(number) <= Assert.ARG.isNotNull(reference)) {
      throw invalid("[%s] is not greater than [%s]");
    }
  }

  /**
   * Asserts that the given {@link Byte}-number is smaller than a given {@link Byte}-reference.
   *
   * @param number
   *          number to assert
   * @param reference
   *          reference number
   * @throws T
   *           if the given number is greater or equal to the given refenrence
   */
  public default void isSmaller(@NonNull final Byte number, @NonNull final Byte reference) throws T {
    if (Assert.ARG.isNotNull(number) <= Assert.ARG.isNotNull(reference)) {
      throw invalid("[%s] is not greater than [%s]");
    }
  }

  /**
   * Asserts that the given {@link Double}-number is smaller than a given {@link Double}-reference.
   *
   * @param number
   *          number to assert
   * @param reference
   *          reference number
   * @throws T
   *           if the given number is greater or equal to the given refenrence
   */
  public default void isSmaller(@NonNull final Double number, @NonNull final Double reference) throws T {
    if (Assert.ARG.isNotNull(number) <= Assert.ARG.isNotNull(reference)) {
      throw invalid("[%s] is not greater than [%s]");
    }
  }

  /**
   * Asserts that the given {@link Float}-number is smaller than a given {@link Float}-reference.
   *
   * @param number
   *          number to assert
   * @param reference
   *          reference number
   * @throws T
   *           if the given number is greater or equal to the given refenrence
   */
  public default void isSmaller(@NonNull final Float number, @NonNull final Float reference) throws T {
    if (Assert.ARG.isNotNull(number) <= Assert.ARG.isNotNull(reference)) {
      throw invalid("[%s] is not greater than [%s]");
    }
  }

  /**
   * Asserts that the given {@link Integer}-number is smaller than a given {@link Integer}-reference.
   *
   * @param number
   *          number to assert
   * @param reference
   *          reference number
   * @throws T
   *           if the given number is greater or equal to the given refenrence
   */
  public default void isSmaller(@NonNull final Integer number, @NonNull final Integer reference) throws T {
    if (Assert.ARG.isNotNull(number) <= Assert.ARG.isNotNull(reference)) {
      throw invalid("[%s] is not greater than [%s]");
    }
  }

  /**
   * Asserts that the given {@link Long}-number is smaller than a given {@link Long}-reference.
   *
   * @param number
   *          number to assert
   * @param reference
   *          reference number
   * @throws T
   *           if the given number is greater or equal to the given refenrence
   */
  public default void isSmaller(@NonNull final Long number, @NonNull final Long reference) throws T {
    if (Assert.ARG.isNotNull(number) <= Assert.ARG.isNotNull(reference)) {
      throw invalid("[%s] is not greater than [%s]");
    }
  }

  /**
   * Asserts that the given {@link Short}-number is smaller than a given {@link Short}-reference.
   *
   * @param number
   *          number to assert
   * @param reference
   *          reference number
   * @throws T
   *           if the given number is greater or equal to the given refenrence
   */
  public default void isSmaller(@NonNull final Short number, @NonNull final Short reference) throws T {
    if (Assert.ARG.isNotNull(number) <= Assert.ARG.isNotNull(reference)) {
      throw invalid("[%s] is not greater than [%s]");
    }
  }
}
