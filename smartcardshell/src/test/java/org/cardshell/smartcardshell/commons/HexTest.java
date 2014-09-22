package org.cardshell.smartcardshell.commons;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class HexTest {

  @Test
  public void testParseHexString() {
    final byte[] expected = new byte[] { (byte) 5, (byte) 10, (byte) 20 };
    final byte[] result = Hex.parseHexString("050A14");
    assertThat(result).isEqualTo(expected);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseHexStringWrongLength() {
    Hex.parseHexString("050");
  }

  @Test
  public void testToHexString() {
    final byte[] data = new byte[] { (byte) 5, (byte) 10, (byte) 20 };
    final String result = Hex.toHexString(data);
    assertThat(result).isEqualTo("050A14");
  }

  @Test
  public void testToHexStringByte() {
    final String result = Hex.toHexString(0xA4);
    assertThat(result).isEqualTo("A4");
  }

  @Test
  public void testToHexStringInt() {
    final String result = Hex.toHexString(255);
    assertThat(result).isEqualTo("FF");
  }
}
