package facade;

/**
 * 
 * 门面类
 * @author user
 *
 */
public class Facade {

	private SubSystemOne subSystemOne;
	private SubSystemTow subSystemTow;
	private SubSystemThree subSystemThree;
	public Facade() {
		this.subSystemOne = new SubSystemOne();
		this.subSystemTow = new SubSystemTow();
		this.subSystemThree = new SubSystemThree();
	}
	
	/*
	 * 定义不同方法，进行组合子系统方法，通过门面模式进行封装
	 */
	public void method() {
		System.out.println("调用所有的子系统的方法，通过门面模式");
		subSystemOne.methodOne();
		subSystemTow.methodTow();
		subSystemThree.methodThree();
	}
	
}
