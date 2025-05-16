package se.whispers.modules.serviceprovider.imageconverter;
import se.whispers.modules.service.imageconverter.ImageConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class JpgToPngConverter implements ImageConverter{

    @Override
    public void convert(String inputFile, String outputFile) {

        try {
            System.out.println("Converting JPG to PNG: " + inputFile + " to " + outputFile);

            BufferedImage image = ImageIO.read(new File(inputFile));
            ImageIO.write(image, "png", new File(outputFile));

        }catch (Exception e) {
            System.err.println("Error converting JPG to PNG: " + e.getMessage());
        }

    }

    @Override
    public String getSourceFormat() {
        return "jpg";
    }

    @Override
    public String getTargetFormat() {
        return "png";
    }
}
