package factory;

/**
 * 满多少返多少
 * 条件 满    返
 * @author user
 *
 */
public class CashReturn implements CashSuper {

	private double moneyCondition;
	private double moneyReturn;
	
	// 初始化条件 满多少 返多少
	public CashReturn(String moneyyCondition,String moneyReturn) {
		this.moneyReturn = Double.parseDouble(moneyReturn);
		this.moneyCondition = Double.parseDouble(moneyyCondition);
	}
	
	// 返现的收费方法进行实现
	@Override
	public double acceptCash(double money) {
		double result = money;
		if (result >= moneyCondition) {
			result = money - Math.floor(money / moneyCondition) * moneyReturn;
		}
		return result;
	}

	

}
