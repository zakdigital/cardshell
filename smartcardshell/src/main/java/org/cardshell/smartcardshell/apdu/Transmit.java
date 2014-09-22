/******************************************************************************
 * SelectFile.java
 *
 * Author: Sascha Zak
 * Date  : 09.09.2014
 *
 * Copyright © 2014 zak digital
 * http://www.cardshell.org
 *
 *****************************************************************************/
package org.cardshell.smartcardshell.apdu;

import org.cardshell.smartcardshell.CommandAPDU;
import org.cardshell.smartcardshell.ResponseAPDU;
import org.cardshell.smartcardshell.commons.Hex;
import org.cardshell.smartcardshell.commons.NonNull;
import org.cardshell.smartcardshell.commons.Nullable;

/**
 * Implementation of a {@link CommandAPDU} for transmission of any given APDU.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public final class Transmit extends BasicAPDU {

  /**
   * Creates a new instance with the given APDU as hexadecimal encoded {@link String}.
   *
   * @param apdu
   *          hexadecimal encoded {@link String}
   */
  public Transmit(@NonNull final String apdu) {
    super(Hex.parseHexString(apdu));
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.apdu.ResponseInterpreter#interprete(org.cardshell.smartcardshell.ResponseAPDU)
   */
  @Nullable
  @Override
  public String interpreteSpecific(@NonNull final ResponseAPDU response) throws TransmissionException {
    throw new TransmissionException("Unknown response code", response);
  }
}
