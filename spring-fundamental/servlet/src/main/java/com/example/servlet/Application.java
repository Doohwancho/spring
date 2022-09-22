package com.example.servlet;

public class Application {

    public static void main(String[] args) throws Exception {
        final var tomcatStarter = new TomcatStarter();
        tomcatStarter.start();
        tomcatStarter.await();
    }
}
