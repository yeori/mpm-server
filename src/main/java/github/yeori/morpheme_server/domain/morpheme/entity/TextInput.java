package github.yeori.morpheme_server.domain.morpheme.entity;

import java.util.Set;

public record TextInput(String input, Set<String> target) {
}
