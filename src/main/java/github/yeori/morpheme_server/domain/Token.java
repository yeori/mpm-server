package github.yeori.morpheme_server.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Token {
  String origin;
  String surface;
  String pumsa; // NN, VV, VX etc
  List<Token> decomposed;

  public static Token from(String word, String pumsa) {
    Token tk = new Token();
    tk.origin = word;
    tk.surface = word;
    tk.pumsa = pumsa;
    return tk;
  }

  public String inferMainPumsa() {
    if (decomposed == null) {
      return pumsa;
    } else {
      return decomposed.get(0).pumsa;
    }
  }
}
