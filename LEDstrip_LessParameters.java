import com.diozero.ws281xj.LedDriverInterface;
import com.diozero.ws281xj.PixelAnimations;
import com.diozero.ws281xj.PixelColour;
import com.diozero.ws281xj.rpiws281x.WS281x;

//This is one of two files I will be making to better understand how the LED talks to the Pi.
//We are trying the following in this file:
//
//public WS281x(int gpioNum,int brightness,int numPixels)
//Parameters:
//gpioNum - GPIO pin to use to drive the LEDs.
//brightness - Brightness level (0..255).
//numPixels - The number of pixels connected.
//This version of the file uses minimum possible parameters.
//This means every parameter we haven't set will use their defaults.
//Minimum params may be bad for us because this library was only tested on WS2812 by the author.
public class LEDstrip_LessParameters {
    public static void main(String[] args){
        int pin = 18;
        int brightness = 150;
        int pixelnum = 10;

        try (LedDriverInterface led = new WS281x(pin,brightness,pixelnum)){
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