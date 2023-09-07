package com.pi4j.test;
import com.pi4j.*;
import com.pi4j.io.pwm.*;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;
import com.pi4j.library.pigpio.*;

public class TestPwm {
    private static Pwm pwm = null;
    private static Context pi4j;

    public static void main(String[] args) throws Exception
    {
        pi4j = Pi4J.newAutoContext();
        initPiGpio();
        initGPIOCM4();
        pwm.on(50,1);
        while (true){
            // Timeout here?
        }
    }

    private static void initPiGpio()
    {
        var pigpio =  PiGpio.newNativeInstance();
        pigpio.gpioCfgClock(2, 1, 0);
        pigpio.initialize();
    }

    private static void initGPIOCM4()
    {
        var configPwm = Pwm.newConfigBuilder(pi4j)
            .address(13)
            .pwmType(PwmType.HARDWARE)
            .provider("pigpio-pwm")
            .initial(0)
            .shutdown(0)
            .build();
        try {
            pwm = pi4j.create(configPwm);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
