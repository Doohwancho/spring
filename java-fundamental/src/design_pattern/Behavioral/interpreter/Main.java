package design_pattern.Behavioral.interpreter;

public class Main {
   //Rule: Robert and John are male
   public static Expression getMaleExpression(){
      Expression robert = new TerminalExpression("Robert");
      Expression john = new TerminalExpression("John");
      return new OrExpression(robert, john);		
   }

   //Rule: Julie is a married women
   public static Expression getMarriedWomanExpression(){
      Expression julie = new TerminalExpression("Julie");
      Expression married = new TerminalExpression("Married");
      return new AndExpression(julie, married);		
   }

   public static void main(String[] args) {
      Expression isMale = getMaleExpression();
      Expression isMarriedWoman = getMarriedWomanExpression();

      System.out.println("John is male? " + isMale.interpret("John"));
      System.out.println("Julie is a married women? " + isMarriedWoman.interpret("Married Julie"));
   }
}

/*

---
output

John is male? true
Julie is a married women? true


---
structure

- Expression(interface)
	- TerminalExpression
	- AndExpression
	- OrExpression

---
what is interpreter pattern for?

interpreting syntax within context


---
소감

TerminalExpression에 필터 걸고, And, OR로 묶은 다음,
파라미터가 필터에 걸리는지 보는 구조인데, 

필터 패턴이랑 다를게 없네?
굳이 다른점을 꼽자면, 필터 패턴은 array에 조건 a,b,c 등록해놓고, loop하면서 필터한다이고,
인터프리터 패턴은 개별 필터들 조립해서 적용한다 인데..


*/