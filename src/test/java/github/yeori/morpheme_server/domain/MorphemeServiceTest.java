package github.yeori.morpheme_server.domain;

import java.util.Set;
import org.junit.jupiter.api.Test;
import github.yeori.morpheme_server.domain.morpheme.MorphemeService;
import github.yeori.morpheme_server.domain.morpheme.entity.TextInput;

public class MorphemeServiceTest {
  @Test
  void testAnalyizeText() {
    var service = new MorphemeService();
    var dto = service.analyizeText(new TextInput(
        "하지만 객체 내부의 property 가 변경된 경우에는 reactive 하게 작동하지 않습니다. _wdata.temperature = \"40C\" 로 변경해도 화면은 갱신되지 않습니다.",
        Set.of("NNG", "SL"), 2));
    for (Token token : dto.tokens()) {
      System.out.println(token);
    }
    System.out.println(dto.getAsJamo());
    System.out.println(dto.getAsText());
  }
}
