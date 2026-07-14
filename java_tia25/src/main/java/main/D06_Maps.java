package main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map.Entry;

import model.Exam;
import model.Student;

/**
 * Demo 6: Maps
 *
 * <p>Demonstrates {@link java.util.HashMap} operations: inserting, looking up, overwriting, and
 * iterating over keys, values, and entries using domain objects as keys and values.
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public class D06_Maps {

   /**
    * Entry point — runs all map demos sequentially.
    *
    * @param args command-line arguments (not used)
    */
   public static void main(String[] args) {
      HashMap<Student, Exam> studentsWithExam = new HashMap<>();

      /* Wert setzen */
      studentsWithExam.put(new Student("8162436", "Hans"), new Exam(LocalDate.now(), 2.7));
      studentsWithExam.put(new Student("1102636", "Anna"), new Exam(LocalDate.now(), 1.4));
      studentsWithExam.put(new Student("7253847", "Petra"), new Exam(LocalDate.now(), 3.6));

      /* Schlüssel/Wert prüfen */
      System.out.println(studentsWithExam.containsKey(new Student("8162436", "Hans")));
      System.out.println(studentsWithExam.containsValue(new Exam(LocalDate.now(), 1.4)));

      /* Wert überschreiben + Wert lesen */
      Exam oldExam =
            studentsWithExam.put(new Student("1102636", "Anna"), new Exam(LocalDate.now(), 1.2));
      System.out.println("Anna's old exam: " + oldExam);
      System.out.println("Anna's new exam: " + studentsWithExam.get(new Student("1102636", "Anna")));

      /* Werte ausgeben */
      System.out.println(studentsWithExam);

      /* Datensammlungen: die Menge aller Schlüssel */
      System.out.println();
      System.out.println("Name list:");
      for (Student s : studentsWithExam.keySet()) {
         System.out.println(s.getName() + " [ ]");
      }

      /* Datemsammlungen: alle Werte */
      double total = 0;
      int counter = 0;
      for (Exam e : studentsWithExam.values()) {
         total += e.getGrade();
         counter++;
      }
      System.out.println("Average grade: " + (total / counter));

      /* Datensammlungen: die Menge aller Schlüssel-Wert-Paare / Einträge */
      System.out.println();
      System.out.println("Grade list: ");
      for (Entry<Student, Exam> entry : studentsWithExam.entrySet()) {
         Student s = entry.getKey();
         Exam e = entry.getValue();
         System.out.println(s.getId() + ": " + e.getGrade());
      }
   }

}
