import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        //create receiver (you only need one of these for all commands)
        Receiver receiver = new Receiver();


        //create new command
        Command cmdRainbow = new CMDRainbow(receiver);
        //create invoker to pass the command
        Invoker rainbowInvoker = new Invoker(cmdRainbow);

        //create new command
        Command cmdStatic = new CMDStatic(receiver);
        //create invoker to pass the command
        Invoker staticInvoker = new Invoker(cmdStatic);


        // Scanner for testing purposes
        // Create Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            //prompt
            System.out.println("Enter a number to choose an animation.");
            System.out.println("1. Static (set color and brightness)");
            System.out.println("2. Rainbow");
            System.out.println("0. Close program");

            //scan for integer
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
                case 0:
                    //close program
                    System.out.println("Closing program...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("That's not an option.");
            }
        }
    }
}
