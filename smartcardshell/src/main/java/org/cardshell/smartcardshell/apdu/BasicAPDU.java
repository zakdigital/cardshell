/******************************************************************************
 * BasicAPDU.java
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

/**
 * Basic implementation of a {@link CommandAPDU} that handles all general specified response codes.
 *
 * @author Sascha Zak
 * @since 0.1.0
 */
public abstract class BasicAPDU extends CommandAPDU implements ResponseInterpreter {

  /**
   * Creates a new mutable command APDU and initializes it with the given buffer. The internal buffer's length is set to
   * the length of the buffer passed.
   *
   * @param buffer
   *          the byte array to be used for holding the APDU
   */
  public BasicAPDU(final byte[] buffer) {
    super(buffer);
  }

  /**
   * Creates a new mutable command APDU and initializes it with the given buffer. The buffer is assumed to hold an APDU.
   * The (logical) length of the internally buffered APDU is set to length.
   *
   * @param buffer
   *          the byte array to be used for holding the APDU
   * @param length
   *          the (logical) length of the APDU currently in the buffer
   * @throws IndexOutOfBoundsException
   *           if the given length exceeds the size of the buffer
   */
  public BasicAPDU(final byte[] buffer, final int length) throws IndexOutOfBoundsException {
    super(buffer, length);
  }

  /**
   * Creates a new mutable command APDU with a given buffer size. A new buffer with the given size is allocated. The
   * length of the internally buffered APDU is set to 0.
   *
   * @param size
   *          the size of the buffer to create
   * @see #getLength
   */
  public BasicAPDU(final int size) {
    super(size);
  }

  /**
   * {@inheritDoc}
   *
   * @see org.cardshell.smartcardshell.apdu.ResponseInterpreter#interprete(org.cardshell.smartcardshell.ResponseAPDU)
   */
  @Override
  public String interprete(@NonNull final ResponseAPDU response) throws TransmissionException {
    final String sw1 = Hex.toHexString(response.getSW1());
    final String sw2 = Hex.toHexString(response.getSW2());

    final String warning;
    final String error;

    switch (sw1) {
      case "90":
        return sw1 + sw2;
      case "61":
        return sw1 + sw2;
      case "62":
        warning = interprete62(sw2);
        error = null;
        break;
      case "63":
        warning = interprete63(sw2);
        error = null;
        break;
      case "65":
        warning = null;
        error = interprete65(sw2);
        break;
      case "67":
        warning = null;
        error = interprete67(sw2);
        break;
      case "68":
        warning = null;
        error = interprete68(sw2);
        break;
      case "69":
        warning = null;
        error = interprete69(sw2);
        break;
      case "6A":
        warning = null;
        error = interprete6A(sw2);
        break;
      case "6B":
        warning = null;
        error = interprete6B(sw2);
        break;
      case "6C":
        warning = null;
        error = interprete6C(sw2);
        break;
      case "6D":
        warning = null;
        error = interprete6D(sw2);
        break;
      case "6E":
        warning = null;
        error = interprete6E(sw2);
        break;
      case "6F":
        warning = null;
        error = interprete6F(sw2);
        break;
      default:
        warning = null;
        error = null;
    }
    if (warning != null) {
      return warning;
    }
    if (error != null) {
      throw new TransmissionException(error, response);
    }
    return interpreteSpecific(response);
  }

  /**
   * Interpretion of the given {@link ResponseAPDU} to do by any subclass.
   *
   * @param response
   *          {@link ResponseAPDU} to interprete
   * @return warning message or {@code null}, if the result was ok
   * @throws TransmissionException
   *           if the given {@link ResponseAPDU} represents an error
   */
  protected abstract String interpreteSpecific(@NonNull final ResponseAPDU response) throws TransmissionException;

  private String interprete62(final String sw2) {
    switch (sw2) {
      case "00":
        return "No information given";
      case "81":
        return "Part of returned data may be corrupted";
      case "82":
        return "End of file/record reached before reading Le bytes";
      case "83":
        return "Selected file invalidated";
      case "84":
        return "FCI not formatted according to 1.1.5";
      default:
        return null;
    }
  }

  private String interprete63(final String sw2) {
    switch (sw2) {
      case "00":
        return "No information given";
      case "81":
        return "File filled up by the last write";
      case "CX":
        return "Counter provided by 'X' (valued from 0 to 15) (exact meaning depending on the command)";
      default:
        return null;
    }
  }

  private String interprete65(final String sw2) {
    switch (sw2) {
      case "00":
        return "No information given";
      case "81":
        return "Memory failure";
      default:
        return null;
    }
  }

  private String interprete67(final String sw2) {
    switch (sw2) {
      case "00":
        return "Wrong length";
      default:
        return null;
    }
  }

  private String interprete68(final String sw2) {
    switch (sw2) {
      case "00":
        return "No information given";
      case "81":
        return "Logical channel not supported";
      case "82":
        return "Secure messaging not supported";
      default:
        return null;
    }
  }

  private String interprete69(final String sw2) {
    switch (sw2) {
      case "00":
        return "No information given";
      case "81":
        return "Command incompatible with file structure";
      case "82":
        return "Security status not satisfied";
      case "83":
        return "Authentication method blocked";
      case "84":
        return "Referenced data invalidated";
      case "85":
        return "Conditions of use not satisfied";
      case "86":
        return "Command not allowed (no current EF)";
      case "87":
        return "Expected SM data objects missing";
      case "88":
        return "SM data objects incorrect";
      default:
        return null;
    }
  }

  private String interprete6A(final String sw2) {
    switch (sw2) {
      case "00":
        return "No information given";
      case "80":
        return "Incorrect parameters in the data field";
      case "81":
        return "Function not supported";
      case "82":
        return "File not found";
      case "83":
        return "Record not found";
      case "84":
        return "Not enough memory space in the file";
      case "85":
        return "Lc inconsistent with TLV structure";
      case "86":
        return "Incorrect parameters P1-P2";
      case "87":
        return "Lc inconsistent with P1-P2";
      case "88":
        return "Referenced data not found";
      default:
        return null;
    }
  }

  private String interprete6B(final String sw2) {
    switch (sw2) {
      case "00":
        return "Wrong parameter(s) P1-P2";
      default:
        return null;
    }
  }

  private String interprete6C(final String sw2) {
    return "Wrong length Le: SW2 indicates the exact length";
  }

  private String interprete6D(final String sw2) {
    switch (sw2) {
      case "00":
        return "Instruction code not supported or invalid";
      default:
        return null;
    }
  }

  private String interprete6E(final String sw2) {
    switch (sw2) {
      case "00":
        return "Class not supported";
      default:
        return null;
    }
  }

  private String interprete6F(final String sw2) {
    switch (sw2) {
      case "00":
        return "No precise diagnosis";
      default:
        return null;
    }
  }
}
