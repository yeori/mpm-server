package github.yeori.morpheme_server.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class JamoTest {

  @Test
  void testDecompose() {
    String jamo = Jamo.decompose("안녕");
    assertEquals(6, jamo.length());
    assertEquals("ㅇㅏㄴㄴㅕㅇ", jamo);

    assertEquals("ㄱㅏㅂㅏㅇ", Jamo.decompose("가방"));
    assertEquals("ㄱㅣㅊㅏ", Jamo.decompose("기차"));
    assertEquals("ㅊㅏ", Jamo.decompose("ㅘ차"));
    assertEquals("ㅊㅏ", Jamo.decompose("ㄱ차ㅎ"));
  }

}
