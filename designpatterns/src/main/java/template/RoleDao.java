package template;

/**
 * 具体的实现模板，需要不同其他实现模板做的事情   模板一   继承实现
 * @author user
 *
 */
public class RoleDao extends MyJdbcTemplateByIn{
	
	@Override
	public void run() {
		System.out.println("role add");
	}
 
	@Override
	public boolean isLog() {
		return false;
	}

}
