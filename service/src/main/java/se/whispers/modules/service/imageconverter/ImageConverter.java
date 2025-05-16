package se.whispers.modules.service.imageconverter;

public interface ImageConverter {
    void convert(String inputFile, String outputFile) throws ImageConverterException;
    String getSourceFormat();
    String getTargetFormat();
}
