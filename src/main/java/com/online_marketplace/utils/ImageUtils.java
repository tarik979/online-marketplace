package com.online_marketplace.utils;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {
    public static byte[] compressImage(byte[] data){
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream output = new ByteArrayOutputStream(data.length);
        byte[] temp = new byte[4 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(temp);
            output.write(temp, 0, size);
        }

        try{
            output.close();
        }catch(Exception ex){

        }

        return output.toByteArray();
    }

    public static byte[] decompresser(byte[] data){
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream output = new ByteArrayOutputStream(data.length);
        byte[] temp = new byte[4 * 1024];

        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(temp);
                output.write(temp, 0, count);
            }
            output.close();
        } catch (Exception e) {
        }

        return output.toByteArray();
    }
}
