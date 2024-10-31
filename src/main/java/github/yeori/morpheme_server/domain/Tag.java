package github.yeori.morpheme_server.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

public enum Tag {
  NNG("일반 명사"),
  NNP("고유 명사"),
  NNB("의존 명사"),
  NNBC("단위를 나타내는 명사"),
  NR("수사"),
  NP("대명사"),
  VV("동사"),
  VA("형용사"),
  VX("보조 용언"),
  VCP("긍정 지정사"),
  VCN("부정 지정사"),
  MM("관형사"),
  MAG("일반 부사"),
  MAJ("접속 부사"),
  IC("감탄사"),
  JKS("주격 조사"),
  JKC("보격 조사"),
  JKG("관형격 조사"),
  JKO("목적격 조사"),
  JKB("부사격 조사"),
  JKV("호격 조사"),
  JKQ("인용격 조사"),
  JX("보조사"),
  JC("접속 조사"),
  EP("선어말 어미"),
  EF("종결 어미"),
  EC("연결 어미"),
  ETN("명사형 전성 어미"),
  ETM("관형형 전성 어미"),
  XPN("체언 접두사"),
  XSN("명사 파생 접미사"),
  XSV("동사 파생 접미사"),
  XSA("형용사 파생 접미사"),
  XR("어근"),
  SF("마침표, 물음표, 느낌표"),
  SE("줄임표 …"),
  SSO("여는 괄호 (, ["),
  SSC("닫는 괄호 ), ]"),
  SC("구분자 , · / :"),
  SY("기타 기호"),
  SL("외국어"),
  SH("한자"),
  SN("숫자");

  private final String description;

  Tag(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public static Tag findTag(String tagName) {
    for (Tag tag : Tag.values()) {
      if (tag.name().equalsIgnoreCase(tagName)) {
        return tag;
      }
    }
    return null; // or you can throw an IllegalArgumentException if preferred
  }

  public static Map<String, String> toMap() {
    Entry<String, String>[] entries = Arrays.stream(Tag.values())
        .map(tag -> Map.entry(tag.name(), tag.getDescription())).toArray(Map.Entry[]::new);
    return Map.ofEntries(entries);
  }
}
