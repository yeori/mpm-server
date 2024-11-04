package github.yeori.morpheme_server.domain.morpheme.entity;

import java.util.stream.Collectors;
import java.util.List;
import github.yeori.morpheme_server.domain.Token;

public record MorphemeDto(List<Token> tokens, double elapsed) {

  public String getAsText() {
    return this.tokens.stream().map(token -> token.getSurface()).collect(Collectors.joining(" "));
  }

  public String getAsJamo() {
    return this.tokens.stream().map(token -> token.getSurfaceJamo())
        .collect(Collectors.joining(" "));
  }
}
