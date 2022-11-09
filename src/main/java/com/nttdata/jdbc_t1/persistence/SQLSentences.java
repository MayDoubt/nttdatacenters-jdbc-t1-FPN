package com.nttdata.jdbc_t1.persistence;


/**
 * SQL sentences utility class
 * 
 * @author Fernando Pérez Nieto
 *
 */
public class SQLSentences {

	/**
	 * Utility class constructor
	 */
	private SQLSentences() {
	    throw new IllegalStateException("Utility class");
	}
	
	/** SQL */
	public static final String SELECT_PLAYERS = "SELECT sp.name AS playerName, st.name AS teamName, sp.first_rol AS rol1, sp.second_rol AS rol2, sp.birth_date AS birthD FROM nttdata_mysql_soccer_player sp JOIN nttdata_mysql_soccer_team st ON sp.id_soccer_team = st.id_soccer_team";
}
