package visitor;

public class Main {
    public static void main(String[] args){
        Directory root = new Directory("root");
        Directory bin = new Directory("bin");
        Directory Lkt = new Directory("Lkt");
        
        File file1 = new File("file1");
        File file2 = new File("file2");
        File file3 = new File("file3");
        File file4 = new File("file4");

        root.add(file1);
        bin.add(file2);
        bin.add(file3);
        Lkt.add(file4);
        
        root.add(Lkt);
        root.add(bin);

        root.accept(new ViewVisitor());    //경로 출력. DFS
    }
}

/*

---
structure

- Element(interface)
	- Entry(abstract)
		- Directory
		- File

- Visitor(abstract)
	- View Visitor

- Main


---
what is this code?

파일 시스템 코드

- Element(interface)
	- Entry(abstract)
		- Directory
		- File
여기까지가 기존 시스템.
여기에 파일 트리 iterate 연산 하려고 덧 붙인게
 
- Visitor(abstract)
	- View Visitor


---
what is visitor pattern?

기존 시스템 구조 바꾸지 않으면서, 연산을 visitor에 분리하여 추가할 수 있는 디자인 패턴.


*/