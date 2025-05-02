import se.whispers.modules.service.imageconverter.ImageConverter;
import se.whispers.modules.serviceprovider.imageconverter.JpgToPngConverter;
import se.whispers.modules.serviceprovider.imageconverter.PngToJpgConverter;

module se.whispers.modules.serviceprovider {
    requires se.whispers.modules.service;
    requires java.desktop;
    provides ImageConverter with
            PngToJpgConverter,
            JpgToPngConverter;
}