
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import com.google.gson.Gson;

// represents the class that parses the Gson input
public class Parser {
  private final HashMap<Integer, Session> sessions;
  private final HashMap<Integer, Round> rounds;
  private final  HashMap<Integer, Participant> participants;

  Parser(Reader gson) {
    // get properties from data
    Properties props = new Gson().fromJson(gson.toString(), Properties.class);

    // convert sessions property to HashMap<Integer, Session>
    String sessionsAsString = props.getProperty("sessions");
    ArrayList<String> sessionsStringArr = new Utils().toList(sessionsAsString);
    HashMap<Integer,Session> sessionHash = new HashMap<Integer, Session>();
    for(String s : sessionsStringArr) {
      Session sesh = new Gson().fromJson(s, Session.class);
      sessionHash.put(sesh.getId(), sesh);
    }

    // convert rounds property to HashMap<Integer, Round>
    String roundsAsString = props.getProperty("rounds");
    ArrayList<String> roundsStringArr = new Utils().toList(roundsAsString);
    HashMap<Integer, Round> roundsHash = new HashMap<Integer, Round>();
    for(String s : roundsStringArr) {
      Round rou = new Gson().fromJson(s, Round.class);
      roundsHash.put(rou.getId(), rou);
    }

    // convert participantInfo property to HashMap<Integer, Participant>
    String partAsString = props.getProperty("participantInfo");
    ArrayList<String> partStringArr = new Utils().toList(partAsString);
    HashMap<Integer, Participant> partHash = new HashMap<Integer, Participant>();
    for(String s : partStringArr) {
      Participant pant = new Gson().fromJson(s, Participant.class);
      partHash.put(pant.getId(), pant);
    }

    this.sessions = sessionHash;
    this.rounds = roundsHash;
    this.participants = partHash;
  }

  public ArrayList<Stat> getStats() {
    ArrayList<Stat> result = new ArrayList<Stat>();
    for(Integer i : this.participants.keySet()) {
      result.add(participants.get(i).stats(this.sessions, this.rounds));
    }

    return new Utils().sortBy(result, new StatComparator());
  }
} 
