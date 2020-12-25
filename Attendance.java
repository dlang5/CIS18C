import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * Attendance takes the name list and presents the data
 *
 * @version 1.0 12/7/20
 * @author Dennis Lang
 */

public class Attendance {
  public static void main(String[] args) {

    /* new array of Students, with 10 total */
    int NUM_STUDENTS = 10;
    Student studentList[] = new Student[NUM_STUDENTS];

    /* new Arraylist to hold file contents line by line */
    ArrayList<String> list = new ArrayList<String>();    

    /* try adding the next line to the Arraylist then close */
    try {
    Scanner myFile = new Scanner(new File("Names.txt"));
    while (myFile.hasNext()) {
      list.add(myFile.next());
    }
    myFile.close();           
    /* catch file not found */
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    /* convert Arraylist to Array */
    String nameArray[] = list.toArray(new String[list.size()]);

    /* print unmodified */
    System.out.println("\nCheck-In Sheet:\n" + "-------------");
    for (int i = 0; i < nameArray.length / 2; i++){
      System.out.println(nameArray[2 * i] + ' ' + nameArray[2 * i + 1]);
    }

    /* convert Arraylist to Stack */
    Stack<String> stax = new Stack<String>();
    stax.addAll(list);
    System.out.println("\nIs sign-in sheet empty? " + stax.empty());  
    /* check no students in list */
    peekBottom(stax);
   
    /* print unsorted */
    System.out.println("\nUnsorted List:\n" + "-------------");
    for (int i = 0; i < nameArray.length / 2; i++) {
      studentList[i] = new Student(nameArray[2 * i + 1], nameArray[2 * i]); // algorithm
      studentList[i].showData();
    }

    /* HashMap example "Boggle" scores for each person's name */
    System.out.println("\nBoggle Score Calculator:\n" + "------------------");
    Map<String, Integer> map = new HashMap<>();
    for (String str: list) {
      map.put(str, str.length()); // seed map with ArrayList
    }
    Map<String, Integer> sortedMap = sortByValue(map);
    printMap(sortedMap);


    /* sort by last name, using compareTo() */
    Arrays.sort(studentList, new Comparator<Student>() {
        @Override
        public int compare (Student first, Student second) {
            return first.getName().compareTo(second.getName());
        }
    });

    /* print sorted */
    System.out.println("\nSorted List:\n" + "-------------");
    for (int i = 0; i < nameArray.length/2; i++) {
      studentList[i].showData();
    }

    /* binary search the ArrayList for a name - case sensitive */
    Scanner scanner = new Scanner(System.in);
    System.out.println("\nFind check-in order using binary search? (y/n)");
    char answer = scanner.next().charAt(0); 
    if (answer != 'n' && answer == 'y') {
      System.out.println("Enter first or last name (case sensitive):");
      scanner.nextLine();
      String nameToSearch = scanner.nextLine();
      int index = Collections.binarySearch(list, nameToSearch);
      if (index == -21) {
        System.out.println("Not found.");
      } else {
        int checkInOrder = (int)Math.ceil(list.indexOf(nameToSearch) / 2) + 1;
        System.out.println("Name Found. Check in order (out of 10): " + checkInOrder);
      }
    }
  }

  /* print map with point values */
  public static <K, V> void printMap(Map<K, V> map) {
    for (Map.Entry<K, V> entry : map.entrySet()) {
      int valueInt = ((Integer)entry.getValue()); // string to int
      int score = 0; // initialize
      if ( valueInt < 3) {
        score = 0;
      }else if (valueInt >= 3 && valueInt < 5) {
        score = 1;
      } else if (valueInt == 5) {
        score = 2;
      } else if (valueInt == 6) {
        score = 3;
      } else if (valueInt <= 7) {
        score = 5;
      } else {
        score = 11;
      }
      System.out.println(entry.getKey() + " = " + score + " point(s)");
    }
  }

  /* sort map by value, not unique key */
  private static Map<String, Integer> sortByValue(Map<String, Integer> map) {
    List<Map.Entry<String, Integer>> list =
            new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
        public int compare(Map.Entry<String, Integer> o1,
                            Map.Entry<String, Integer> o2) {
            return (o1.getValue()).compareTo(o2.getValue());
        }
    });
    Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
    for (Map.Entry<String, Integer> entry : list) {
        sortedMap.put(entry.getKey(), entry.getValue());
    }
    return sortedMap;
  }

  /* recursive view bottom of stack */
  public static void peekBottom(Stack<String> s) {
    Stack<String> temp = new Stack<String>();
    while (s.empty() == false) {
      temp.push(s.peek()); // seed temp stack to use temp.peek() on bottom of stax
      s.pop();
    }   
    System.out.println("First to class: " + temp.peek());
  }
}



