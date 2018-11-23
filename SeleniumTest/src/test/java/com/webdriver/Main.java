package com.webdriver;

/**
 * Created by pc on 23.11.2018.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("ок");
        System.out.println(Main.class.getProtectionDomain().getCodeSource().getLocation());
        SeleniumTest stest = new SeleniumTest();
        stest.testSelenium();
    }
}
