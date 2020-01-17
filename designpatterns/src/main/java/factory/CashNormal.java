package factory;

public class CashNormal implements CashSuper{

	// 正常收费
	@Override
	public double acceptCash(double money) {
		return money;
	}
}
