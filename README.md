# sandbox-challenge-fall-2024

# To run the program: 
For my unique url, you can simply run the main method of the WriteToFile.java class, 
and it will print the response data into result.txt.
Otherwise, if you would like to input a different data set, create a 'new Parser(Reader)'
and invoke the method getResponse() to retrieve the response data.

Ex.
Reader r = new FileReader("somefile.json");
String response = new Parser(r).getResponse();

# Design process: 
Initially I made the following observations: 

(1) Session, Participant, and Round objects all have unique ids.  These ids lend themselves well to 
HashMaps because I wouldn't have to worry about collisions, and because they would provide instant 
lookup to almost all of the data in the initial GET request.

(2) If I had all of the information in one place, the calculations would become fairly simple.  I was 
concerned about finding mistakes in calculations if I was doing them in multiple methods and multiple times,
so I decided to use the stats method in Participant as a place to put all the information together.
    (a) My goal was to avoid repeating steps to ensure that everything was being counted exactly once.  So, 
    I used three HashMaps to store the data for each language.  I considered using one HashMap for this
    String (language) -> [some object storing scores, durations, numRounds], but decided that would add unnecessary layers to accessing information.
    (b) As this is a bigger method, I made sure to write comments with each step, to remind myself of the 
    process I was following in each step for debugging later.


Other design choices: 
- Sorting using Comparators: since there was a longer process in determining order for Language objects, 
it seemed best to un-private a couple of basic fields (name, langauge) to allow for a clear and universal 
sorting algorithm as opposed to specialized ones

- FullStat and EmptyStat: Initially I was only using FullStat, but the easiest and clearest way to account for the "N/A" fields in the json response seemed to be to have those as fields in a different type of Stat object.  A more complicated manipulation of a FullStat to give it empty values (negative number placeholders,
null), seemed flimsy and too personalized to make sense were anyone to edit or use this in the future.

# Technical challenges
- Parsing the JSON data!  Initially I was trying to use Properties to break apart Session, Participant, and
Rounds.  Eventually, I realized it would be a lot easier to convert the Reader into a ParsedInfo, and
then manipulate the java-converted objects from there.
- I also got stuck here and there on a few silly things like using Maven to add gson to my project, realizing 
that the error on my FileReader was because I needed to import exceptions, not because the exceptions were throwing, and actually using the debugger in VS code to help me.  All of these little things, though, were more what I learned along the way rather than major inhibitors.

# Time spent 
3 - 4 hours: design and implementation 
2 - 3 hours: debugging, technical difficulties, research, gson/json, formatting files etc.
