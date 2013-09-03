package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro.ini");
		
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject currentSubject = SecurityUtils.getSubject();
		Session session = currentSubject.getSession();
		
		if (!currentSubject.isAuthenticated()) {
			System.out.println("user isn't authenticated.");
			
			UsernamePasswordToken token = new UsernamePasswordToken("root", "secret");
			token.setRememberMe(true);
			
			currentSubject.login(token);
			
		}
		
		System.out.println("is aut: "+ currentSubject.isAuthenticated());
		System.out.println("role admin: "+currentSubject.hasRole("admin"));
		currentSubject.logout();
		System.out.println("is aut: "+ currentSubject.isAuthenticated());
		
		System.out.println("exit");
		System.exit(0);

	}

}
