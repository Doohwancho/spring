package jdk_versions.jdk8.lambda.step1_why.step3_strategy_pattern;

import jdk_versions.jdk8.lambda.step1_why.Product;

class NameFilter implements FilterPredicate {
  private String[] mContents;

  public NameFilter(String... args) {
    mContents = args;
  }

  @Override
  public boolean filter(Product product) {

    if (product.getName().equals(mContents[0])) {
      return true;
    }

    return false;
  }
}