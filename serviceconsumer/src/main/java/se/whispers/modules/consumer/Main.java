package se.whispers.modules.consumer;

import se.whispers.modules.service.imageconverter.ImageConverter;
import se.whispers.modules.service.imageconverter.ImageConverterException;

import java.io.File;
import java.net.URL;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) throws ImageConverterException {

        URL jpgUrl = Main.class.getClassLoader().getResource("sample.jpg");
        URL pngUrl = Main.class.getClassLoader().getResource("sample.png");

        //pointing to a resource file and converting it to an absolute file system path
        if (jpgUrl == null) {
            System.err.println("JPG file not found, converting the PNG file to JPG");
            PngToJpg pngToJpg = new PngToJpg();
            pngToJpg.convertPngToJpg(pngUrl.getPath(), new File("serviceconsumer/src/main/resources/output.jpg").getAbsolutePath());
        } else if (pngUrl == null) {
            System.out.println("JPG file found, converting the JPG file to PNG");
            JpgToPng jpgToPng = new JpgToPng();
            jpgToPng.convertJpgToPng(jpgUrl.getPath(), new File("serviceconsumer/src/main/resources/output.png").getAbsolutePath());
        }
        else if (jpgUrl != null && pngUrl != null) {
            System.out.println("Both JPG and PNG files found, converting the JPG file to PNG");
            JpgToPng jpgToPng = new JpgToPng();
            PngToJpg pngToJpg = new PngToJpg();
            jpgToPng.convertJpgToPng(jpgUrl.getPath(), new File("serviceconsumer/src/main/resources/output.png").getAbsolutePath());
            pngToJpg.convertPngToJpg(pngUrl.getPath(), new File("serviceconsumer/src/main/resources/output.jpg").getAbsolutePath());
        }
        else {
            System.out.println("No suitable converter found");
        }

    }

}
