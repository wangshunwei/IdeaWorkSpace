package proxy;

/**
 * 自己生成啦实现类和代理对象属于静态代理
 *
 * 通过JDK和 CGLIB在运行过程中生成代理对象 属于动态代理
 */
public class Buy implements BuyHouse {

    @Override
    public void buyHouse() {
        System.out.println("i am buy a big house");
    }

}
