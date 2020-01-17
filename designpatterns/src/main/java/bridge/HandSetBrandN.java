package bridge;

public class HandSetBrandN extends HandSetBrand{

	/*
	 * N品牌的手机运行手机上的软件
	 * (non-Javadoc)
	 * @see designpatterns.bridge.HandSetBrand#run()
	 */
	@Override
	public void run() {
		soft.run(name);
		
	}
	
	

}
