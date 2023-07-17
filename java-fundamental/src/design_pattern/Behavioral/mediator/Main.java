package design_pattern.Behavioral.mediator;

public class Main {
	public static void main(String[] args) {

		AuthenticationDialog dialog = new AuthenticationDialog(); //facade 역
		
		System.out.println("==== 1.Current state");
		dialog.printCurrentStatus();
		
		System.out.println();
		System.out.println("==== 2. Convert from login to user join form");
		dialog.isJoinForm.click();
		
		System.out.println();
		System.out.println("==== 3. submit user join");
		dialog.ok.click();
		
		System.out.println();
		System.out.println("==== 4. Convert from user join to login");
		dialog.isJoinForm.click();
		
		System.out.println();
		System.out.println("==== 5. Check remember me");
		dialog.remeberMe.click();
		
		System.out.println();
		System.out.println("==== 6. Do login");
		dialog.ok.click();
	}
}

/*

---
output

==== 1.Current state
Title: Login
isJoinForm: false
remeberMe: false

==== 2. Convert from login to user join form
show [regUsername, regPassword, regEmail] elements

==== 3. submit user join
Validate [regUsername, regPassword, regEmail] elements
And user join this system.

==== 4. Convert from user join to login
hide [regUsername, regPassword, regEmail] elements

==== 5. Check remember me
set ID in cookie

==== 6. Do login
Login!





---
structure

- Component
	- Button
	- Textbox
	- Checkbox

- Mediator
	- AuthenticationDialog
	

---
what is mediator?

AuthenticationDialog(Mediator)가 Button, Textbox, Checkbox 다양한 컴포넌트의 의사결정 중간에서 제어
컴포넌트 끼리 연락 안됨. 완전 분리.



*/