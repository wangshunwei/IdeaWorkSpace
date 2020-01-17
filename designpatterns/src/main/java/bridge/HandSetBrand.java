package bridge;

public abstract class HandSetBrand {
	
	// 品牌的名称
	protected String name;
	protected HandSetSoft soft;
	// 初始化手机的软件

	
	public HandSetSoft getSoft() {
		return soft;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSoft(HandSetSoft soft) {
		this.soft = soft;
	}
	/*
	 * 运行软件,具体怎么运行由子类去实现
	 * 并且运行相应的软件
	 */
	public abstract void run();

}
