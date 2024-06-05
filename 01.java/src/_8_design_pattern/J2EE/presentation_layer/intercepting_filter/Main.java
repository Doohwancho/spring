package _8_design_pattern.J2EE.presentation_layer.intercepting_filter;

public class Main {
   public static void main(String[] args) {
      FilterManager filterManager = new FilterManager(new Target());
      filterManager.setFilter(new AuthenticationFilter());
      filterManager.setFilter(new DebugFilter());

      Client client = new Client();
      client.setFilterManager(filterManager);
      client.sendRequest("HOME");
   }
}

/*

---
structure

- Filter(interface)
	- DebugFilter
	- AuthenticationFilter

- FilterManager
- FilterChain
- Target

- Client


---
what is intercepting_filter?

FilterManager가 여러 Filter들을 FilterChain에 넣고,
Target이 등록되고 
Client가 실행하면,target.execute()하기 이전, 혹은 이후에 Filter Chain에 있던 필터 실행하는 구조.


*/