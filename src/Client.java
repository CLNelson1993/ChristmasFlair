public class Client {
    public static void main(String[] args) throws InterruptedException {
        //create receiver (you only need one of these for all commands)
        Receiver receiver = new Receiver();

        //create new command
        Command concreteCommandMethod = new ConcreteCommand(receiver);
        //create invoker to pass the command.
        Invoker concreteInvoker = new Invoker(concreteCommandMethod); //create invoker to pass the command.

        //create new command
        Command cmdRainbow = new CMDRainbow(receiver);
        //create invoker to pass the command.
        Invoker rainbowInvoker = new Invoker(cmdRainbow);

        //execute concreteCommandMethod().
        concreteInvoker.execute();
        Thread.sleep(3000);
        //execute cmdRainbow().
        rainbowInvoker.execute();
    }
}