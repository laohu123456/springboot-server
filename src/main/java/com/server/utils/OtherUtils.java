package com.server.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.UUID;

public class OtherUtils {

    public static boolean equalLongValue(long value1, long value2) {
        Long val1 = new Long(value1);
        Long val2 = new Long(value2);
        return val1.longValue() == val2.longValue();
    }

    public static boolean equalLongValue(Long value1, Long value2) {
        Long val1 = new Long(value1);
        Long val2 = new Long(value2);
        return val1.longValue() == val2.longValue();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static int countWordInFile(String filename, String word) {
        int counter = 0;
        try (FileReader fr = new FileReader(filename)) {
            try (BufferedReader br = new BufferedReader(fr)) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    int index = -1;
                    while (line.length() >= word.length() && (index = line.indexOf(word)) >= 0) {
                        counter++;
                        line = line.substring(index + word.length());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return counter;
    }

    public static byte[][] serializeArray(Object[] array) throws Exception {
        if(array == null){
            throw new Exception("Array is null");
        }
        int length = array.length;
        byte[][] bytes = new byte[length][];
        for (int i = 0; i < length; i++) {
            bytes[i] = SerializationUtils.serialize(array[i]);
        }
        return bytes;
    }
}
