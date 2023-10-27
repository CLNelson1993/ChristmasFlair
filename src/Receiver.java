import com.diozero.ws281xj.LedDriverInterface;
import com.diozero.ws281xj.PixelAnimations;
import com.diozero.ws281xj.PixelColour;
import com.diozero.ws281xj.rpiws281x.WS281x;

public class Receiver {

    //initialize WS281x Strip. 18 = gpionum, 255 = brightness, 100 = pixelnum.
    private LedDriverInterface ledDriver = new WS281x(18, 255, 100); //should this be public, private, or nothing at all?


    //Rainbow method
    public void cmdRainbow() {
        //create an array of colors using PixelColour
        int[] colors = PixelColour.RAINBOW;

        //animation code
        for (int i=0; i<250; i++) {
            for (int pixel=0; pixel<ledDriver.getNumPixels(); pixel++) {
                ledDriver.setPixelColour(pixel, colors[(i+pixel) % colors.length]);
            }

            System.out.println("Rendering cmdRainbow()...");
            ledDriver.render();
            PixelAnimations.delay(50);
        }
    }

}