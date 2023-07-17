package design_pattern.Behavioral.command;

public class Main {
	public static void main(String[] args) {
		SimpleRemoteControl remote = new SimpleRemoteControl();
		
		Light light = new Light();
		LightOnCommand lightOn = new LightOnCommand(light); //기능 바꿀 땐, command만 바꾸면 된다.
		
		GarageDoor garageDoor = new GarageDoor();
		GarageDoorOpenCommand garageOpen = 
		    new GarageDoorOpenCommand(garageDoor);
 
		
		remote.setCommand(lightOn); //리모콘에 LightOn 집어넣으면 on,off 가능. 다른 command 집어넣어도 on off 가
		remote.buttonWasPressed();
		
		remote.setCommand(garageOpen);
		remote.buttonWasPressed(); //사용자 입장에서 무지성 on off만 해도 되서 편하다. 
    }
	
}
