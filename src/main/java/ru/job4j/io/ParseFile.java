package ru.job4j.io;

import java.io.*;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 1024)) {
            String output = "";
            int data;
            while ((data = bufferedInputStream.read()) != -1) {
                output += (char) data;
            }
            return output;
        }
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 1024)) {
            String output = "";
            int data;
            while ((data = bufferedInputStream.read()) != -1) {
                if (data < 0x80) {
                    output += (char) data;
                }
            }
            return output;
        }
    }

    public synchronized void saveContent(String content) throws IOException {
        OutputStream o = new FileOutputStream(file);
        for (int i = 0; i < content.length(); i += 1) {
            o.write(content.charAt(i));
        }
    }

    public static void main(String[] args) throws IOException {
        ParseFile parseFile = new ParseFile();
        parseFile.setFile(new File("pom_tmp.xml"));

        System.out.println(parseFile.getContent());
        System.out.println(parseFile.getContentWithoutUnicode());
    }
}
