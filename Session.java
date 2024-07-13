import java.util.HashMap;
import java.util.List;

// represents a Session in the language learning trials
public class Session {
  private final int participantId;
  private final int sessionId;
  private final String language;
  private final List<Integer> rounds;
  private final int startTime;
  private final int endTime;

  Session(int participantId, int sessionId, String language, List<Integer> rounds, int startTime, int endTime) {
    this.participantId = participantId;
    this.sessionId = sessionId; 
    this.language = language;
    this.rounds = rounds;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  // determines the id of this Session
  public int getId() {
    return this.sessionId;
  }

  // determines the time duration of this Session
  public int duration() {
    return endTime - startTime;
  }
/**
     * store the greatest round score for each language
     * HashMap<String, Integer> maxScore = new HashMap<String, Integer>(); // maps language -> top round score
     * within visiting each session, find top round score 
     *  if current exists 
     *      if greater than current, replace 
     *  else 
     *      push for first time, max score 
     * 
     */
  // EFFECT: mutates the given HashMaps scores, durations, and numRounds using the given HashMap allRounds
  // with the Session and Round data stored in this Session object
  public void updateRoundStats(HashMap<Integer, Round> allRounds, HashMap<String, Integer> scores, HashMap<String, Integer> durations, HashMap<String, Integer> numRounds, HashMap<String, Integer> maxScore) {
    for(int i : this.rounds) { // for every round id in this session
      Round r = allRounds.get(i); // get round
      if(scores.containsKey(this.language)) { // if language already encountered, update
        scores.put(this.language, r.addScore(scores.get(this.language))); // increase score by score of r 
        durations.put(this.language, r.addDuration(durations.get(this.language))); // increase duration by length of r
        numRounds.put(this.language, numRounds.get(this.language) + 1); // increase number of rounds for this language
        if(r.getScore() > maxScore.get(this.language)) {
          maxScore.put(this.language, r.getScore());
        }
      }
      else { // otherwise, add for the first time to each list
        scores.put(this.language, r.addScore(0)); // set score as r
        durations.put(this.language, r.addDuration(0)); // set duration as duration of r
        numRounds.put(this.language, 1); // indicate first round
        maxScore.put(this.language, r.getScore());
      }
    }
  }
}

