package jdk_versions.jdk8.lambda.step1_why.step4_lambda로_strategy_pattern_간소화;

import jdk_versions.jdk8.lambda.step1_why.Product;

interface FilterPredicate {
  public abstract boolean filter(Product product);
}
