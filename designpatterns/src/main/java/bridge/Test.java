package bridge;

public class Test {

	public static void main(String[] args) {
		
		HandSetBrand ab;
		ab = new HandSetBrandN();
		ab.setName("M品牌手机");
		ab.setSoft(new HandSetGame());
		ab.run();// 运行游戏 可以将软件都放在list中，然后一起执行
		ab.setSoft(new HandSetAddressList());
		ab.run();
	}
	
}
