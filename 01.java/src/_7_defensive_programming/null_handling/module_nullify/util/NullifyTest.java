package _7_defensive_programming.null_handling.module_nullify.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
  만든이: silentsoft
  license: Apache License 2.0
  reference: https://github.com/silentsoft/nullify/blob/main/src/test/java/org/silentsoft/nullify/NullifyTest.java
 */
public class NullifyTest {

  @Test
  public void ofTest() {
    Assertions.assertNull(Nullify.of(null));
    Assertions.assertNull(Nullify.of(""));
    Assertions.assertNull(Nullify.of((char) 0));
    Assertions.assertNull(Nullify.of((char) 0x00));
    Assertions.assertNull(Nullify.of('\u0000'));
    Assertions.assertNull(Nullify.of(new ArrayList<>()));
    Assertions.assertNull(Nullify.of((Iterable<String>) () -> Arrays.asList("").iterator()));
    Assertions.assertNull(Nullify.of(new HashSet<>()));
    Assertions.assertNull(Nullify.of(new HashMap<>()));
    Assertions.assertNull(Nullify.of(new Hashtable()));
    Assertions.assertNull(Nullify.of(new String[]{}));
    Assertions.assertNull(Nullify.of(new int[]{}));
    Assertions.assertNull(Nullify.of(new long[]{}));
    Assertions.assertNull(Nullify.of(new double[]{}));
    Assertions.assertNull(Nullify.of(new float[]{}));
    Assertions.assertNull(Nullify.of(new short[]{}));
    Assertions.assertNull(Nullify.of(new byte[]{}));
    Assertions.assertNull(Nullify.of(new boolean[]{}));
    Assertions.assertNull(Nullify.of(new char[]{}));

    Assertions.assertNotNull(Nullify.of(new Object()));
    Assertions.assertNotNull(Nullify.of(true));
    Assertions.assertNotNull(Nullify.of(0));
    Assertions.assertNotNull(Nullify.of(0x00));
    Assertions.assertNotNull(Nullify.of(Arrays.asList("value")));
    Assertions.assertNotNull(Nullify.of((Iterable<String>) () -> Arrays.asList("value").iterator()));
    Assertions.assertNotNull(Nullify.of(new int[]{0}));
    Assertions.assertNotNull(Nullify.of((long) 0x00));
    Assertions.assertNotNull(Nullify.of(new long[]{0}));
    Assertions.assertNotNull(Nullify.of((double) 0x00));
    Assertions.assertNotNull(Nullify.of(new double[]{0}));
    Assertions.assertNotNull(Nullify.of((float) 0x00));
    Assertions.assertNotNull(Nullify.of(new float[]{0}));
    Assertions.assertNotNull(Nullify.of((short) 0x00));
    Assertions.assertNotNull(Nullify.of(new short[]{0}));
    Assertions.assertNotNull(Nullify.of((byte) 0x00));
    Assertions.assertNotNull(Nullify.of(new byte[]{0}));
    Assertions.assertNotNull(Nullify.of(new boolean[]{false}));

    Assertions.assertEquals("value", Nullify.of("value", "default_value"));
    Assertions.assertEquals("default_value", Nullify.of(null, "default_value"));
  }

  @Test
  public void arrayTest() {
    String[] strings = new String[0];
    Assertions.assertNull(Nullify.of(strings));
    strings = new String[]{null};
    Assertions.assertNull(Nullify.of(strings));
    strings = new String[]{""};
    Assertions.assertNull(Nullify.of(strings));
    strings = new String[]{null, ""};
    Assertions.assertNull(Nullify.of(strings));
    strings = new String[]{"value"};
    Assertions.assertNotNull(Nullify.of(strings));
    strings = new String[]{null, "", "value"};
    Assertions.assertNotNull(Nullify.of(strings));

    char[] chars = new char[0];
    Assertions.assertNull(Nullify.of(chars));
    chars = new char[]{'\u0000'};
    Assertions.assertNull(Nullify.of(chars));
    chars = new char[]{'\u0000', 'a'};
    Assertions.assertNotNull(Nullify.of(chars));
    chars = new char[]{'a', '\u0000'};
    Assertions.assertNotNull(Nullify.of(chars));
  }

  @Test
  public void collectionTest() {
    List<String> list1 = new ArrayList<>();
    Assertions.assertNull(Nullify.of(list1));
    list1.add(null);
    Assertions.assertNull(Nullify.of(list1));
    list1.add("");
    Assertions.assertNull(Nullify.of(list1));
    list1.add("value");
    Assertions.assertNotNull(Nullify.of(list1));

    List<List<String>> list2 = new ArrayList<>();
    Assertions.assertNull(Nullify.of(list2));
    list2.add(null);
    Assertions.assertNull(Nullify.of(list2));
    list2.add(new ArrayList<>());
    Assertions.assertNull(Nullify.of(list2));
    list2.get(1).add("");
    Assertions.assertNull(Nullify.of(list2));
    list2.get(1).add("value");
    Assertions.assertNotNull(Nullify.of(list2));
  }

  @Test
  public void mapTest() {
    Map<String, String> map1 = new HashMap<>();
    Assertions.assertNull(Nullify.of(map1));
    map1.put("key", null);
    Assertions.assertNull(Nullify.of(map1));
    map1.put("key", "");
    Assertions.assertNull(Nullify.of(map1));
    map1.put("key", "value");
    Assertions.assertNotNull(Nullify.of(map1));

    Map<String, Map<String, String>> map2 = new HashMap<>();
    Assertions.assertNull(Nullify.of(map2));
    map2.put("key", new HashMap<>());
    Assertions.assertNull(Nullify.of(map2));
    map2.get("key").put("key", "");
    Assertions.assertNull(Nullify.of(map2));
    map2.get("key").put("key", "value");
    Assertions.assertNotNull(Nullify.of(map2));

    Map<String, Map<String, Map<String, String>>> map3 = new HashMap<>();
    Assertions.assertNull(Nullify.of(map3));
    map3.put("key", new HashMap<>());
    Assertions.assertNull(Nullify.of(map3));
    map3.get("key").put("key", new HashMap<>());
    Assertions.assertNull(Nullify.of(map3));
    map3.get("key").get("key").put("key", "");
    Assertions.assertNull(Nullify.of(map3));
    map3.get("key").get("key").put("key", "value");
    Assertions.assertNotNull(Nullify.of(map3));
  }

  @Test
  public void toStringTest() {
    Assertions.assertEquals("", Nullify.toString(null));
    Assertions.assertEquals("value", Nullify.toString("value"));
    Assertions.assertEquals("true", Nullify.toString(true));
    Assertions.assertEquals("false", Nullify.toString(false));
    Assertions.assertEquals("0", Nullify.toString(0));
    Assertions.assertEquals("1", Nullify.toString(1));

  }

  @Test
  public void isNullTest() {
    Assertions.assertTrue(Nullify.isNull(null));
    Assertions.assertTrue(Nullify.isNull(""));
    Assertions.assertTrue(Nullify.isNull('\u0000'));
    Assertions.assertFalse(Nullify.isNull(new Object()));
  }

  @Test
  public void isNotNullTest() {
    Assertions.assertTrue(Nullify.isNotNull(new Object()));
    Assertions.assertTrue(Nullify.isNotNull("value"));
    Assertions.assertTrue(Nullify.isNotNull('0'));
    Assertions.assertFalse(Nullify.isNotNull(null));
  }

}