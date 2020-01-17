package template;

/**
 * 继承实现模板模式
 * @author user
 *
 */
public class Test {
	
	public static void main(String[] args) {
		
		// 不同的模板  一
		MyJdbcTemplateByIn mt = new RoleDao();
		mt.execute();
		// 不同的模板  二
		MyJdbcTemplateByIn msgt = new MessageDao();
		msgt.execute();				
		
	}
	

}
