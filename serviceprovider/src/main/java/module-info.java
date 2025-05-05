module se.whispers.modules.serviceprovider {
    requires se.whispers.modules.service;
    requires java.desktop;
    provides se.whispers.modules.service.imageconverter.ImageConverter with
            se.whispers.modules.serviceprovider.imageconverter.PngToJpgConverter,
            se.whispers.modules.serviceprovider.imageconverter.JpgToPngConverter;
}