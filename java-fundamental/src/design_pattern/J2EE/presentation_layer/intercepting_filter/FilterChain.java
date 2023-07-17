package design_pattern.J2EE.presentation_layer.intercepting_filter;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {
   private List<Filter> filters = new ArrayList<Filter>();
   private Target target;

   public void addFilter(Filter filter){
      filters.add(filter);
   }

   public void execute(String request){
      for (Filter filter : filters) { //이건 @PreFilter 여서 target.execute() 전에 위치한거고,
         filter.execute(request);
      }
      target.execute(request);
      
      //만약에 @PostFilter였으면 여기에서 filter.execute() loop 돌릴 듯 
   }

   public void setTarget(Target target){
      this.target = target;
   }
}
