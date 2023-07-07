package jdk_versions.jdk8.lambda.step1_why.step3_strategy_pattern;

import jdk_versions.jdk8.lambda.step1_why.Product;

interface FilterPredicate {
  public abstract boolean filter(Product product);
}
