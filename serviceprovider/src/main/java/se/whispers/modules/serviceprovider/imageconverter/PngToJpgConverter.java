package se.whispers.modules.serviceprovider.imageconverter;

import se.whispers.modules.service.imageconverter.ImageConverter;
import se.whispers.modules.service.imageconverter.ImageConverterException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class PngToJpgConverter implements ImageConverter {
    @Override
    public void convert(String inputFile, String outputFile) throws ImageConverterException {
        try{
        System.out.println("Converting PNG to JPG: " + inputFile + " to " + outputFile);

        BufferedImage image = ImageIO.read(new File(inputFile));
        ImageIO.write(image, "jpg", new File(outputFile));

        } catch (Exception e) {
            throw new ImageConverterException("Error converting PNG to JPG: ", e);
        }
    }

}
