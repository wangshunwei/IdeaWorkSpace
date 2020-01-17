package broker;

public class UnitedNationsSecurityCouncil extends UnitedNations{

    public USA colleague1;
    public Iraq colleague2;

    public Iraq getColleague2() {
        return colleague2;
    }

    public void setColleague2(Iraq colleague2) {
        this.colleague2 = colleague2;
    }

    public USA getColleague1() {
        return colleague1;
    }

    public void setColleague1(USA colleague1) {
        this.colleague1 = colleague1;
    }

    @Override
    public void Declare(String message, Country colleague) {
        if (colleague == colleague1) {
            colleague2.GetMessge(message);
        } else {
            colleague1.GetMessge(message);
        }
    }
}
