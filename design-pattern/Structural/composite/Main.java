package composite;


public class Main {
	
    public void compositionTest() {
        PersonalComputer pc = new PersonalComputer("PC");

        pc.add(new Processor(1000, "Intel"));
        pc.add(new Memory(500, "8GB"));
        pc.add(new HardDrive(800, "SSD"));
        
        System.out.println(pc.getPrice() == 2300);
    }
}

/*

Equipment에 tree형태로 딸려있던 class들을 Composite 인터페이스로 일괄관리.
iterate도 가능 

*/