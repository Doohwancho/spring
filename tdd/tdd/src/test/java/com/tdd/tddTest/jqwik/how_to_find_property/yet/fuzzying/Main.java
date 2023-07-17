package com.tdd.tddTest.jqwik.how_to_find_property.yet.fuzzying;

public class Main {
  /*
    Code should never explode, even if you feed it with lots of diverse and unforeseen input data.
    Thus, the main idea is to generate the input, execute the function under test and check that:

    1. No exceptions occur, at least no unexpected ones
    2. No 5xx return codes in http requests, maybe you even require 2xx all the time
    3. All return values are valid
    4. Runtime is under an acceptable threshold
    5. Using the same input several times will produce the same outcome

    Fuzzying is often done in retrospect when you want to examine the robustness of existing code.
    It’s also more common in integrated testing since during micro or unit testing you can usually be more specific than just demanding “Don’t blow up!”.
   */

}
