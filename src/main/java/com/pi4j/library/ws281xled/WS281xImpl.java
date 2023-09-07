package com.pi4j.library.ws281xled;

import com.pi4j.library.ws281xled.util.NativeLibraryLoader;
import com.pi4j.library.ws281xled.util.StringUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.nio.file.Path;
import java.nio.file.Paths;


public class WS281xImpl  implements WS281xIntrf {

    WS281xIntrf functionsNative = null;


    public int x = 42;
    public WS281xImpl(String fileName) throws  java.io.IOException {
    }

    public WS281xImpl() {
    }


    public static WS281xImpl newInstance(String fileName, String libName) throws IOException {
        WS281xImpl impl = new WS281xImpl();
        impl.initialize(fileName, libName);
        return(impl);
    }

    @Override
    //WS281xLED.ws2811_return_t
    public int ws2811_render_test(WS281xLED.ws2811_test ws2811) {
        int ret = jna_rc.x0;
        ret = this.functionsNative.ws2811_render_test(ws2811);

        return ret;
    }

    @Override
    public WS281xLED.ws2811_return_t ws2811_render(WS281xLED.ws2811_t ws2811) {
        return null;
    }


    public void initialize(String fileName, String libName) throws java.io.IOException {
        // the following allows my usage of the JNA code, rather than cloning my own version
        Path workingDirectory=Paths.get(".").toAbsolutePath();
        // get CPU architecture from system properties
        String osArch = System.getProperty("os.arch").toLowerCase();

        // sanitize CPU architecture string
        switch (osArch) {
            case "arm":
                osArch = "armhf";
                break;
            case "arm64":
                osArch = "aarch64";
                break;
            case "aarch64":
                break;
            default:
                throw new IllegalStateException("Pi4J has detected and UNKNOWN/UNSUPPORTED 'os.arch' : [" +
                    osArch + "]; only 'arm|armhf' and 'arm64|aarch64' are supported.");
        }
        String possibleJnaPath = workingDirectory+"/Pi4j_JNA/target/lib/" +osArch +"/pi4j-ws281xled/";
        // Also, handle the user relying upon "pi4j.library.path" to locate so's
        String pi4jLibpath = System.getProperty("pi4j.library.path");

        if(StringUtil.isNotNullOrEmpty(pi4jLibpath, true)){
            possibleJnaPath += ":"+pi4jLibpath;
        }
        System.setProperty("jna.library.path", possibleJnaPath);
        NativeLibraryLoader.load(fileName, libName);
        NativeLibrary lib = NativeLibrary.getInstance(fileName); //WS281xIntrf.getCLibName(LIBPROPFILE));
        WS281xIntrf INSTANCE = Native.load( WS281xIntrf.class);
        this.functionsNative = INSTANCE; //Native.loadLibrary(extractFile(fileName), String.valueOf(WS281xIntrf.class));
    }

    private String extractFile(final String fileName) throws com.pi4j.io.exception.IOException, java.io.IOException {
        final InputStream source = WS281xFuncsNative.class.getClassLoader().getResourceAsStream(fileName);
        final File file = File.createTempFile("lib", null);
        copyInputStreamToFile(source, file);
        return file.getAbsolutePath();
    }

    static final int DEFAULT_BUFFER_SIZE = 8192;

    private static void copyInputStreamToFile(InputStream inputStream, File file)
        throws com.pi4j.io.exception.IOException, java.io.IOException {
        // append = false
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }

    }

}