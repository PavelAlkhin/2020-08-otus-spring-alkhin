package ru.otus.spring.service;

import java.util.Scanner;

public class ScannerService {

    private final static Scanner SCANNER = new Scanner(System.in);

    public String read(){
        return SCANNER.nextLine();
    }
}
