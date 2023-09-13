//This is one of two files I will be making to better understand how the LED talks to the Pi.
//We are trying the following in this file:
//
//WS281x(int frequency,int dmaNum,int gpioNum,int brightness,int numPixels,StripType stripType)
//Parameters:
//frequency - Communication frequency, 800,000 or 400,000.
//dmaNum - DMA number.
//gpioNum - GPIO pin to use to drive the LEDs.
//brightness - Brightness level (0..255).
//numPixels - The number of pixels connected.
//stripType - Strip type
//This many parameters may be unnecessary. Hopefully we find out!

import com.diozero.ws281xj.LedDriverInterface;
import com.diozero.ws281xj.PixelAnimations;
import com.diozero.ws281xj.PixelColour;
import com.diozero.ws281xj.StripType;
import com.diozero.ws281xj.rpiws281x.WS281x;
public class LEDstrip_MoreParameters {
    public static void main(String[] args){
        int freq = 400000; //Communication frequency, 800,000 or 400,000.
        int dma = 5; //dma number. No clue what this is, default is 5.
        int pin = 18; //GPIO pin number. note to self: physical pin # is different from the GPIO #.
        int brightness = 150 ; //(0-255) brightness.
        int numpixels = 10 ; //10 lights being used.
        StripType strip = StripType.WS2811_RBG; //WS2811 RGB LED Strip.

        try (LedDriverInterface led = new WS281x(freq,dma,pin,brightness,numpixels,strip)) {
            rainbow(led);
        }
    }

    public static void rainbow(LedDriverInterface led){
        int[] colors = PixelColour.RAINBOW;

        for (int i=0; i<250; i++){
            for (int pixel=0; pixel<led.getNumPixels(); pixel++){
                led.setPixelColour(pixel, colors[(i+pixel) % colors.length]);
            }

            led.render();
            PixelAnimations.delay(50);
        }
    }
}