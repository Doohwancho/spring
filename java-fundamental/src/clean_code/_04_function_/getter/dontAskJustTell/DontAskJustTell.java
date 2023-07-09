package clean_code._04_function_.getter.dontAskJustTell;

public class DontAskJustTell {

  public static void main(String[] args) {
    Distance distance = new Distance(100);

    //Don't
    int km1 = distance.getMeter() * 1000;

    //Do
    int km2 = distance.toKiloMeter();
  }

}
