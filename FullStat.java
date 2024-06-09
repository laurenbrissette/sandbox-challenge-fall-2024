import java.util.ArrayList;

// represents a set statistics, a Stat for a Participant with Session data
public class FullStat extends Stat{
  private final ArrayList<Language> languages;
  private final double averageRoundScore;
  private final double averageSessionDuration;

  FullStat(int id, String name, ArrayList<Language> languages, double averageRoundScore, double averageSessionDuration) {
    super(id, name);
    this.languages = languages;
    this.averageRoundScore = averageRoundScore;
    this.averageSessionDuration = averageSessionDuration;
  }
}
