package _9_effective_java._3장_모든객체의_공통메서드.__10.clone.why_override_clone.step2;

public class 가변객체 {
    
    public int value;
    public String name;
    
    public 가변객체(int value, String name) {
        this.value = value;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "(value=" + value + ", name=" + name + ")";
    }
}
