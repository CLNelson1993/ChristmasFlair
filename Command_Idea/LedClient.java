package Command_Idea;

public class LedClient {
    public static void main(String[] args) {
        //Creating the receiver object
        LedReceiver ledReceiver = LedReceiverUtil.getLedDevice();
        //creating command and associating with receiver
        RainbowCommand rainbowCommand = new RainbowCommand(ledReceiver);
        //Creating invoker and associating with Command
        LedInvoker ledInvoker = new LedInvoker(rainbowCommand);
        //perform action on invoker object
        ledInvoker.execute();
    }
}