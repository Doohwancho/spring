package _8_design_pattern.Behavioral.visitor;

public class File extends Entry {
    public File(String name) {
        super(name);
    }

    public void add(Entry entry) {

    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
