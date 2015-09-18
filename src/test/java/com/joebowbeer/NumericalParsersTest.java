package com.joebowbeer;

import static com.joebowbeer.NumericalParsers.parseEnglishNumerals;
import static com.joebowbeer.NumericalParsers.parseRomanNumerals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class NumericalParsersTest {

  @Test
  public void testParseRomanNumerals() {
    assertEquals(1, parseRomanNumerals("I"));
    assertEquals(2, parseRomanNumerals("II"));
    assertEquals(3, parseRomanNumerals("III"));
    assertEquals(4, parseRomanNumerals("IV"));
    assertEquals(5, parseRomanNumerals("V"));
    assertEquals(6, parseRomanNumerals("VI"));
    assertEquals(7, parseRomanNumerals("VII"));
    assertEquals(8, parseRomanNumerals("VIII"));
    assertEquals(9, parseRomanNumerals("IX"));
    assertEquals(10, parseRomanNumerals("X"));
    assertEquals(11, parseRomanNumerals("XI"));
    assertEquals(12, parseRomanNumerals("XII"));
    assertEquals(1492, parseRomanNumerals("MCDXCII"));
    assertEquals(1958, parseRomanNumerals("MCMLVIII"));
    assertEquals(2015, parseRomanNumerals("MMXV"));
  }

  @Test
  public void testParseEnglishNumerals() {
    assertEquals(1, parseEnglishNumerals("one"));
    assertEquals(2, parseEnglishNumerals("two"));
    assertEquals(3, parseEnglishNumerals("three"));
    assertEquals(4, parseEnglishNumerals("four"));
    assertEquals(5, parseEnglishNumerals("five"));
    assertEquals(6, parseEnglishNumerals("six"));
    assertEquals(7, parseEnglishNumerals("seven"));
    assertEquals(8, parseEnglishNumerals("eight"));
    assertEquals(9, parseEnglishNumerals("nine"));
    assertEquals(10, parseEnglishNumerals("ten"));
    assertEquals(11, parseEnglishNumerals("eleven"));
    assertEquals(1016, parseEnglishNumerals("one thousand and sixteen"));
    assertEquals(1492, parseEnglishNumerals("fourteen hundred ninety two"));
    assertEquals(1958, parseEnglishNumerals("one thousand nine hundred fifty eight"));
    assertEquals(2015, parseEnglishNumerals("two thousand fifteen"));
    assertEquals(52100, parseEnglishNumerals("fifty two thousand and one hundred"));
    assertEquals(152000, parseEnglishNumerals("one hundred fifty two thousand"));
//    assertEquals(1492, parseEnglishNumerals("fourteen ninety two"));
//    assertEquals(2015, parseEnglishNumerals("twenty fifteen"));
  }
}
