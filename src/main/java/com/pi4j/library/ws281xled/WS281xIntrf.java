package com.pi4j.library.ws281xled;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.io.IOException;

public interface WS281xIntrf extends Library {

    static interface jna_rc{
        public static final int x0 = 0;
        public static final int x1 = 1;
        public static final int x2 = 2;
        public static final int x3 = 3;
        public static final int x4 = 4;
        public static final int x5 = 5;
        public static final int x6 = 6;
        public static final int x7 = 7;
        public static final int x9 = 9;
        public static final int x10 = 10;
        public static final int x11 = 11;
        public static final int x12 = 12;
        public static final int x13 = 13;
        public static final int x14 = 14;
        public static final int xCount = 16;
    }
   int ws2811_render_test(WS281xLED.ws2811_test ws2811);  // TODO *  WS281xLED.ws2811_return_t

    WS281xLED.ws2811_return_t ws2811_render(WS281xLED.ws2811_t ws2811);  // TODO *

    public static WS281xImpl newNativeInstance(String soName, String libName) throws java.io.IOException {
        WS281xImpl rval = new WS281xImpl(soName);
        rval.initialize(soName, libName);
        return(rval);
    }

    public static WS281xImpl createWS281xImpl() throws IOException {
        return(WS281xImpl.newInstance("libpi4j-ws281xled.so", "pi4j-ws281xled"));
    }

    }



