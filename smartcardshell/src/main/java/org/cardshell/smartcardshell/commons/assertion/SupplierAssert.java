/******************************************************************************
 * SupplierAssert.java
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
 * Describes an extended assertion component that is able to handle {@link Supplier} for assertion and therefore must
 * create invalidity {@link Throwable}s having a cause if handling the {@link Supplier} fails.
 *
 * @author Sascha Zak
 * @since 0.1.0
 * @param <T>
 *          type of {@link Throwable} thrown on invalid assertion arguments
 */
public interface SupplierAssert<T extends Throwable> extends Assert<T> {

  /**
   * Returns a specialized {@link Throwable} and is called, if assertion failed.
   *
   * @param reason
   *          reason describing why assertion failed
   * @param cause
   *          causing {@link Throwable}
   * @return the {@link Throwable} to indicate invalid assertion arguments
   */
  public abstract T invalid(@NonNull final String reason, @NonNull final Throwable cause);
}
