package com.nttdata.jdbc_t1.utilities;


/**
 * Class that manages all the information of the user interface
 * 
 * @author Fernando Pérez Nieto
 *
 */
public class InterfaceUtils {
	
	/** Database interface messages */
	public static final String ESTABLISHED_CONNECTIONDB_MSG = "Conexión establecida correctamente a ";
	public static final String ESTABLISHED_CONNECTION_MSG = "Conexión establecida correctamente";
	public static final String ERROR_CONNECTION_MSG = "Fallo al intentar conectar a la base de datos";
	public static final String ERROR_CLOSE_MSG = "Fallo intentando cerrar la conexión a la base de datos";
	public static final String NOT_DRIVER_FOUND = "No se encuentra el driver de conexión";
	public static final String DRIVER_FORMAT_ERROR = "El formato de driver no es válido";
	public static final String SQL_SENTENCE_ERROR = "La sequencia SQL no está bien declarada";
	
	/** User interface messages */
	public static final String WELCOME_MSG = "\nBienvenido al taller de JDBC. Autor: Fernando Pérez Nieto\n";
	public static final String DIVIDER = "\n=========================================================================\n";
	
	/**
	 * Utility class constructor
	 */
	private InterfaceUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Show welcome message
	 */
	public static void menu() {
		System.out.println(DIVIDER);
		System.out.println(WELCOME_MSG);
		System.out.println(DIVIDER);
	}
	
	/**
	 * Concat strings
	 * 
	 * @param args
	 * @return String
	 */
	public static String toStrBuilder(final String... args) {

		StringBuilder strb = new StringBuilder();
		for (String str : args) {
			strb.append(str);
		}
		return strb.toString();
	}
}
