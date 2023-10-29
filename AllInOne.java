//This file serves to be the actual Client that is compiled on the Raspberry Pi.
//I can't figure out how to combine all my classes and interfaces so I'm just doing it manually.
//It sucks. It takes forever. But it sure does work. Rename to Client.java before compiling and use this command:
//
//sudo java -cp ".:/opt/diozero-distribution-1.3.5/*" Client.java

import com.diozero.ws281xj.LedDriverInterface;
import com.diozero.ws281xj.PixelAnimations;
import com.diozero.ws281xj.PixelColour;
import com.diozero.ws281xj.rpiws281x.WS281x;
import java.util.Scanner;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        //create receiver (you only need one of these for all commands)
        Receiver receiver = new Receiver();

        //create new command
        Command cmdClose = new CMDClose(receiver);
        //create invoker to pass the command
        Invoker closeInvoker = new Invoker(cmdClose);

        //create new command
        Command cmdRainbow = new CMDRainbow(receiver);
        //create invoker to pass the command
        Invoker rainbowInvoker = new Invoker(cmdRainbow);

        //create new command
        Command cmdStatic = new CMDStatic(receiver);
        //create invoker to pass the command
        Invoker staticInvoker = new Invoker(cmdStatic);

        //create new command
        Command cmdWipe = new CMDWipe(receiver);
        //create invoker to pass the command
        Invoker wipeInvoker = new Invoker(cmdWipe);

        //create new command
        Command cmdRainbowCycle = new CMDRainbowCycle(receiver);
        //create invoker to pass the command
        Invoker rainbowCycleInvoker = new Invoker(cmdRainbowCycle);

        //create new command
        Command cmdStrobe = new CMDStrobe(receiver);
        //create invoker to pass the command
        Invoker strobeInvoker = new Invoker(cmdStrobe);



        // Scanner for testing purposes. This probably will be useless later. Too bad!
        // Create Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            //prompt
            System.out.println("Enter a number to choose an animation.");
            System.out.println("1. Static (set color and brightness)");
            System.out.println("2. Rainbow");
            System.out.println("3. Color Wipe");
            System.out.println("4. Rainbow Cycle");
            System.out.println("5. Strobe");
            System.out.println("0. Close program");

            //scan for choice (integer)
            int userInput = scanner.nextInt();

            //choices
            switch (userInput) {
                case 1:
                    //execute cmdStatic()
                    staticInvoker.execute();
                    break;
                case 2:
                    //execute cmdRainbow()
                    rainbowInvoker.execute();
                    break;
                case 3:
                    wipeInvoker.execute();
                    break;
                case 4:
                    rainbowCycleInvoker.execute();
                    break;
                case 5:
                    strobeInvoker.execute();
                    break;
                case 0:
                    //execute cmdClose()
                    closeInvoker.execute();
                    scanner.close();
                    System.exit(0);
                default:
                    //invalid option
                    System.out.println("That's not an option.");
            }
        }
    }
}


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

public class Invoker {
    public Command command;

    public Invoker(Command c){
        this.command=c;
    }

    public void execute(){
        this.command.execute();
    }
}

public interface Command {
    void execute();
}

public class CMDWipe implements Command {
    private Receiver receiver;

    public CMDWipe(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.cmdWipe();
    }
}

public class CMDStatic implements Command {
    private Receiver receiver;

    public CMDStatic(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.cmdStatic();
    }
}

public class CMDRainbowCycle implements Command {
    private Receiver receiver;

    public CMDRainbowCycle(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.cmdRainbowCycle();
    }
}

public class CMDRainbow implements Command {
    private Receiver receiver;

    public CMDRainbow(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.cmdRainbow();
    }
}

public class CMDClose implements Command {
    private Receiver receiver;

    public CMDClose(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.cmdClose();
    }
}

public class CMDStrobe implements Command {
    private Receiver receiver;

    public CMDStrobe(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.cmdStrobe();
    }
}