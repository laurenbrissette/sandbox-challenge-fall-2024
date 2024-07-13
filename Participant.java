import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

// represents a Participant in the Sessions used to measure language learning 
public class Participant {
  private final int participantId;
  private final String name;
  private final int age;
  private final List<Integer> sessions;

  Participant(int participantId, String name, int age, List<Integer> sessions) {
    this.participantId = participantId;
    this.name = name;
    this.age = age;
    this.sessions = sessions;
  }

  // returns the id of this Participant
  public int getId() {
    return this.participantId;
  }

  // determines the required stats of this Participant in form Stat 
  public Stat stats(HashMap<Integer, Session> allSessions, HashMap<Integer, Round> allRounds) {
    if(this.sessions.isEmpty()) { // initial check before continuing with FullStat
      return new EmptyStat(this.participantId, this.name);
    }
    // final variable values needed 
    double avgSessionDuration;
    double avgRoundScore;
    ArrayList<Language> langStat = new ArrayList<Language>();
    // accumulators to store info
    HashMap<String, Integer> scores = new HashMap<String, Integer>(); // maps language -> total score
    HashMap<String, Integer> durations = new HashMap<String, Integer>(); // maps language -> total duration
    HashMap<String, Integer> numRounds = new HashMap<String, Integer>(); // maps language -> number of rounds
    HashMap<String, Integer> maxScore = new HashMap<String, Integer>(); // maps language -> top round score

    // for every session id, accumulates necessary information on session based on above variables
    for(int i : this.sessions) { // for every one of our session ids 
      Session s = allSessions.get(i); // get the session
      if(s.equals(null)) { // null check
        throw new IllegalArgumentException("s is null");
      }
      else { // update stats based on that session 
        s.updateRoundStats(allRounds, scores, durations, numRounds, maxScore);
      }
    }
    // for each language, create a Langauge w/ needed info and add to langStat list
    // and accumulate totals
    double scoreTotal = 0;
    double durationTotal = 0;
    double numRoundsTotal = 0;
    for(String s : durations.keySet()) { 
      // get average score 
      double avgScore = new Utils().round((double)scores.get(s) / (double)numRounds.get(s));
      // get average duration 
      double avgDur = new Utils().round((double)durations.get(s) / (double)numRounds.get(s));
      // add to final array 
      langStat.add(new Language(s, avgScore, avgDur, maxScore.get(s)));
      // accumulate totals
      scoreTotal = scoreTotal + scores.get(s);
      durationTotal = durationTotal + durations.get(s);
      numRoundsTotal = numRoundsTotal + numRounds.get(s);
    }
    // determine average session duration 
    avgSessionDuration = new Utils().round(durationTotal / this.sessions.size());
    // determine average round score 
    avgRoundScore = new Utils().round(scoreTotal / numRoundsTotal);

    return new FullStat(this.participantId, this.name, new Utils().sortBy(langStat, new LanguageComparator(scores)), avgRoundScore, avgSessionDuration);
  }
}

