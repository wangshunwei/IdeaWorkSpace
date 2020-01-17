package offer;

/**
 * 剑指offer单例最优解
 */
public class Test02 {

    /**
     * 懒汉式
     */
    public static class Singleton7 {

        private volatile static Singleton7 instance = null;
        
        private Singleton7() {

        }

        public static Singleton7 getInstance() {
            if (instance == null) {
                synchronized (Singleton7.class) {
                    if (instance == null) {
                        instance = new Singleton7();
                    }
                }
            }
            return instance;
        }

    }

    /**
     * 饿汉式  要想外部类访问内部类，直接在外部类中定义方法，创建内部类的对象，然后访问内部类
     */
    public static class Singleton {
        private final static Singleton INSTANCE = new Singleton();

        private Singleton() {

        }

        public static Singleton getInstance() {
            return INSTANCE;
        }

    }
    /**
     * 饿汉式 内部类的实现形式
     *
     */
    public static class Singleton5{

        private final static class SingletonHolder{
            // 内部类中获得这个对象
            private  final static  Singleton5 INSTANCE = new Singleton5();
        }

        private Singleton5() {

        }
        // 外部类中定义方法访问内部类的数据
        public static Singleton5 getInstance() {
            return SingletonHolder.INSTANCE;
        }
    }
}

