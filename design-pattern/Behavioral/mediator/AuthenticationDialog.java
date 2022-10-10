package mediator;

//facade 역할 
public class AuthenticationDialog implements Mediator {

	public String title;
	public Checkbox isJoinForm;
	
	public Textbox loginUserName;
	public Textbox loginPassword;
	
	public Textbox regUsername;
	public Textbox regPassword;
	public Textbox regEmail;
	
	public Button ok;
	public Button cancel;
	
	public Checkbox remeberMe;
	
	public AuthenticationDialog() { //factory, 
		
		super();
		
		title = "Login";
		isJoinForm = new Checkbox(this);
				
		loginUserName = new Textbox(this);
		loginPassword = new Textbox(this);
		
		regUsername = new Textbox(this);
		regPassword = new Textbox(this);
		regEmail = new Textbox(this);
		
		ok = new Button(this);
		cancel = new Button(this);
		
		remeberMe = new Checkbox(this);
	}

	public void printCurrentStatus() {
		System.out.println("Title: " + title);
		System.out.println("isJoinForm: " + isJoinForm.checked());
		System.out.println("remeberMe: " + remeberMe.checked());
	}

	@Override
	public void notify(Component sender, String event) { //이곳에서 컴포넌트 일괄 제어 

		if(sender.equals(ok) && "click".equals(event)) {
			
			if(isJoinForm.checked()) {
				System.out.println("Validate [regUsername, regPassword, regEmail] elements");
				System.out.println("And user join this system.");
			}
			else {
				System.out.println("Login!");
			}
		}
		else if(sender.equals(isJoinForm) && "check".equals(event)) {
			
			if(isJoinForm.checked()) {
				title = "User Join Form";
				System.out.println("show [regUsername, regPassword, regEmail] elements");
			}
			else {
				title = "Login";
				System.out.println("hide [regUsername, regPassword, regEmail] elements");
			}
		}
		else if(sender.equals(remeberMe) && "check".equals(event)) {
			
			if(remeberMe.checked()) {
				System.out.println("set ID in cookie");
			}
			else {
				System.out.println("remove ID from cookie");
			}
		}
	}
}
