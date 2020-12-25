/**
 * Student class contains data + outputs last name, first name
 *
 * @version 1.0 12/7/20
 * @author Dennis Lang
 */


/* class of students */
public class Student {
  String fName;
  String lName;
  String fullName;
/* setter */
public Student (String c, String d) {
  lName = c;
  fName = d;
  fullName = c + ", " + d;
}
  /* print data method */
  public void showData() {
      System.out.println(fullName);
  }
  /* getter */
  public String getName() {
      return lName;
  }
}



