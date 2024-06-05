package _2_oop._1_상태_데이터의_캡슐화.nested_class.지역_클래스;

import java.util.Scanner;

public class RegistrationForm {
    
    public void getUserDetails() {
        class InputValidator { //메서드 안에 넣어버려서, 캡슐화 극대화하네?
            
            private Scanner scanner = new Scanner(System.in);
            
            public String getValidatedInput(String prompt) {
                System.out.println(prompt);
                String input = scanner.nextLine();
                
                while (input == null || input.trim().isEmpty()) {
                    System.out.println("Invalid input. Please try again.");
                    System.out.println(prompt);
                    input = scanner.nextLine();
                }
                return input;
            }
        }
        
        InputValidator validator = new InputValidator();
        
        String username = validator.getValidatedInput("Please enter your username:");
        String password = validator.getValidatedInput("Please enter your password:");
        String email = validator.getValidatedInput("Please enter your email:");
        
        // process the input (save to database, etc.)
    }
}
