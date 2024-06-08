

public class Language {
  private final String language;
  final double averageScore;
  private final double averageRoundDuration;

  Language(String language, double averageScore, double averageRoundDuration) {
    this.language = language;
    this.averageScore = averageScore;
    this.averageRoundDuration = averageRoundDuration;
  }
}
