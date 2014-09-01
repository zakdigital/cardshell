/******************************************************************************
 * SupplierValidate.java
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
 * Describes an extended validation component that is able to handle {@link Supplier} for validation and therefore must
 * create invalidity {@link Throwable}s having a cause if handling the {@link Supplier} fails.
 *
 * @author Sascha Zak
 * @since 0.1.0
 * @param <T>
 *          type of {@link Throwable} thrown on invalid validation arguments
 */
public interface SupplierValidate<T extends Throwable> extends Validate<T> {

  /**
   * Returns a specialized {@link Throwable} and is called, if validation failed.
   *
   * @param reason
   *          reason describing why validation failed
   * @param cause
   *          causing {@link Throwable}
   * @return the {@link Throwable} to indicate invalid validation arguments
   */
  public abstract T invalid(@NonNull final String reason, @NonNull final Throwable cause);
}
