

import java.util.ArrayList;

// represents a set of Stats for a Participant
public class FullStat extends Stat{
  private ArrayList<Language> languages;
  private double averageRoundScore;
  private double averageSessionDuration;

  FullStat(int id, String name, ArrayList<Language> languages, double averageRoundScore, double averageSessionDuration) {
    super(id, name);
    this.languages = languages;
    this.averageRoundScore = averageRoundScore;
    this.averageSessionDuration = averageSessionDuration;
  }
}
