package test;

public class CreateTables {

	public static StringBuilder TABLES_CREATE_SB = new StringBuilder();

	static {
		TABLES_CREATE_SB.append("CREATE TABLE role(");
		TABLES_CREATE_SB.append("id BIGINT PRIMARY KEY auto_increment ,");
		TABLES_CREATE_SB.append("name VARCHAR(50)");
		TABLES_CREATE_SB.append(") ;");

		TABLES_CREATE_SB.append("CREATE TABLE user (");
		TABLES_CREATE_SB.append("id BIGINT PRIMARY KEY auto_increment ,");
		TABLES_CREATE_SB.append("first_name VARCHAR(50) not null ,");
		TABLES_CREATE_SB.append("last_name VARCHAR(50) not null ,");
		TABLES_CREATE_SB.append("login VARCHAR(50) not null ,");
		TABLES_CREATE_SB.append("email VARCHAR(50) not null ,");
		TABLES_CREATE_SB.append("password VARCHAR(50) not null ,");
		TABLES_CREATE_SB.append("birth_date DATE not null ,");
		TABLES_CREATE_SB.append("role_id BIGINT ,");
		TABLES_CREATE_SB
				.append("constraint fk_user_1 foreign key (role_id) references role(id)");
		TABLES_CREATE_SB.append(") ;");

	}

	public static StringBuilder TABLES_DROP_SB = new StringBuilder();

	static {
		TABLES_DROP_SB.append("DROP TABLE user ;");
		TABLES_DROP_SB.append("DROP TABLE role ;");
	}

}
