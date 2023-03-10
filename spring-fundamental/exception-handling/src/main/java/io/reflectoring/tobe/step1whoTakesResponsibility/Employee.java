package io.reflectoring.tobe.step1whoTakesResponsibility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Employee {
    public void makeReport() throws IOException {
        BufferedReader br = null;
        String input = null;
        br = new BufferedReader(new FileReader("input.txt"));
        input = br.readLine();
        System.out.println(input);
    }
}
