package com.pi4j.test;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.exception.LifecycleException;
import com.pi4j.library.ws281xled.WS281xFuncsNative;
import com.pi4j.library.ws281xled.WS281xImpl;
import com.pi4j.library.ws281xled.WS281xIntrf;
import com.pi4j.library.ws281xled.WS281xLED;
import com.pi4j.util.Console;
import sun.misc.Signal;
import sun.misc.SignalHandler;

public class WS281xTest {


    public static void main(String[] args) throws Exception {
        var console = new Console();
        Context pi4j = Pi4J.newAutoContext();

        console.title("<-- The Pi4J V2 Project Extension  -->", "NeoPixel94V");


        int pixels = 1;
        int duty = 0;
        int freq = 1;
        int duration = 0;
        boolean doTest = false;

        Signal.handle(new Signal("INT"), new SignalHandler() {
            public void handle(Signal sig) {
                System.out.println("Performing ctl-C shutdown");
                try {
                    pi4j.shutdown();
                } catch (LifecycleException e) {
                    e.printStackTrace();
                }
                // Thread.dumpStack();
                System.exit(2);
            }
        });

        for (int i = 0; i < args.length; i++) {
            String o = args[i];
            if (o.contentEquals("-duty")) {
                String a = args[i + 1];
                duty = Integer.parseInt(a.substring(0));
                i++;
            } else if (o.contentEquals("-freq")) {
                String a = args[i + 1];
                freq = Integer.parseInt(a.substring(0));
                i++;
            } else if (o.contentEquals("-duration")) {
                String a = args[i + 1];
                duration = Integer.parseInt(a.substring(0));
                i++;
            } else if (o.contentEquals("-test")) {
                doTest = true;
            } else {
                console.println("  !!! Invalid Parm " + o);
                console.println("  -duty, -freq,  - duration  -test");
                System.exit(42);
            }
        }

// next line not needed
     ////  WS281xFuncsNative foo = new WS281xFuncsNative("libpi4j-ws281xled.so");

       WS281xImpl functionsV1 = WS281xIntrf.createWS281xImpl();
       final WS281xFuncsNative functionsV2 = new WS281xFuncsNative("libpi4j-ws281xled.so");


        WS281xLED.ws2811_test dev_test = new WS281xLED.ws2811_test();
        dev_test.waldo = 5;

        int returnVal = functionsV1.ws2811_render_test(dev_test);
        console.println("Render_test v1  :  " + dev_test.waldo + "  retVal  : " +returnVal);



        WS281xLED.ws2811_t dev_t = new WS281xLED.ws2811_t();
        console.println("Render v1 :  " + functionsV1.ws2811_render(dev_t));
    }

}

