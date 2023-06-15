package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your message: ");
        String message = in.nextLine();
        System.out.println("Enter your key: ");
        String key = in.nextLine();
        String encryptMessage = encryptADGFVX(message, "1JR4HDE2AV9M8PINKZBYUF6T5GXS3OWLQ7C0", key);

    }

    public static String encryptADGFVX(String message, String keyphrase, String keyword) {
        message = message.toLowerCase(Locale.ROOT);
        keyword = keyword.toUpperCase(Locale.ROOT);
        char[][] poly = new char[6][6];
        char[] index = {'A', 'D', 'F', 'G', 'V', 'X'};
        int dist = 0;
        for (int i = 0; i < poly.length; i++) {
            for (int j = 0; j < poly[0].length; j++) {
                poly[i][j] = keyphrase.charAt(j + dist);
            }
            dist += 6;
        }

        System.out.println("Polybius square\n");
        for (char[] chars : poly) {
            for (int j = 0; j < poly[0].length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n\n");

        String interEncrypt = "";
        for (int i = 0; i < message.length(); i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    if (Character.toLowerCase(poly[j][k]) == message.charAt(i)) {
                        interEncrypt = interEncrypt + index[j] + index[k];
                        break;
                    }
                }
            }
        }
        System.out.println("Inter encrypted message " + interEncrypt);
        char[][] poly2 = new char[100][keyword.length()];
        for (int i = 0; i < poly2.length; i++) {
            for (int j = 0; j < keyword.length(); j++) {
                poly2[i][j] = '*';
            }
        }

        for (int i = 0; i < interEncrypt.length(); i++) {
            poly2[i / keyword.length()][i % keyword.length()] = interEncrypt.charAt(i);
        }
        System.out.println("\n\n");
        for (int i = 0; i < keyword.length(); i++) {
            System.out.print(keyword.charAt(i) + " ");
        }
        System.out.println("\n");
        for (int i = 0; i < 100; i++) {
            if (poly2[i][0] == '*') {
                break;
            }
            for (int j = 0; j < keyword.length(); j++) {
                System.out.print(poly2[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n\n");
        char[][] poly3 = new char[100][keyword.length()];
        int ascii = 65;
        dist = 0;
        while (ascii <= 90){
            char ch = (char) ascii;
            for (int i = 0; i < keyword.length(); i++) {
                if (ch == keyword.charAt(i)){
                    System.out.print(ch + " ");
                    for (int j = 0; j < 100; j++) {
                        poly3[j][dist] = poly2[j][i];
                    }
                    dist++;
                }
            }
            ascii++;
        }
        System.out.println("\n");
        for (int i = 0; i < 100; i++) {
            if (poly3[i][0] == '*') {
                break;
            }
            for (int j = 0; j < keyword.length(); j++) {
                System.out.print(poly3[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");

        String interEncrypt2 = "";
        for (int i = 0; i < keyword.length(); i++) {
            for (int j = 0; j < 100; j++) {
                if (poly3[j][i] != '*'){
                    interEncrypt2 += poly3[j][i];
                }
            }
        }
        System.out.println("Encrypted message " + interEncrypt2);
        return interEncrypt2;
    }
}

