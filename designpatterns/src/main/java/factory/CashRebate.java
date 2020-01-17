package factory;
/**
 * 
 * 进行折扣收费
 * @author user
 *
 */
public class CashRebate  implements CashSuper{

	// 折扣率需要进行初始化
	private double moneyRebate = 1d;
	
	// 具体计算收费的方法
	@Override
	public double acceptCash(double money) {
		return money * moneyRebate;
	}
	// 初始化折扣率
	public  CashRebate(String moneyRebate) {
		this.moneyRebate = Double.parseDouble(moneyRebate);
	}

}
