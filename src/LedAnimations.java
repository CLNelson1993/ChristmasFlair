import com.diozero.ws281xj.LedDriverInterface;
import com.diozero.ws281xj.PixelAnimations;
import com.diozero.ws281xj.PixelColour;
import com.diozero.ws281xj.rpiws281x.WS281x;

public class LedAnimations {



    public static void main(String[] args) {
        int gpio = 18;
        int brightness = 255;
        int numpixels = 25;

        try (LedDriverInterface led = new WS281x(gpio, brightness, numpixels)) {
            ledRainbow(led);
            ledStrobe(led, 10); //set number of strobes to 10.
        //    PixelAnimations.demo(led); // not sure if I need this.
        } catch (Throwable t) {
            System.out.println("Error: " + t);
            t.printStackTrace();
        }
    }



    private static void ledRainbow(LedDriverInterface led) {
        System.out.println("Executing ledRainbow().");

        int[] colours = PixelColour.RAINBOW;

        for (int i = 0; i < 254; i++) {
            for (int pixel = 0; pixel < led.getNumPixels(); pixel++) {
                led.setPixelColour(pixel, colours[(i + pixel) % colours.length]);
            }

            led.render();
            PixelAnimations.delay(50);
        }
    }

    private static void ledStrobe(LedDriverInterface led, int times) {
        System.out.println("Executing ledStrobe().");

        for (int i = 0; i < times ; i++) {
            led.render();
            PixelAnimations.delay(100);
            led.allOff();
            PixelAnimations.delay(100);
        }
    }

}