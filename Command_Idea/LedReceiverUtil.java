package Command_Idea;

public class LedReceiverUtil {

    public static LedReceiver getLedDevice(){
        String ledName = "WS281x"; //This is where detection would occur I think. Defaulted to WS281x for now.
        System.out.println("Device in use is:"+ledName);
        if(ledName.contains("WS281x")){
            return new LedWs281xReceiver();
        }else{
            return new LedSignReceiver();
        }
    }

}