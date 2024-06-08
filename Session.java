
import java.util.ArrayList;
import java.util.HashMap;

public class Session {
  final int participantId;
  private final int sessionId;
  private final String language;
  private ArrayList<Integer> roundIds;
  private final int startTime;
  private final int endTime;

  Session(int participantId, int sessionId, String language, ArrayList<Integer> roundIds, int startTime, int endTime) {
    this.participantId = participantId;
    this.sessionId = sessionId; 
    this.language = language;
    this.roundIds = roundIds;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public int getId() {
    return this.participantId;
  }

  public int duration() {
    return endTime - startTime;
  }

  public void updateRoundStats(HashMap<Integer, Round> allRounds, 
  HashMap<String, Integer> scores, HashMap<String, Integer> durations, HashMap<String, Integer> numRounds) {
    for(Integer i : this.roundIds) { // for every round id in this session
      Round r = allRounds.get(i); // get round
      scores.put(this.language, r.addScore(scores.get(this.language))); // increase score by score of r 
      durations.put(this.language, r.addDuration(durations.get(this.language))); // increase duration by length of r
      numRounds.put(this.language, numRounds.get(this.language) + 1); // increase number of rounds for this language
    }
  }
}

