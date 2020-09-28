package ru.otus.spring.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ScannerService {

    private final static Scanner SCANNER = new Scanner(System.in);

    public String read(){
        return SCANNER.nextLine();
    }
}
