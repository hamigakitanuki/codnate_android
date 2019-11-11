package com.example.codnate3;

import android.graphics.Bitmap;
import android.util.Xml;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

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
}
