package unsafe;

class Player{
    private int age = 12;
    
    public Player(){		//Even if you create this constructor private;
        //You can initialize using Unsafe.allocateInstance()
        this.age = 50;
    }
    public int getAge(){
        return this.age;
    }
    public void setAge(int age){
        this.age = age;
    }
}
