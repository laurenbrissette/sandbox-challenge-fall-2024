import java.util.ArrayList;

// represents a Stat for a Participant without any Session data 
public class EmptyStat extends Stat{
  private final ArrayList<Language> languages;
  private final String averageRoundScore;
  private final String averageSessionDuration;

  EmptyStat(int id, String name) {
    super(id, name);
    this.languages = new ArrayList<Language>();
    this.averageRoundScore = "N/A";
    this.averageSessionDuration = "N/A";
  }
}
