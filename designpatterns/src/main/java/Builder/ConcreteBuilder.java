package Builder;

/**
 * 具体的建造者类 中封装和具体的商品，然后根据商品的需要进行设置部分进行建造
 * 为这个产品进行建造
 * @author user
 *
 */
public class ConcreteBuilder extends Builder{
	
	private Product product;
	
    public ConcreteBuilder() {
        product = new Product();
    }
    @Override
    void bulidA() {
        product.setBuildA("地基");
    }
    @Override
    void bulidB() {
        product.setBuildB("钢筋工程");
    }
    @Override
    void bulidC() {
        product.setBuildC("铺电线");
    }
    @Override
    void bulidD() {
        product.setBuildD("粉刷");
    }
    @Override
    Product getProduct() {
        return product;
    }

}
