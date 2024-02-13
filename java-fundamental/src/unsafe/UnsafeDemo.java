package unsafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/*
Output:

0
45
50
 */

public class UnsafeDemo
{
    public static void main(String[] args) throws NoSuchFieldException, SecurityException,
        IllegalArgumentException, IllegalAccessException, InstantiationException
    {
        Field f = Unsafe.class.getDeclaredField("theUnsafe"); //Internal reference
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        
        //This creates an instance of player class without any initialization
        Player p = (Player) unsafe.allocateInstance(Player.class);
        System.out.println(p.getAge());		//Print 0
        
        p.setAge(45);						//Let's now set age 45 to un-initialized object
        System.out.println(p.getAge());		//Print 45
        
        System.out.println(new Player().getAge());	//This the normal way to get fully initialized object; Prints 50
    }
}
