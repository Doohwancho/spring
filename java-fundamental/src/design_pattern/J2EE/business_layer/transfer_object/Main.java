package design_pattern.J2EE.business_layer.transfer_object;

public class Main {
   public static void main(String[] args) {
      StudentBO studentBusinessObject = new StudentBO();

      //print all students
      for (StudentVO student : studentBusinessObject.getAllStudents()) {
         System.out.println("Student: [RollNo : " + student.getRollNo() + ", Name : " + student.getName() + " ]");
      }

      //update student
      StudentVO student = studentBusinessObject.getAllStudents().get(0);
      student.setName("Michael");
      studentBusinessObject.updateStudent(student);

      //get the student
      student = studentBusinessObject.getStudent(0);
      System.out.println("Student: [RollNo : " + student.getRollNo() + ", Name : " + student.getName() + " ]");
   }
}

/*

---
structure

- StudentVO
- StudentBO(business object)


---
what is transfer_object?

data 계층간 object(VO)를 transfer한다는 것으로,

이 예제에선 business layer에서 repository layer의 VO를 가져오는 과정에서 
object가 왔다갔다 한걸 'transfer'로 이해했나 봄. 
아님 Main()이 client라고 가정하면, client에서 Business layer와 통신 과정에서 층간 왔다갔다한 VO를 transfer했다고 보는 듯.


*/