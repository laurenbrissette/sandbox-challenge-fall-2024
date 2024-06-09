

// represents a statistic for a Participant which will be posted as a JSON
// may be either an EmptyStat or a FullStat
abstract class Stat {
  private final int id;
  final String name; // must be accessible by comparator

  Stat(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
