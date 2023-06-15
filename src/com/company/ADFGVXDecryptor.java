package com.company;

import java.util.Arrays;

public class ADFGVXDecryptor {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String ADFGVX = "ADFGVX";


    public static String decrypt(String key, String ciphertext) {
        int keywordLength = key.length();
        int ciphertextLength = ciphertext.length();
        int col = (int) Math.ceil((double) ciphertextLength / keywordLength);

        char[] keyArray = key.toCharArray();
        char[] ciphertextArray = ciphertext.toCharArray();

// Создание таблицы транспозиции
        char[][] transpositionTable = new char[keywordLength][col];
        int index = 0;

        for (int i = 0; i < keywordLength; i++) {
            for (int j = 0; j < col; j++) {
                if (index < ciphertextLength) {
                    transpositionTable[i][j] = ciphertextArray[index];
                    index++;
                }
            }
        }

// Создание отсортированного массива символов ключа
        char[] sortedKeyArray = keyArray.clone();
        Arrays.sort(sortedKeyArray);

// Расшифровка
        StringBuilder plaintextBuilder = new StringBuilder();
        for (char c : sortedKeyArray) {
            int colIndex = -1;
            for (int i = 0; i < keywordLength; i++) {
                if (keyArray[i] == c) {
                    colIndex = i;
                    break;
                }
            }

            if (colIndex != -1) {
                for (int i = 0; i < col; i++) {
                    char transpositionChar = transpositionTable[colIndex][i];
                    if (transpositionChar != '\0') {
                        plaintextBuilder.append(transpositionChar);
                    }
                }
            }
        }

        return plaintextBuilder.toString();
    }
}
