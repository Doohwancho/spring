package com.tdd.tddTest.jqwik.how_to_find_property.business_rule_as_property;

import java.math.BigDecimal;
import java.util.Objects;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

public class DisCount정책테스트 {

  /*
    Q. 테스트 하고 싶은 business spec?

    For all customers with last year’s sales volume above 5000 EUR,
    we will provide an additional discount of 5 percent,
    if the invoice’s total amount is equal or above 500 EUR.


    A. 어떻게 복잡한 business logic을 테스트 하지?
       1. High volume customer discount is 5% for total above 500 EUR
       2. No discount for low volume customer or total below 500 EUR

       이 2개로 쪼개서 테스트 해
   */

  //1번 조건 테스트
  @Property
  @Label("High volume customer discount is 5% for total above 500 EUR")
  boolean highVolumeCustomerDiscount(@ForAll("eurosAbove5000") Euro lastYearVolume, @ForAll("eurosAbove5000") Euro invoiceTotal) {
    Assume.that(lastYearVolume.compareTo(Euro.amount(5000)) > 0);
    Assume.that(invoiceTotal.compareTo(Euro.amount(500)) >= 0);

    return new DiscountCalculator().discountInPercent(lastYearVolume, invoiceTotal).equals(new Percent(5));
  }


  //2번 조건 테스트
  @Property
  @Label("No discount for low volume customer or total below 500 EUR")
  boolean noDiscount(@ForAll("eurosBelow5000") Euro lastYearVolume, @ForAll("eurosBelow500") Euro invoiceTotal) {
    Assume.that(
        lastYearVolume.compareTo(Euro.amount(5000)) <= 0 ||
            invoiceTotal.compareTo(Euro.amount(500)) < 0
    );

    return new DiscountCalculator().discountInPercent(lastYearVolume, invoiceTotal)
        .equals(new Percent(0));
  }

  //각 조건에 맞는 파라미터 range를 조절
  @Provide
  Arbitrary<Euro> eurosAbove5000() {
    return Arbitraries.bigDecimals().between(BigDecimal.valueOf(5000), BigDecimal.valueOf(10000)).map(Euro::new);
  }

  //각 조건에 맞는 파라미터 range를 조절
  @Provide
  Arbitrary<Euro> eurosBelow5000() {
    return Arbitraries.bigDecimals().between(BigDecimal.valueOf(1), BigDecimal.valueOf(5000)).map(Euro::new);
  }

  //각 조건에 맞는 파라미터 range를 조절
  @Provide
  Arbitrary<Euro> eurosBelow500() {
    return Arbitraries.bigDecimals().between(BigDecimal.valueOf(1), BigDecimal.valueOf(499)).map(Euro::new);
  }


  static class Euro {
    private BigDecimal amount;

    public Euro(BigDecimal amount) {
      this.amount = amount;
    }

    public static Euro amount(int value) {
      return new Euro(BigDecimal.valueOf(value));
    }

    public int compareTo(Euro other) {
      return this.amount.compareTo(other.amount);
    }
  }


  class Percent {
    private int value;

    public Percent(int value) {
      this.value = value;
    }

//    @Override
//    public boolean equals(Object obj) {
//      if (this == obj) {
//        return true;
//      }
//      if (obj == null || getClass() != obj.getClass()) {
//        return false;
//      }
//      Percent percent = (Percent) obj;
//      return value == percent.value;
//    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null || getClass() != obj.getClass()) {
        return false;
      }
      Percent percent = (Percent) obj;
      return Objects.equals(value, percent.value);
    }


    @Override
    public int hashCode() {
      return Objects.hash(value);
    }
  }

  class DiscountCalculator {
    public Percent discountInPercent(Euro lastYearVolume, Euro invoiceTotal) {
      if (lastYearVolume.compareTo(Euro.amount(5000)) > 0 && invoiceTotal.compareTo(Euro.amount(500)) >= 0) {
        return new Percent(5);
      } else {
        return new Percent(0);
      }
    }
  }

}
