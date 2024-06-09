import java.io.FileReader;
import java.io.Reader;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

// feeds response.json into Parser programming and writes response to result.txt
public class WriteToFile {
  public static void main(String[] args) {
    try {
      FileWriter myWriter = new FileWriter("result.txt");
      Reader res = new FileReader("response.json");
      Parser parsey = new Parser(res);
      myWriter.write(parsey.getResponse());
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
