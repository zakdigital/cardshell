/******************************************************************************
 * ResponseInterpreter.java
 *
 * Author: Sascha Zak
 * Date  : 09.09.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.apdu;

import org.cardshell.smartcardshell.ResponseAPDU;
import org.cardshell.smartcardshell.commons.NonNull;
import org.cardshell.smartcardshell.commons.Nullable;

/**
 * A {@link ResponseInterpreter} is able to interprete {@link ResponseAPDU}s.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public interface ResponseInterpreter {

  /**
   * Interpretes the given {@link ResponseAPDU} and decides whether to throw an exception.
   *
   * @param response
   *          response to interprete
   * @return warning message or {@code null}, if the response indicates a successful operation
   * @throws TransmissionException
   *           if the response indicates an error
   */
  @Nullable
  public String interprete(@NonNull ResponseAPDU response) throws TransmissionException;
}
