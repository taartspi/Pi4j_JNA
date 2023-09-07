package com.pi4j.library.ws281xled;


import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.util.Console;
import com.sun.jna.*;
import com.sun.jna.Structure.*;


public class WS281xLED {

    Console console = new Console();
    Context pi4j =  Pi4J.newAutoContext();




    public final int RPI_HWVER_TYPE_UNKNOWN = 0;
    public final int RPI_HWVER_TYPE_PI1 = 1;
    public final int RPI_HWVER_TYPE_PI2 = 2;
    public final int RPI_HWVER_TYPE_PI4 = 3;

    public final int RPI_PWM_CHANNELS = 2;


    public static class ws2811_return_t extends Structure {
        public int returnEnumGood = 0;
        public int returnEnumFail = -1;
    }

    @FieldOrder({"waldo"})
    public static class ws2811_test extends Structure {
        public int waldo;
    }

    /*typedef struct ws2811_t
{
    uint64_t render_wait_time;                   //< time in µs before the next render can run
    struct ws2811_device *device;                //< Private data for driver use
    const rpi_hw_t *rpi_hw;                      //< RPI Hardware Information
    uint32_t freq;                               //< Required output frequency
    int dmanum;                                  //< DMA number _not_ already in use
    ws2811_channel_t channel[RPI_PWM_CHANNELS];
} ws2811_t;(/

 */
    @FieldOrder({"render_wait_time","device","rpi_hw","freq","dnanum","channel"})
    public static class ws2811_t extends Structure {
        public long render_wait_time;
        public ws2811_device_p device; // todo *
        public rpi_hw_t rpi_hw;   // todo *
        public int freq;
        public int dnanum;
        public ws2811_channel_t channel[]; //  TODO allocate ??? RPI_PWM_CHANNELS];
    }

    /*
    typedef struct {
    uint32_t type;
#define RPI_HWVER_TYPE_UNKNOWN                   0
#define RPI_HWVER_TYPE_PI1                       1
#define RPI_HWVER_TYPE_PI2                       2
#define RPI_HWVER_TYPE_PI4                       3
    uint32_t hwver;
    uint32_t periph_base;
    uint32_t videocore_base;
    char *desc;
} rpi_hw_t;
     */
    @FieldOrder({"type","hwver","periph_base","videocore_base","desc"})
    public static class rpi_hw_t extends Structure {
        public int type;
        public int hwver;
        public int periph_base;
        public int videocore_base;
        public String desc;
    };




    /*
    typedef struct ws2811_channel_t
{
    int gpionum;                                 //< GPIO Pin with PWM alternate function, 0 if unused
    int invert;                                  //< Invert output signal
    int count;                                   //< Number of LEDs, 0 if channel is unused
    int strip_type;                              //< Strip color layout -- one of WS2811_STRIP_xxx constants
    ws2811_led_t *leds;                          //< LED buffers, allocated by driver based on count
    uint8_t brightness;                          //< Brightness value between 0 and 255
    uint8_t wshift;                              //< White shift value
    uint8_t rshift;                              //< Red shift value
    uint8_t gshift;                              //< Green shift value
    uint8_t bshift;                              //< Blue shift value
    uint8_t *gamma;                                 //< Gamma correction table
} ws2811_channel_t;
     */
    @FieldOrder({"gpionum","invert","count","strip_type",
        "leds","brightness","wshift","rshift","gshift","bshift","gamma"})
    public static class ws2811_channel_t extends Structure {

        public int gpionum;                                 //< GPIO Pin with PWM alternate function, 0 if unused
        public int invert;                                  //< Invert output signal
        public int count;                                   //< Number of LEDs, 0 if channel is unused
        public int strip_type;                              //< Strip color layout -- one of WS2811_STRIP_xxx constants
        public int leds;                          //  TODO *   ws2811_led_t  typed to unot32 < LED buffers, allocated by driver based on count
        public byte brightness;                          //< Brightness value between 0 and 255
        public byte wshift;                              //< White shift value
        public byte rshift;                              //< Red shift value
        public byte gshift;                              //< Green shift value
        public byte bshift;                              //< Blue shift value
        public byte gamma;      // TODO  *

    }
/*typedef struct ws2811_device
{
    int driver_mode;
    volatile uint8_t *pxl_raw;
    volatile dma_t *dma;
    volatile pwm_t *pwm;
    volatile pcm_t *pcm;
    int spi_fd;
    volatile dma_cb_t *dma_cb;
    uint32_t dma_cb_addr;
    volatile gpio_t *gpio;
    volatile cm_clk_t *cm_clk;
    videocore_mbox_t mbox;
    int max_count;
} ws2811_device_t; */


    // pointer
    @FieldOrder({"p"})
    public static class ws2811_device_p extends Structure{
        public Pointer p;

/*            public ws2811_device_p(Pointer p) {
                // cannot use super(p) because of fixed-size array fields
                super();
                useMemory(p); // set pointer
                read(); // initialize fields
            }*/

            public ws2811_device_p() {
                super();
                // handle fixed-size array fields correctly
                ensureAllocated();
            }
    }





    }
