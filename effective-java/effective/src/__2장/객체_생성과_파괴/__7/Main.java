package __2장.객체_생성과_파괴.__7;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class Main {
	public static void main(String[] args) {
		//case1) 캐시 메모리 해제 처리 using WeakHashMap
		Object key = new Object();
		Object value = new Object();

		Map<Object, Object> cache = new WeakHashMap<>(); //그냥 HashMap말고 WeakHashMap으로 만들면 안에 GC에 대상에 포함되어, 안에 object안쓰면 해제시킴.  
		cache.put(key, value);
		
		
		//case2) Weak reference 
		WeakReference weakWidget = new WeakReference(new String("widget"));
		
		//weak reference로 선언하면, new String("widget")에 strong reference가 없어지는 순간, gc가 메모리 해제해줌. 
	}
}
