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

import org.cardshell.smartcardshell.Channel;
import org.cardshell.smartcardshell.CommandAPDU;
import org.cardshell.smartcardshell.File;
import org.cardshell.smartcardshell.ResponseAPDU;
import org.cardshell.smartcardshell.commons.Hex;
import org.cardshell.smartcardshell.commons.NonNull;
import org.cardshell.smartcardshell.commons.Nullable;
import org.cardshell.smartcardshell.commons.assertion.Assert;

/**
 * Implementation of a {@link CommandAPDU} for {@link File} selection.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public class SelectFile extends BasicAPDU {

  public SelectFile(@NonNull final Channel channel, @NonNull final File file) {
    super(7);
    append(channel.getNumber());
    append(Hex.parseHexString("A4"));
    append(Hex.parseHexString("00"));
    append(Hex.parseHexString("00"));
    append(Hex.parseHexString("02"));
    append(file.getId());
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.apdu.ResponseInterpreter#interprete(org.cardshell.smartcardshell.ResponseAPDU)
   */
  @Nullable
  @Override
  public String interpreteSpecific(@NonNull final ResponseAPDU response) throws TransmissionException {
    Assert.ARG.isNotNull(response);
    final String sw1 = Hex.toHexString(response.getSW1());
    final String sw2 = Hex.toHexString(response.getSW2());

    if ("62".equals(sw1)) {
      return getWarningMessage(sw2);
    }
    if ("6A".equals(sw1)) {
      throw new TransmissionException(getErrorMessage(sw2), response);
    }
    throw new TransmissionException("Unknown response code", response);
  }

  private String getErrorMessage(final String sw2) {
    switch (sw2) {
      case "81":
        return "Function not supported";
      case "82":
        return "File not found";
      case "86":
        return "Incorrect parameters P1-P2";
      case "87":
        return "Lc inconsistent with P1-P2";
      default:
        return String.format("Unknown status word 2 [%s]", sw2);
    }
  }

  private String getWarningMessage(final String sw2) {
    switch (sw2) {
      case "83":
        return "Selected file invalidated";
      case "84":
        return "FCI not formatted according to 5.1.5";
      default:
        return String.format("Unknown status word 2 [%s]", sw2);
    }
  }
}
