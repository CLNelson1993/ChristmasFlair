package Command_Idea;

public class RainbowCommand implements LedCommand {

    private LedReceiver led;

    public RainbowCommand(LedReceiver ledReceiver){
        this.led=led;
    }

    @Override
    public void execute() {
        //open command is forwarding request to openFile method
        this.led.rainbowCommand();
    }

}