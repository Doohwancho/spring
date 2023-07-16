package com.tdd.tddTest.jqwik.how_to_find_property.inverse_function;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Set;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.StringLength;

public class URLEncoderTest {

  /*
    inverse pattern할 때 주의사항!

    array 두번 뒤집는건 원본으로 돌아오는데,
    encode -> decode는 원본으로 안돌아올 수 있음!

    ex. "" 이 문자열은 encode가 안됨! "?" 로 변환됨!

    ---

    해결법: why use @AlphaChars?

    character "" cannot be correctly encoded and then decoded using the "Big5" charset.
    The "Big5" charset is used for Traditional Chinese characters and does not support the "" character.
    When the character is encoded and then decoded, it is replaced with a "?" character,
    causing the test to fail.

    the @AlphaChars annotation is used to limit the characters in toEncode to ASCII alphabetic characters
   */

  @Property
  void encodeAndDecodeAreInverse(
      @ForAll @StringLength(min = 1, max = 20) @AlphaChars String toEncode,
      @ForAll("charset") String charset
  ) throws UnsupportedEncodingException {
    String encoded = URLEncoder.encode(toEncode, charset);
    assertThat(URLDecoder.decode(encoded, charset)).isEqualTo(toEncode);
  }

  @Provide
  Arbitrary<String> charset() {
    Set<String> charsets = Charset.availableCharsets().keySet();
    return Arbitraries.of(charsets.toArray(new String[charsets.size()]));
  }

}
