

import java.util.ArrayList;
import java.util.Comparator;

import com.google.gson.Gson;

public class Utils {
  
  // converts an array of json objects in string format to a 
  // java ArrayList of json format String 
  public ArrayList<String> toList(String str) {
    String acc = "";
    ArrayList<String> result = new ArrayList<String>();
    for(int x = 0; x < str.length(); x += 1) {
      String character = str.substring(x, x + 1);
      if(character.equals("}")) { // end of an object 
        // convert everything in that object to a session and add to list
        result.add(acc);
        // erase acc to reset
        acc = "";
      }
      else if(!(character.equals("{"))) { // not the start of an object or the end
        // must be inner info, so add to end
        acc = acc + character;
      }
    }
    return result;
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

class StatComparator implements Comparator<Stat> {
  public int compare(Stat o1, Stat o2) {
    return o1.name.compareTo(o2.name);
  }
}

class LanguageComparator implements Comparator<Language> {
  public int compare(Language o1, Language o2) {
    if(o1.averageScore > o2.averageScore) {
      return 1;
    }
    else if(o1.averageScore == o2.averageScore) {
      return 0;
    }
    else {
      return -1;
    }
  }
}
