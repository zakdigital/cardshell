/******************************************************************************
 * TransmissionException.java
 *
 * Author: Sascha Zak
 * Date  : 09.09.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.apdu;

import org.cardshell.smartcardshell.APDU;
import org.cardshell.smartcardshell.ResponseAPDU;
import org.cardshell.smartcardshell.commons.Hex;
import org.cardshell.smartcardshell.commons.NonNull;
import org.cardshell.smartcardshell.commons.assertion.Assert;

/**
 * Exception thrown due to {@link APDU}-transmissions with irregular response.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public final class TransmissionException extends RuntimeException {

  /** */
  private static final long serialVersionUID = 6598920453203414361L;

  /** causing {@link ResponseAPDU} */
  private final ResponseAPDU cause;

  /**
   * Constructs a new runtime exception with the specified detail message. The cause is not initialized, and may
   * subsequently be initialized by a call to {@link #initCause}.
   *
   * @param message
   *          the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
   * @param cause
   *          {@link ResponseAPDU} causing this exception
   */
  public TransmissionException(@NonNull final String message, @NonNull final ResponseAPDU cause) {
    super(Assert.ARG.isNotNull(message));
    this.cause = Assert.ARG.isNotNull(cause);
  }

  /**
   * Returns the {@link ResponseAPDU} causing this exception.
   *
   * @return {@link ResponseAPDU} causing this exception
   */
  public final ResponseAPDU getCauseResponse() {
    return cause;
  }

  /**
   * {@inheritDoc}
   *
   * @see java.lang.Throwable#getMessage()
   */
  @Override
  public String getMessage() {
    return Hex.toHexString(cause.getSW1()) + Hex.toHexString(cause.getSW2()) + ": " + super.getMessage();
  }
}
