package github.yeori.morpheme_server.service;

public class Jamo {
  final private static char[] CHO = "ㄱㄲㄴㄷㄸㄹㅁㅂㅃㅅㅆㅇㅈㅉㅊㅋㅌㅍㅎ".toCharArray();
  final private static char[] JUNG = "ㅏㅐㅑㅒㅓㅔㅕㅖㅗㅘㅙㅚㅛㅜㅝㅞㅟㅠㅡㅢㅣ".toCharArray();
  final private static char[] JONG = "_ㄱㄲㄳㄴㄵㄶㄷㄹㄺㄻㄼㄽㄾㄿㅀㅁㅂㅄㅅㅆㅇㅈㅊㅋㅌㅍㅎ".toCharArray();

  public static String decompose(String text) {
    int len = text.length();
    char[] chrs = new char[3 * len];
    int pos = 0;
    for (int k = 0; k < len; k++) {
      int offset = text.charAt(k) - 44032;
      int idx0 = (offset / 28 / 21);
      if (idx0 < 0) {
        continue;
      }
      chrs[pos++] = CHO[idx0];
      int idx1 = (offset / 28 % 21);
      chrs[pos++] = JUNG[idx1];
      int idx2 = (offset % 28);
      if (idx2 > 0) {
        chrs[pos++] = JONG[idx2];
      }
    }
    return new String(chrs, 0, pos);

  }
}
