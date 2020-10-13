package ru.job4j.net;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileDownload {
    private String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
    private int downloadSpeed = 200;
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public FileDownload(String file, int downloadSpeed) {
        this.file = file;
        this.downloadSpeed = downloadSpeed;
    }

    public static void main(String[] args) throws Exception {
        if (args.length > 1) {
            FileDownload fileDownload = new FileDownload(args[0], parseInt(args[1]));
            fileDownload.downloadFile();
            ExecutorService pool = fileDownload.getPool();
            pool.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    fileDownload.downloadFile();
                    return "Success";
                }
            });
            pool.shutdown();
        } else {
            System.out.println("Please enter url and download speed limit");
        }
        System.out.println("Download completed");
    }

    private void downloadFile() throws InterruptedException {
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[downloadSpeed * 1024];
            float limit = (float) downloadSpeed * 1024 / 1000;
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, downloadSpeed * 1024)) != -1) {
                long start = System.nanoTime();
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long end = System.nanoTime();
                float speed = (float) bytesRead * 1000 / ((end - start));

                if (speed > limit) {
                    long sleepTime = (long) (speed * 100 / limit);
                    System.out.println("Pause: " + sleepTime + " ms");
                    TimeUnit.MILLISECONDS.sleep(sleepTime);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 100;
        }
    }

    private ExecutorService getPool() {
        return pool;
    }
}