package Builder;

import java.awt.*;

public abstract  class PersonBuilder   {

    protected Graphics g;
    protected Paint p;

    public PersonBuilder(Graphics g, Paint p) {
        this.g = g;
        this.p = p;
    }

    public abstract void builderHead();
    public abstract void builderBody();
    public abstract void builderArmLeft();
    public abstract void builderLegLeft();
    public abstract void builderArmRight();
    public abstract void builderLegRight();


}
