package _1_syntax._1_by_jdk_versions.jdk8.lambda.step1_why.step3_strategy_pattern;

import _1_syntax._1_by_jdk_versions.jdk8.lambda.step1_why.Product;

interface FilterPredicate {
  public abstract boolean filter(Product product);
}
