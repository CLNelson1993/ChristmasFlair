package Command_Idea;

public class LedInvoker {
    public LedCommand command;
    public LedInvoker(LedCommand c){

        this.command=c;
    }
    public void execute(){
        this.command.execute();
    }
}