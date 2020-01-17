package factory;

/**
 * 
 * 缺点 需要确定条件 以后新增条件还需要进行改动这个工厂类
 * 
 * @author user
 *
 */

public class CashFactory {

	// 参数书为输入的哪种类型
	public static CashSuper createCashAccept(String type) {
		
		CashSuper cs = null;
		switch(type){
			case "正常收费":
				cs = new CashNormal();
				break;
			case "满300返100":
				cs = new CashReturn("300", "100");
				break;
			case "打8折":
				cs = new CashRebate("0.8");
				break;
		}
		return cs;
	}
	
}
