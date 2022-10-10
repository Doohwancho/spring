package integration_layer.data_access_object;

public class Main {
   public static void main(String[] args) {
      StudentDao studentDao = new StudentDaoImpl();

      //print all students
      for (Student student : studentDao.getAllStudents()) {
         System.out.println("Student: [RollNo : " + student.getRollNo() + ", Name : " + student.getName() + " ]");
      }


      //update student
      Student student =studentDao.getAllStudents().get(0);
      student.setName("Michael");
      studentDao.updateStudent(student);

      //get the student
      studentDao.getStudent(0);
      System.out.println("Student: [RollNo : " + student.getRollNo() + ", Name : " + student.getName() + " ]");		
   }
}


/*

---
structure

- Student

- StudentDao(interface)
	- StudentDaoImpl


---
what is Data Access Object?

DAO는 
1. repository layer
	VO(value object, Entity with getter & setter)인 Student와
2. business layer
	해당 VO 여러개 들을 관리하는 함수들을 역할 분리하여 담은 객체



*/