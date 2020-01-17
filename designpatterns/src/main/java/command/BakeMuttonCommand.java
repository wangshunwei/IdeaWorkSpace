package command;

public class BakeMuttonCommand extends Command{


    public BakeMuttonCommand(Barbecuer receiver) {
        super(receiver);
    }

    @Override
    public void ExcuteCommand() {
        receiver.bakeMutton();
    }
}
