package Command_Idea;

public class LedSignReceiver implements LedReceiver {
    @Override
    public void rainbowCommand() {
        System.out.println("Unable to execute on LED Sign. Try using an LED Strip instead.");
    }
}