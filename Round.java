
// represents a Round of a Session, essentially a subsection of the Session duration
public class Round {
  private final int roundId;
  private final int sessionId;
  private final int score;
  private final int startTime;
  private final int endTime;

  Round(int roundId, int sessionId, int score, int startTime, int endTime) {
    this.roundId = roundId; 
    this.sessionId = sessionId;
    this.score = score;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  // returns the id of this Round
  public int getId() {
    return this.roundId;
  }

  // returns the score of this Round
  public int getScore() {
    return this.score;
  }

  // adds the score of this Round to given existing score and returns 
  public int addScore(int existing) {
    return existing + this.score;
  }
   
  // adds the duration of this Round to given existing duration and returns
  public int addDuration(int existing) {
    return existing + this.duration();
  }

  // determines the duration of this Round
  public int duration() {
    return this.endTime - this.startTime;
  }
}
