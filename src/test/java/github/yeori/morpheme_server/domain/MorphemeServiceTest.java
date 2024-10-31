package github.yeori.morpheme_server.domain;

import java.util.Set;
import org.junit.jupiter.api.Test;
import github.yeori.morpheme_server.domain.morpheme.MorphemeService;
import github.yeori.morpheme_server.domain.morpheme.entity.TextInput;

public class MorphemeServiceTest {
  @Test
  void testAnalyizeText() {
    var service = new MorphemeService();
    var dto = service.analyizeText(new TextInput("오늘 놀다.", Set.of("NNG")));
    for (Token token : dto.tokens()) {
      System.out.println(token);
    }
  }
}
