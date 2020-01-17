package Builder;

import java.awt.*;

public class PersonThinBuilder extends PersonBuilder {

    public PersonThinBuilder(Graphics g, Paint p) {
        super(g, p);
    }

    @Override
    public void builderHead() {
        System.out.println("创建头");
    }

    @Override
    public void builderBody() {
        System.out.println("创建身体");
    }

    @Override
    public void builderArmLeft() {
        System.out.println("创建左胳膊");
    }

    @Override
    public void builderLegLeft() {
        System.out.println("创建左腿");
    }

    @Override
    public void builderArmRight() {
        System.out.println("创建右胳膊");
    }

    @Override
    public void builderLegRight() {
        System.out.println("创建右腿");
    }
}
