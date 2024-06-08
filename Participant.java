
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Participant {
  final int participantId;
  private final String name;
  private final int age;
  private final ArrayList<Integer> sessionIds;

  Participant(int participantId, String name, int age, ArrayList<Integer> sessionIds) {
    this.participantId = participantId;
    this.name = name;
    this.age = age;
    this.sessionIds = sessionIds;
  }

  Participant(int participantId, String name, int age) {
    this(participantId, name, age, new ArrayList<Integer>());
  }

  // returns the id of this Participant
  public int getId() {
    return this.participantId;
  }

  // determines the required stats of this Participant in form Stat 
  public Stat stats(HashMap<Integer, Session> allSessions, HashMap<Integer, Round> allRounds) {
    if(this.sessionIds.isEmpty()) {
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
    // for every session id, accumulates necessary information on session based on above variables
    for(Integer i : this.sessionIds) { // for every one of our session ids 
      Session s = allSessions.get(i); // get the session 
      s.updateRoundStats(allRounds, scores, durations, numRounds);
    }

    // for each language, create a Langauge w/ needed info and add to langStat list
    // and accumulate round totals
    double scoreTotal = 0;
    double durationTotal = 0;
    double numRoundsTotal = 0;
    for(String s : durations.keySet()) { 
      // get average score 
      double avgScore = (double)scores.get(s) / numRounds.get(s);
      // get average duration 
      double avgDur = (double)durations.get(s) / numRounds.get(s);
      // add to final array 
      langStat.add(new Language(s, avgScore, avgDur));
      scoreTotal = scoreTotal + scores.get(s);
      durationTotal = durationTotal + durations.get(s);
      numRoundsTotal = numRoundsTotal + numRounds.get(s);
    }
    // determine average session duration 
    avgSessionDuration = durationTotal / this.sessionIds.size();
    // determine average round duration 
    avgRoundScore = scoreTotal / numRoundsTotal;

    return new FullStat(this.participantId, this.name, new Utils().sortBy(langStat, new LanguageComparator()), avgRoundScore, avgSessionDuration);
  }
}

