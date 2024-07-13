
// represents a data set about a Participant's learning progress with a given language
public class Language {
  final String language; // must be available to comparator
  private final double averageScore;
  private final double averageRoundDuration;
  private final double maxScore;

  Language(String language, double averageScore, double averageRoundDuration, double maxScore) {
    this.language = language;
    this.averageScore = averageScore;
    this.averageRoundDuration = averageRoundDuration;
    this.maxScore = maxScore;
  }
}
