import com.diozero.ws281xj.LedDriverInterface;
import com.diozero.ws281xj.PixelAnimations;
import com.diozero.ws281xj.PixelColour;
import com.diozero.ws281xj.rpiws281x.WS281x;

public class LedRainbow {
    public static void main(String[] args) {
        int gpio = 18;
        int brightness = 255;
        int numpixels = 25;

        try (LedDriverInterface led = new WS281x(gpio, brightness, numpixels)) {
            ledRainbow(led);
            // PixelAnimations.demo(led); // I can't find documentation on 'demo'. Is this part needed for the animation  to work?
        } catch (Throwable t) {
            System.out.println("Error: " + t);
            t.printStackTrace();
        }
    }
    private static void ledRainbow(LedDriverInterface led) {
        int[] colours = PixelColour.RAINBOW;

        for (int i = 0; i < 254; i++) {
            for (int pixel = 0; pixel < led.getNumPixels(); pixel++) {
                led.setPixelColour(pixel, colours[(i + pixel) % colours.length]);
            }

            led.render();
            PixelAnimations.delay(50);
        }
    }
}
