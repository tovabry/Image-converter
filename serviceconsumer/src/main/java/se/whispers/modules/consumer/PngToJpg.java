package se.whispers.modules.consumer;

import se.whispers.modules.service.imageconverter.ImageConverter;
import se.whispers.modules.service.imageconverter.ImageConverterException;

import java.util.ServiceLoader;

public class PngToJpg {

    public void convertPngToJpg(String inputFile, String outputFile) throws ImageConverterException {
        ImageConverter imageConverter;
        ServiceLoader<ImageConverter> imageConverters = ServiceLoader.load(ImageConverter.class);
        imageConverter = imageConverters.stream()
                .filter(service -> service.type().getName().equals("se.whispers.modules.serviceprovider.imageconverter.PngToJpgConverter"))
                .findFirst()
                .map(ServiceLoader.Provider::get)
                .orElseThrow(() -> new RuntimeException("No suitable converter found"));
        imageConverter.convert(inputFile, outputFile);
    }
}
