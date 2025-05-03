package se.whispers.modules.consumer;

import se.whispers.modules.service.imageconverter.ImageConverterException;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class Main {
    static URL jpgUrl = Main.class.getClassLoader().getResource("sample.jpg");
    static URL pngUrl = Main.class.getClassLoader().getResource("sample.png");
    static URL outputJpg = Main.class.getClassLoader().getResource("output.jpg");
    static URL outputPng = Main.class.getClassLoader().getResource("output.png");
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ImageConverterException {

        printServiceLoaderInfo();

    }

    private static void printServiceLoaderInfo() throws ImageConverterException {
        while (true) {

            System.out.println("\n" + "Please select an option: " + "\n" +
                    "1. Convert JPG to PNG" + "\n" +
                    "2. Convert PNG to JPG" + "\n" +
                    "3. Convert both JPG and PNG" + "\n" +
                    "4. Exit" + "\n");

            int input = scanner.nextInt();

            if (input == 1) {
                runPngToJpg();
            } else if (input == 2) {
                runJpgToPng();
            } else if (input == 3) {
                runBoth();
            } else if (input == 4) {
                System.out.println("Exiting the program...");
                break;
            } else {
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    private static void runJpgToPng() throws ImageConverterException {

        if (outputPng != null) {
            System.out.println("Output JPG file found, deleting the file before making a new one");
            File outputJpgFile = new File(outputJpg.getPath());
            if (outputJpgFile.delete()) {
                System.out.println("Output JPG file deleted successfully");
            } else {
                System.out.println("Failed to delete output JPG file");
            }
        }

        System.out.println("JPG file found, converting the JPG file to PNG");
        JpgToPng jpgToPng = new JpgToPng();
        jpgToPng.convertJpgToPng(jpgUrl.getPath(), new File("serviceconsumer/src/main/resources/output.png").getAbsolutePath());
    }

    private static void runPngToJpg() throws ImageConverterException {
        if (outputJpg != null) {
            System.out.println("Output PNG file found, deleting the file before making a new one");
            File outputPngFile = new File(outputJpg.getPath());
            if (outputPngFile.delete()) {
                System.out.println("Output PNG file deleted successfully");
            } else {
                System.out.println("Failed to delete output PNG file");
            }
        }

        System.out.println("PNG file found, converting the PNG file to JPG");
        PngToJpg pngToJpg = new PngToJpg();
        pngToJpg.convertPngToJpg(pngUrl.getPath(), new File("serviceconsumer/src/main/resources/output.jpg").getAbsolutePath());
    }

    private static void runBoth() throws ImageConverterException {
        if ((outputJpg != null) && (outputPng != null)) {
            System.out.println("Output JPG and PNG files found, deleting the files before making new ones");
            File outputJpgFile = new File(outputJpg.getPath());
            File outputPngFile = new File(outputPng.getPath());
            if (outputJpgFile.delete() && outputPngFile.delete()) {
                System.out.println("Output JPG and PNG files deleted successfully");
            } else {
                System.out.println("Failed to delete output JPG and PNG files");
            }
        }

        System.out.println("Both JPG and PNG files found, converting the JPG file to PNG");
        JpgToPng jpgToPng = new JpgToPng();
        PngToJpg pngToJpg = new PngToJpg();
        jpgToPng.convertJpgToPng(jpgUrl.getPath(), new File("serviceconsumer/src/main/resources/output.png").getAbsolutePath());
        pngToJpg.convertPngToJpg(pngUrl.getPath(), new File("serviceconsumer/src/main/resources/output.jpg").getAbsolutePath());
    }

}
