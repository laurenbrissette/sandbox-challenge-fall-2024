
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.Gson;

// represents the class that parses the Gson input
public class Parser {
  private final HashMap<Integer, Session> sessions;
  private final HashMap<Integer, Round> rounds;
  private final HashMap<Integer, Participant> participants;

  Parser(Reader read) {
    // convert Reader into ParsedInfo objects
    ParsedInfo js = new Gson().fromJson(read, ParsedInfo.class);
    // break apart ParsedInfo output into HashMaps id -> object
    this.sessions = js.hashSessions(); 
    this.participants = js.hashParticipants();
    this.rounds = js.hashRounds();
  }

  // creates a sorted list of Stats for this Parser's information 
  // considering the sorting requirements specified in the problem statement
  public ArrayList<Stat> getStats() {
    ArrayList<Stat> result = new ArrayList<Stat>();
    for(Integer i : this.participants.keySet()) { // add stat for this participant to result
      result.add(participants.get(i).stats(this.sessions, this.rounds));
    }
    return new Utils().sortBy(result, new StatComparator());
  }

  // returns a json output which reflects the Reader initially used to 
  // construct this Parser, broken down, anaylzed, and reformatted 
  // based on the desired metrics
  public String getResponse() {
    ArrayList<Stat> stats = this.getStats();
    return new Gson().toJson(stats);
  }
} 


