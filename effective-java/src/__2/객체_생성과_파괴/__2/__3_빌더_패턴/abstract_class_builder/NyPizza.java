package __2.객체_생성과_파괴.__2.__3_빌더_패턴.abstract_class_builder;

import java.util.Objects;

public class NyPizza extends Pizza {
	
   private final Size size;

   public static class Builder extends Pizza.Builder<Builder> {
      private final Size size;

      public Builder(Size size) {
         this.size = Objects.requireNonNull(size);
      }

      @Override public NyPizza build() {
         return new NyPizza(this);
      }

      @Override protected Builder self() { return this; }
   }

   private NyPizza(Builder builder) {
      super(builder);
      size = builder.size;
   }
}
