package se.whispers.modules.consumer;

import se.whispers.modules.service.imageconverter.ImageConverterException;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static final String INPUT_DIR = "/app/input";
    private static final String OUTPUT_DIR = "/app/output";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ImageConverterException {
        // Create output directory if it doesn't exist
        new File(OUTPUT_DIR).mkdirs();
        printServiceLoaderInfo();
    }

    private static void printServiceLoaderInfo() throws ImageConverterException {
        while (true) {
            System.out.println("\nPlease select an option:\n" +
                    "1. Convert JPG to PNG\n" +
                    "2. Convert PNG to JPG\n" +
                    "3. Convert both JPG and PNG\n" +
                    "4. Exit\n");

            int input = scanner.nextInt();

            if (input == 1) {
                convertAllJpgToPng();
            } else if (input == 2) {
                convertAllPngToJpg();
            } else if (input == 3) {
                convertAllFiles();
            } else if (input == 4) {
                System.out.println("Exiting the program...");
                break;
            } else {
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    private static void convertAllJpgToPng() throws ImageConverterException {
        File inputDir = new File(INPUT_DIR);
        File[] jpgFiles = inputDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));

        if (jpgFiles == null || jpgFiles.length == 0) {
            System.out.println("No JPG files found in input directory");
            return;
        }

        JpgToPng converter = new JpgToPng();
        for (File jpg : jpgFiles) {
            String outputName = jpg.getName().replace(".jpg", ".png");
            System.out.println("Converting " + jpg.getName() + " to PNG");
            converter.convertJpgToPng(
                    jpg.getAbsolutePath(),
                    new File(OUTPUT_DIR, outputName).getAbsolutePath()
            );
        }
    }

    private static void convertAllPngToJpg() throws ImageConverterException {
        File inputDir = new File(INPUT_DIR);
        File[] pngFiles = inputDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));

        if (pngFiles == null || pngFiles.length == 0) {
            System.out.println("No PNG files found in input directory");
            return;
        }

        PngToJpg converter = new PngToJpg();
        for (File png : pngFiles) {
            String outputName = png.getName().replace(".png", ".jpg");
            System.out.println("Converting " + png.getName() + " to JPG");
            converter.convertPngToJpg(
                    png.getAbsolutePath(),
                    new File(OUTPUT_DIR, outputName).getAbsolutePath()
            );
        }
    }

    private static void convertAllFiles() throws ImageConverterException {
        convertAllJpgToPng();
        convertAllPngToJpg();
    }
}