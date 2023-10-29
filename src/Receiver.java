import com.diozero.ws281xj.LedDriverInterface;
import com.diozero.ws281xj.PixelAnimations;
import com.diozero.ws281xj.PixelColour;
import com.diozero.ws281xj.rpiws281x.WS281x;
import java.util.Scanner;

public class Receiver {
    //Initialize LED strip
    LedDriverInterface ledDriver = new WS281x(18, 255, 100);

    //Close method (Turn off strip when users choose to close program)
    public void cmdClose() {
        System.out.println("Turning off LED and closing program...");
        ledDriver.allOff();
        ledDriver.close();
    }

    //Rainbow method
    public void cmdRainbow() {
        //set strip to default (in case brightness was changed using cmdStatic)
        ledDriver = new WS281x(18, 255, 100);
        //create an array of colors using PixelColour
        int[] colors = PixelColour.RAINBOW;

        System.out.println("Rendering cmdRainbow()...");


        //animation code
        while(true) {
            for (int i=0; i<250; i++) {
                for (int pixel=0; pixel<ledDriver.getNumPixels(); pixel++) {
                    ledDriver.setPixelColour(pixel, colors[(i+pixel) % colors.length]);
                }

                ledDriver.render();
                PixelAnimations.delay(50);
            }
        }
    }

    //Static method
    public void cmdStatic() {
        Scanner staticScanner = new Scanner(System.in);
        int rVal;
        int gVal;
        int bVal;
        int brightVal;

        //user prompt + scanner
        System.out.println("Enter a value for Red (0-255).");
        rVal = staticScanner.nextInt();
        System.out.println("Enter a value for Green (0-255).");
        gVal = staticScanner.nextInt();
        System.out.println("Enter a value for Blue (0-255).");
        bVal = staticScanner.nextInt();
        System.out.println("Enter a value for Brightness (0-255).");
        brightVal = staticScanner.nextInt();

        //change brightness by updating the driver
        ledDriver = new WS281x(18, brightVal, 100);

        System.out.println("Rendering cmdStatic() in RGB(" + rVal + ", " + gVal + ", " + bVal + ") and brightness(" + brightVal + ")...");

        //change all pixels to the assigned RGB values
        for (int i = 0; i < ledDriver.getNumPixels(); i++) {
            ledDriver.setPixelColourRGB(i, rVal, bVal, gVal);
        }

        ledDriver.render();
    }

    //RomSimpson's anims
    public void cmdWipe() {
        //set strip to default (in case brightness was changed using cmdStatic)
        ledDriver = new WS281x(18, 255, 100);

        for (int i=0; i<ledDriver.getNumPixels(); i++) {
            ledDriver.setPixelColourRGB(i, 0, 255, 0); //set as blue for now
            ledDriver.render();
            PixelAnimations.delay(50);
        }
    }

    public void cmdRainbowCycle() {
        //set strip to default (in case brightness was changed using cmdStatic)
        ledDriver = new WS281x(18, 255, 100);

        for (int j=0; j<256*5; j++) { // 5 cycles of all colors on wheel
            for (int i=0; i<ledDriver.getNumPixels(); i++) {
                ledDriver.setPixelColour(i, PixelColour.wheel(((i * 256 / ledDriver.getNumPixels()) + j) & 255));
            }
            ledDriver.render();
            PixelAnimations.delay(25);
        }
    }

    public void cmdStrobe() {
        //set strip to default (in case brightness was changed using cmdStatic)
        ledDriver = new WS281x(18, 255, 100);

        System.out.println("Executing cmdStrobe()");

        while (true) {
            for (int i = 0; i < ledDriver.getNumPixels(); i++) {
                ledDriver.setPixelColourRGB(i, 0, 255, 0); //blue for now.
            }
            ledDriver.render();
            PixelAnimations.delay(500);
            for (int i = 0; i < ledDriver.getNumPixels(); i++) {
                ledDriver.allOff();
            }
            PixelAnimations.delay(250);
        }
    }

}