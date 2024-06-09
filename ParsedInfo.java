import java.util.HashMap;
import java.util.List;

// stores and manipuates the data from given Json file as Lists to HashMaps 
// used in Parser constructor
public class ParsedInfo {
  private final List<Session> sessions;
  private final List<Participant> participantInfo;
  private final List<Round> rounds;

  ParsedInfo(List<Session> sessions, List<Round> rounds, List<Participant> participantInfo) {
    this.sessions = sessions;
    this.participantInfo = participantInfo;
    this.rounds = rounds;
  }

  // creates a HashMap storing this ParsedInfo's Sessions with their sessionIds as the keys
  public HashMap<Integer, Session> hashSessions() {
    HashMap<Integer, Session> hash = new HashMap<Integer, Session>();
    for(Session s : this.sessions) {
      hash.put(s.getId(), s);
    }
    return hash;
  }

  // creates a HashMap storing this ParsedInfo's Participants with their participantIds as the keys
  public HashMap<Integer, Participant> hashParticipants() {
    HashMap<Integer, Participant> hash = new HashMap<Integer, Participant>();
    for(Participant s : this.participantInfo) {
      hash.put(s.getId(), s);
    }
    return hash;
  }

  // creates a HashMap storing this ParsedInfo's Rounds with their roundIds as the keys
  public HashMap<Integer, Round> hashRounds() {
    HashMap<Integer, Round> hash = new HashMap<Integer, Round>();
    for(Round s : this.rounds) {
      hash.put(s.getId(), s);
    }
    return hash;
  }
}