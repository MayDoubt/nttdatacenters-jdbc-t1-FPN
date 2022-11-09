package com.nttdata.jdbc_t1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.jdbc_t1.persistence.ConnectionManager;
import com.nttdata.jdbc_t1.persistence.SQLSentences;
import com.nttdata.jdbc_t1.utilities.InterfaceUtils;

/**
 * NTTData - Taller JDBC 
 * 
 * @author Fernando Perez Nieto
 *
 */
public class App 
{
	
	/** Logger */
	private static Logger log = LoggerFactory.getLogger(App.class);
	
    /**
     * main class
     * 
     * @param args
     */
    public static void main( String[] args )
    {
    	log.info("TRACE INIT");
		//Connect to Database
		stablishDBConnection();
		log.info("TRACE END");
    }

	/**
	 * Stablish the database connection and make the query
	 */
	private static void stablishDBConnection() {
		
		log.info("TRACE INIT");
		
		//Show welcome message
		InterfaceUtils.menu();
		
		//Variable initialization
		Connection dbConnection = null;
		Statement sentence = null;
		ResultSet queryResult = null;
		StringBuilder playerInfo = new StringBuilder();
		
		//Connect to database
		dbConnection = ConnectionManager.getConnection();
		
		try {
			
			//Make the query
			sentence = dbConnection.createStatement();
			queryResult = sentence.executeQuery(SQLSentences.SELECT_PLAYERS);

			//Get all the fields with format
			while (queryResult.next()) {

				playerInfo.append("Nombre: ");
				playerInfo.append(queryResult.getString("playerName"));

				playerInfo.append(" | Equipo: ");
				playerInfo.append(queryResult.getString("teamName"));
			
				playerInfo.append(" | Demarcación: ");
				playerInfo.append(queryResult.getString("rol1"));

				playerInfo.append(" | Demarcación alternativa: ");
				playerInfo.append(queryResult.getString("rol2"));

				playerInfo.append(" | Fecha nacimiento: ");
				playerInfo.append(queryResult.getDate("birthD"));

				playerInfo.append("\n");
			}
	
		}  catch (SQLException sqle) {
			log.error(InterfaceUtils.SQL_SENTENCE_ERROR);
			sqle.printStackTrace();
		} finally {
			DbUtils.closeQuietly(dbConnection, sentence, queryResult);
		}
		System.out.println("Resultado de la consulta:\n");
		System.out.println(playerInfo.toString());
		log.info("TRACE END");
	}
}
