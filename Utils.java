import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;
// function class
public class Utils {
  // rounds the given double two places
  public double round(double value) {
    return Math.round(value * 100.0) / 100.0;
  }

  // sorts a list <T> using a Comparator
  public <T> ArrayList<T> sortBy(ArrayList<T> arr, Comparator<T> comp) {
    ArrayList<T> result = new ArrayList<T>();
    for(T t : arr) {
      result = this.insertBy(t, result, comp);
    }
    return result;
  }

  // inserts item into already sorted list by Comparator
  public <T> ArrayList<T> insertBy(T item, ArrayList<T> result, Comparator<T> comp) {
    for(int x = 0; x < result.size(); x += 1) {
      if(comp.compare(item, result.get(x)) > 0) {
        result.add(x, item);
        return result;
      } 
    }
    result.add(item);
    return result;
  }
}

// compares two Stat objects 
class StatComparator implements Comparator<Stat> {
  // compares o1 and o2
  public int compare(Stat o1, Stat o2) {
    return (-1) * o1.name.compareTo(o2.name);
  }
}

// compares two Language objects 
// constructed with HashMap containing all scores because the 
// Language are compared based on their associated total score 
class LanguageComparator implements Comparator<Language> {
  HashMap<String, Integer> scores;
  LanguageComparator(HashMap<String, Integer> scores) {
    this.scores = scores;
  }
  // compares o1 and o2
  public int compare(Language o1, Language o2) {
    if(scores.get(o1.language) > scores.get(o2.language)) {
      return 1;
    }
    else if(scores.get(o1.language) == scores.get(o2.language)) {
      return 0;
    }
    else {
      return -1;
    }
  }
}
