import com.diozero.ws281xj.LedDriverInterface;
import com.diozero.ws281xj.rpiws281x.WS281x;
    public class LEDstrip {
        public static void main(String[] args) {
            int pin = 23 //GPIO pin #
            int bright = 150 //Brightness (0-255)
            int pixel_num = 10 //# of lights. 10 lights should light up in this case.

            LedDriverInterface strip = new WS281x(pin,bright,pixel_num);
        }
    }
}