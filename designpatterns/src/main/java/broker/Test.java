package broker;

public class Test {

    public static void main(String[] args) {

        UnitedNationsSecurityCouncil UNSC = new UnitedNationsSecurityCouncil();
        USA c1 = new USA(UNSC);
        Iraq c2 = new Iraq(UNSC);
        UNSC.colleague1 = c1;
        UNSC.colleague2 = c2;
        c1.Declare("不准研制核武器");
        c2.Declare("我们没有研制核武器");
    }
}
