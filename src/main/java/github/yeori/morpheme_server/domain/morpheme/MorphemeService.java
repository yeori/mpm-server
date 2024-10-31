package github.yeori.morpheme_server.domain.morpheme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.bitbucket.eunjeon.seunjeon.CompressedAnalyzer;
import org.bitbucket.eunjeon.seunjeon.LNode;
import org.bitbucket.eunjeon.seunjeon.Morpheme;
import org.springframework.stereotype.Service;
import github.yeori.morpheme_server.domain.Token;
import github.yeori.morpheme_server.domain.morpheme.entity.MorphemeDto;
import github.yeori.morpheme_server.domain.morpheme.entity.TextInput;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import scala.collection.JavaConversions;

@Service
@Slf4j
public class MorphemeService {

  private final AtomicBoolean initialized = new AtomicBoolean(false); // Thread-safe property
  private final static Set<String> VERB = Set.of("VV", "VA");

  @PostConstruct
  public void init() {
    log.info("[Analyzer] initializing..." + Thread.currentThread().getName());
    Runnable r = () -> {
      initAnalyzer();
    };
    new Thread(r, "init-analizer").start();
  }

  private void initAnalyzer() {
    CompressedAnalyzer.parseJava("안녕");
    initialized.set(true);
    log.info("[Analyzer] initialized " + Thread.currentThread().getName());
  }

  private List<Token> decomposeMorpheme(Morpheme mm) {
    ArrayList<Token> tokens = new ArrayList<>();
    Iterable<Morpheme> cols = JavaConversions.asJavaIterable(mm.deComposite()); // 가 + ㅂ니다.
    for (Morpheme col : cols) {
      // System.out.println("[>> ]" + col.getFeatureHead() + ", " + col.getSurface());
      String word = col.getSurface();
      String pumsa = col.getFeatureHead();
      tokens.add(Token.from(word, pumsa));
    }
    return tokens;
  }

  public MorphemeDto analyizeText(TextInput input) {
    Set<String> target = input.target();
    Iterator<String> dict = Arrays.asList("펭수").iterator();
    CompressedAnalyzer.setUserDict(dict);
    Iterable<LNode> nodes = CompressedAnalyzer.parseJava(input.input());
    List<Token> tokens = new ArrayList<>();
    long s = System.nanoTime();
    for (LNode node : nodes) {
      Morpheme mm = node.morpheme();
      String word = mm.getSurface(); // 기본형(어근), 명사
      String feather = mm.getFeatureHead(); // NNP NP, ..
      Token token = Token.from(word, feather);
      String mType = mm.getMType().toString(); // COMMON, INFLECT,
      if ("COMMON".equals(mType)) {
        if (VERB.contains(feather)) {
          token.setSurface(token.getSurface() + "다");
        }
      } else {
        var decomposed = this.decomposeMorpheme(mm);
        token.setDecomposed(decomposed);
        if ("INFLECT".equals(mType)) {
          token.setSurface(decomposed.get(0).getSurface() + "다");
        }
      }
      if (target == null || target.contains(token.inferMainPumsa())) {
        tokens.add(token);
      }
    }
    double e = (System.nanoTime() - s) / 1_000_000.0;

    return new MorphemeDto(tokens, e);
  }
}
