package com.pi4j.library.ws281xled;

import com.pi4j.io.exception.IOException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


import com.pi4j.library.ws281xled.util.NativeLibraryLoader;


//import org.apache.commons.io.F


public class WS281xFuncsNative implements WS281xIntrf{
    private final WS281xIntrf functionsNative = null;
    static {
        // Load the platform library
       ;// NativeLibraryLoader.load("libpi4j-ws281xled.so", "pi4j-ws281xled");
    }

    static {
        // Load the platform library
        ;//NativeLibraryLoader.load("libpi4j-ws281xled.so", "libpi4j-ws281xled");
    }

    public static WS281xImpl newNativeInstance(String soName, String libName) throws java.io.IOException {
        WS281xImpl rval = new WS281xImpl(soName);
        rval.initialize(soName, libName);
        return(rval);
    }


    public WS281xFuncsNative(String fileName) throws  java.io.IOException {
     }

    public void initialize(String fileName) throws  java.io.IOException {
       // todo this.functionsNative = Native.loadLibrary(extractFile(fileName), WS281xIntrf.class);
    }


    @Override
    public int ws2811_render_test(WS281xLED.ws2811_test ws2811) {
        return jna_rc.x0;
    }

    public WS281xLED.ws2811_return_t ws2811_render(final WS281xLED.ws2811_t ws2811) {
        return functionsNative.ws2811_render(ws2811);
    }







}
