package de.p3lina.application;

import java.util.Scanner;

public class ScannerStore {

    private static Scanner scanner;

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        ScannerStore.scanner = scanner;
    }
}
