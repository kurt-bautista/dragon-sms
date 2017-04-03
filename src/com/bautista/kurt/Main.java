package com.bautista.kurt;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        //TODO: Everything
        System.out.printf("ulels %s haha \n", 6.9);
        ScanResult results = new FastClasspathScanner("room").scan();
        List<String> allResults = results.getNamesOfAllStandardClasses();
        System.out.println(allResults);
    }
}
