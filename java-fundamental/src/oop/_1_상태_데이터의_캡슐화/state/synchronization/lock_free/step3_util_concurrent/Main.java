package oop._1_상태_데이터의_캡슐화.state.synchronization.lock_free.step3_util_concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {

  public static void main(String[] args) {
    ConcurrentMap<Integer, String> cMap = new ConcurrentHashMap<>();
    ConcurrentLinkedQueue<String> cQueue = new ConcurrentLinkedQueue<>();
    CopyOnWriteArrayList<Integer> cArrayList = new CopyOnWriteArrayList<>();
  }

}
