package proxy;

public class BuyProxy implements BuyHouse {

    Buy buy = new Buy();

    @Override
    public void buyHouse() {
        seeHouse();
        buy.buyHouse();
        pay();
    }

    public void seeHouse(){
        System.out.println("to see the house...");
    }

    public void pay(){
        System.out.println("pay money");
    }

}
