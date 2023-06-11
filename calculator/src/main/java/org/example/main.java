package org.example;

import com.sun.source.tree.IfTree;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().replace(" ", "");
        char operation;
        String[] inputNumbers;
        if (input.contains("+")) {
            operation = '+';
            inputNumbers = input.split("\\+");
        } else if (input.contains("-")) {
            operation = '-';
            inputNumbers = input.split("-");
        } else if (input.contains("*")) {
            operation = '*';
            inputNumbers = input.split("\\*");
        } else if (input.contains("/")) {
            operation = '/';
            inputNumbers = input.split("/");
        } else {
            throw new IllegalArgumentException("Invalid operation");
        }
        if (inputNumbers.length != 2) {
            throw new IllegalArgumentException("Invalid input");
        }
        boolean isRoman = isRoman(inputNumbers[0]) && isRoman(inputNumbers[1]);
        boolean isArabic = isArabic(inputNumbers[0]) && isArabic(inputNumbers[1]);
        if (!(isRoman || isArabic)) {
            throw new IllegalArgumentException("Invalid number format");
        }
        int a = isArabic ? Integer.parseInt(inputNumbers[0])
                : RomanNumeral.valueOf(inputNumbers[0]).getValue();
        int b = isArabic ? Integer.parseInt(inputNumbers[1])
                : RomanNumeral.valueOf(inputNumbers[1]).getValue();
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IllegalArgumentException("Number out of range");
        }
        int result = 0;
        switch (operation) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
        }
        if (isArabic) {
            System.out.println(result);
        } else {
            if (result < 1)
                throw new IllegalArgumentException("Invalid result for Roman number");
            System.out.println(RomanNumeral.of(result));
        }
    }
    private static boolean isRoman(String str) {
        try {
            RomanNumeral.valueOf(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    private static boolean isArabic(String str) {
        if (str == null || str.isEmpty()) return false;
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
enum RomanNumeral {
    I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10);

    private int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RomanNumeral of(int value) {
        for (RomanNumeral numeral : values()) {
            if (numeral.getValue() == value) {
                return numeral;
            }
        }
        throw new IllegalArgumentException("Invalid Roman numeral");
    }
}