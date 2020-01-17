package strategy;

import java.util.ArrayList;
import java.util.List;

import designpatterns.factory.CashNormal;
import designpatterns.factory.CashRebate;
import designpatterns.factory.CashReturn;
import designpatterns.factory.CashSuper;

/**
 * 
 * 策略模式和工厂模式进行集合简化业务代码
 * @author user
 *
 */
public class StrategyAndFactory {

	CashSuper cashSuper = null;

	/**
	 * 初始化的时候进行创建不同的收费策略对象，根据客户端端的输入
	 * 可以加字段 type 1 2 3 代表三种策略 然后第二个参数实际的字符串进行获取到具体的参数数字
	 */
	public StrategyAndFactory(String type,String param) {
		
		switch(type){
			case "1":
				cashSuper = new CashNormal();
				break;
			case "2":
				cashSuper = new CashRebate(param);
			case "3":
				//处理参数 param
				String replaceAll = param.replaceAll("[^0-9]", ",");
				System.out.println(replaceAll);
			    List<String> ss = new ArrayList<String>();
			    for(String sss:param.replaceAll("[^0-9]", ",").split(",")){
			      if (sss.length()>0)
			        ss.add(sss);
			    }
			    // 提取两个参数
				String moneyyCondition = ss.get(0);
				String moneyReturn = ss.get(1);
				cashSuper = new CashReturn(moneyyCondition, moneyReturn);
		}
	}
	/**
	 * 直接返回结果在工厂类中相当于
	 * @param money
	 * @return
	 */
	public double getResult(double money) {
		return cashSuper.acceptCash(money);
	}
}
