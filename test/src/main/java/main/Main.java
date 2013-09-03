package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nixsolutions.dao.DaoImpl;
import com.nixsolutions.domain.Person;
import com.nixsolutions.domain.User;

public class Main {

	static List<String> request = Arrays.asList("qwe", "asd");

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// context
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		DaoImpl dao = (DaoImpl) context.getBean("dao");

		// ger All Persers
		System.out.println("findAllPersens()");
		List<Person> allPersens = dao.findAllPersons();
		for (Person user : allPersens) {
			System.out.println("persen --> " + user);
		}

		System.out.println("getPersonById(1)");
		Person person = dao.getPersonById(1L);
		System.out.println("persen --> " + person);
		
		System.out.println("getTeacherByIdAsPerson(1)");
		person = dao.getTeacherByIdAsPerson(2L);
		System.out.println("persen --> " + person);
		
		System.out.println("getWorkerByIdAsPerson(1)");
		person = dao.getWorkerByIdAsPerson(1L);
		System.out.println("persen --> " + person);
		

		// get ALL users
		System.out.println("findAll()");
		List<User> allUsers = dao.findAll();
		for (User user : allUsers) {
			System.out.println("user --> " + user);
		}

		// search
		System.out.println("findUsersBySearch()");
		List<User> users = dao.findUsersBySearch(request);
		for (User user : users) {
			System.out.println("user --> " + user);
		}

		System.out.println("end");
	}

}
