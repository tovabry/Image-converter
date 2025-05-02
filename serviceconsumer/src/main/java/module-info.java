module se.whispers.modules.serviceconsumer {
    requires se.whispers.modules.service;
    uses se.whispers.modules.service.imageconverter.ImageConverter;
}

//java --module-path ".\service\target\classes\;.\serviceconsumer\target\classes\;.\serviceprovider\target\classes\" -m se.whispers.modules.serviceconsumer/se.whispers.modules.consumer.Main