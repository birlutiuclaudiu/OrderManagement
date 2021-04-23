package connection;

import java.sql.*;


/**
 * Aceasa clasa reprezinta clasa va realiza conexiunea cu baza de date.
 * @author Claudiu-Andrei Birlutiu, CTI-ro 30226
 * @since 22 April 2021
 * href="http://theopentutorials.com/tutorials/java/jdbc/jdbc-mysql-create-database-example/"
 */
public class ConnectionFactory {
	/**
	 * Camp pentru driver-ul conexiunii
	 */
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	/**
	 * Local host-ul bazei de date. Aceasta are numele warehouse
	 */
	private static final String DBURL = "jdbc:mysql://localhost:3306/warehouse";
	/**
	 * Userul a cel default -root
	 */
	private static final String USER = "root";
	/**
	 * Parola pentru conectare
	 */
	private static final String PASSWORD = "Claudiutp";

	/**
	 * Instantarea obiectului de tip ConnectionFactory
	 */
	private static ConnectionFactory singleInstance = new ConnectionFactory();

	/**
	 * Construcor fara parametri
	 * Se va cere conexiunea la baza de date prin intermediul driver-ului
	 */
	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda prin care se cerea crearea conexiunii cu baza de date. Se va apela metoda statica din clasa
	 * DriverMandger getConnection cu parametrii referitorii la serverul bazei de date, user-ul si parola
	 * @return se va returna obiectul de tip conexiune in caz de succes
	 */
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 *
	 * @return va returna conexiunea cu baza de date
	 */
	public static Connection getConnection() {
		return singleInstance.createConnection();
	}

	/**
	 * Metoda ce inchide conexiunea cu baza de date
	 * @param connection va primi o instanta de conexiune
	 */
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metoda ce va inchide executia unei interogari SQL
	 * @param statement va primi o instanta de interogare
	 */
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metoda ce va inchide/sterge cursorul cu rezultatele interogarii
	 * @param resultSet va primi cursorul de rezultate
	 */
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
