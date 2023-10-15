package Command_Idea;

import com.diozero.ws281xj.LedDriverInterface;
import com.diozero.ws281xj.PixelAnimations;
import com.diozero.ws281xj.PixelColour;
import com.diozero.ws281xj.rpiws281x.WS281x;

public class LedWs281xReceiver implements LedReceiver {
    @Override
    public void rainbowCommand() {
        System.out.println("Executing rainbowCommand()");

        try (LedDriverInterface led_driver = new WS281x(18, 64, 100)) {
            rainbow(led_driver);
        } catch (Throwable t) {
            System.out.println("Error: " + t);
            t.printStackTrace();
        }
    }
    private static void rainbow(LedDriverInterface led) {
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