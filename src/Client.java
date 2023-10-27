public class Client {
    public static void main(String[] args) {
        //create receiver (you only need one of these for all commands)
        Receiver receiver = new Receiver();


        //create new command
        Command cmdRainbow = new CMDRainbow(receiver);
        //create invoker to pass the command
        Invoker rainbowInvoker = new Invoker(cmdRainbow);


        //execute cmdRainbow().
        rainbowInvoker.execute();
    }
}