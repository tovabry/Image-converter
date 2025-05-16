package se.whispers.modules.consumer;

import se.whispers.modules.service.imageconverter.ImageConverter;
import se.whispers.modules.service.imageconverter.ImageConverterException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.ServiceLoader;

public class Main {
    private static final String INPUT_DIR = "/app/input";
    private static final String OUTPUT_DIR = "/app/output";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ImageConverterException {
        new File(OUTPUT_DIR).mkdirs();
        showMenu();
    }

    private static void showMenu() throws ImageConverterException {
        List<ImageConverter> converters = loadConverters();

        while (true) {
            System.out.println("\nAvailable converters:");
            for (int i = 0; i < converters.size(); i++) {
                ImageConverter converter = converters.get(i);
                System.out.printf("%d. Convert %s to %s%n",
                        i + 1,
                        converter.getSourceFormat(),
                        converter.getTargetFormat());
            }
            System.out.printf("%d. Convert all%n", converters.size() + 1);
            System.out.printf("%d. Exit%n", converters.size() + 2);

            int input = scanner.nextInt();

            if (input >= 1 && input <= converters.size()) {
                convertFiles(converters.get(input - 1));
            } else if (input == converters.size() + 1) {
                convertAllFiles(converters);
            } else if (input == converters.size() + 2) {
                System.out.println("Exiting the program...");
                break;
            } else {
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    private static List<ImageConverter> loadConverters() {
        ServiceLoader<ImageConverter> loader = ServiceLoader.load(ImageConverter.class);
        List<ImageConverter> converters = new ArrayList<>();
        loader.forEach(converters::add);
        return converters;
    }

    private static void convertFiles(ImageConverter converter) throws ImageConverterException {
        File inputDir = new File(INPUT_DIR);
        File[] files = inputDir.listFiles((dir, name) ->
                name.toLowerCase().endsWith("." + converter.getSourceFormat().toLowerCase()));

        if (files == null || files.length == 0) {
            System.out.println("No " + converter.getSourceFormat() + " files found");
            return;
        }

        for (File file : files) {
            String outputName = file.getName().replace(
                    "." + converter.getSourceFormat().toLowerCase(),
                    "." + converter.getTargetFormat().toLowerCase());
            System.out.printf("Converting %s to %s%n",
                    file.getName(),
                    converter.getTargetFormat());
            converter.convert(
                    file.getAbsolutePath(),
                    new File(OUTPUT_DIR, outputName).getAbsolutePath()
            );
        }
    }

    private static void convertAllFiles(List<ImageConverter> converters) throws ImageConverterException {
        for (ImageConverter converter : converters) {
            convertFiles(converter);
        }
    }
}