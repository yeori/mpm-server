package github.yeori.morpheme_server.domain.morpheme.entity;

import java.util.List;
import github.yeori.morpheme_server.domain.Token;

public record MorphemeDto(List<Token> tokens, double elapsed) {
}
