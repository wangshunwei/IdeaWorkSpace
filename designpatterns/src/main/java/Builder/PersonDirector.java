package Builder;

public class PersonDirector {

    private PersonBuilder pb;

    public PersonDirector(PersonBuilder pb) {
        this.pb = pb;
    }

    public void createPerson() {
        pb.builderArmLeft();
        pb.builderArmRight();
        pb.builderBody();
        pb.builderHead();
        pb.builderLegLeft();
        pb.builderLegRight();
    }
}
