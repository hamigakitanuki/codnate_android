package com.example.codnate3.net;

import android.graphics.Bitmap;
import android.util.Xml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Malt_part_post {
    private static final String ENTER_STRING = "\r\n";

    public void textPost(PrintStream printStream, String tag, String text, String boundary){
        printStream.print("--" + boundary);
        printStream.print(ENTER_STRING);
        printStream.print("Content-Disposition: form-data; name=\""+tag+"\"");
        printStream.print(ENTER_STRING);
        printStream.print("Content-Type:text/plain;charset="+ Xml.Encoding.UTF_8.name());
        printStream.print(ENTER_STRING);
        printStream.print(ENTER_STRING);
        printStream.print(text);
        printStream.print(ENTER_STRING);
        printStream.flush();

    }
    public void textPost(PrintStream printStream, String tag, int text, String boundary){
        printStream.print("--" + boundary);
        printStream.print(ENTER_STRING);
        printStream.print("Content-Disposition: form-data; name=\""+tag+"\"");
        printStream.print(ENTER_STRING);
        printStream.print("Content-Type:text/plain;charset="+ Xml.Encoding.UTF_8.name());
        printStream.print(ENTER_STRING);
        printStream.print(ENTER_STRING);
        printStream.print(text);
        printStream.print(ENTER_STRING);
        printStream.flush();
    }
    public void bitmapPost(PrintStream printStream,String tag,Bitmap bmp,String boundary)throws IOException {
        printStream.print("--" + boundary);
        printStream.print(ENTER_STRING);
        printStream.print("Content-Disposition: form-data; name=\"image\"; filename=\""+tag+".png\"");
        printStream.print(ENTER_STRING);
        printStream.print("Content-Type: " + "application/octet-stream");
        printStream.print(ENTER_STRING);
        printStream.print("Content-Transfer-Encoding: binary");
        printStream.print(ENTER_STRING);
        printStream.print(ENTER_STRING);
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, bos);
            printStream.write(bos.toByteArray());
        }
        finally {
            if (bos != null) {
                bos.close();
            }
        }
        printStream.print(ENTER_STRING);
        printStream.flush();
    }
}
