
module com.pi4j.library.ws281xled {

    // SLF4J
    requires org.slf4j;

    // PI4J
    requires com.pi4j;

    requires com.pi4j.library.pigpio;

    // JNA
    requires jdk.unsupported;
    requires com.sun.jna;

    // EXPORTS
    exports com.pi4j.library.ws281xled;
    }
