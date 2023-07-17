package design_pattern.J2EE.presentation_layer.front_controller;

public class Main {
   public static void main(String[] args) {
      FrontController frontController = new FrontController();
      frontController.dispatchRequest("HOME");
      frontController.dispatchRequest("STUDENT");
   }
}

/*


---
structure

- FrontController
	-> Dispatcher
		-> HomeView
		-> StudentView


---
what is front controller pattern?

FrontController가 request올 때마다, Dispatcher 시켜서 요청된 View로 보냄 

FrontController는 Dispatcher한테 시키는 일만 담당하고,
Dispatcher는 요청 url과 담당 View 매칭하는 일만 담당함. 

*/