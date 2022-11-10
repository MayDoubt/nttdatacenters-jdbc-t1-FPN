package com.nttdata.jdbc_t1.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.dbutils.DbUtils;

import com.nttdata.jdbc_t1.utilities.InterfaceUtils;


/**
 * 
 * Management class for database connections
 * 
 * @author Fernando Pérez Nieto
 *
 */
public class ConnectionManager {
	
	/** Logger */
	private static Logger log = LoggerFactory.getLogger(ConnectionManager.class);
	
	/** Connection data */
    private static String url = "jdbc:mysql://localhost:3306";    
    private static String driverName = "com.mysql.cj.jdbc.Driver";  
    private static String login = "root";   
    private static String password = "rootroot";
    private static String database = "nttdata_jdbc_ex";
    private static String cert = "encrypt=true;trustServerCertificate=true";
    private static Connection connectionDb; 
    
	/**
	 * Utility class constructor
	 */
    private ConnectionManager() {
    	throw new IllegalStateException("Utility class");
    }
    
    /**
     * Connect to database
     * 
     * @param database
     * @return Connection
     */
    public static Connection getConnection() {
    	
    	log.info("TRACE INIT");
        try {
        	Class.forName(driverName);
            setDriver(database);
        } catch (ClassNotFoundException ex) {
        	//Error finding the driver
        	log.error(InterfaceUtils.NOT_DRIVER_FOUND); 
            ex.printStackTrace();
        }
        log.info("TRACE END");
        return connectionDb;
        
    }

	/**
	 * Stablish driver between mysql or sqlserver
	 * 
	 * @param database
	 */
	private static void setDriver(String database) {
		log.info("TRACE INIT");
		try {
			if(driverName.contains("sqlserver")) {
				//Generate connection to BD sqlserver
				connectionDb = DriverManager.getConnection(InterfaceUtils.toStrBuilder(url, "/", database, ";", cert), login, password);
				if(log.isDebugEnabled() ) {
					log.debug(InterfaceUtils.toStrBuilder(InterfaceUtils.ESTABLISHED_CONNECTIONDB_MSG, database));
				}
				connectionDb.setAutoCommit(false);
			} else if (driverName.contains("mysql")) {
				//Generate connection to BD mysql
				connectionDb = DriverManager.getConnection(InterfaceUtils.toStrBuilder(url, "/", database), login, password);
				if(log.isDebugEnabled() ) {
					log.debug(InterfaceUtils.toStrBuilder(InterfaceUtils.ESTABLISHED_CONNECTIONDB_MSG, database));
				}
				connectionDb.setAutoCommit(false);
			} else {
				//If it's not Mysql or sqlserver it throws an error
				log.error(InterfaceUtils.DRIVER_FORMAT_ERROR);
			}
		} catch (SQLException ex) {
			//Error al conectar a la BD
		    log.error(InterfaceUtils.ERROR_CONNECTION_MSG); 
		    ex.printStackTrace();
		}
		
		log.info("TRACE END");
	}
	
	/**
	 * Close the connection
	 * 
	 * @return true if it closes the connection correctly, false otherwise
	 */
	public static boolean closeConnection(Connection con) {
		try	{
			DbUtils.closeQuietly(con); 
			return Boolean.TRUE;
		} catch (Exception e) {
			//Error trying to close the BD
			log.error(InterfaceUtils.ERROR_CLOSE_MSG); 
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
	
	/**
	 * @param database
	 * @return true if exist, false otherwise
	 */
	public static boolean existsDB (String database){
        boolean exists = false;
        Connection con = null;
        ResultSet rs = null;

        try {
        	
        	con = getConnection();
            if (con != null) {
            	//Access to metadata
                rs = con.getMetaData().getCatalogs();

                while(rs.next()) {
                	//Get all the database names
                    String catalogs = rs.getString(1);
                    //Check if exists BD
                    if(database.equalsIgnoreCase(catalogs)) {
                    	exists = true;
                        break;
                    } else {
                    	exists = false;
                    }
                }
            }
        } catch (Exception e) {
        	log.error(InterfaceUtils.ERROR_CONNECTION_MSG); 
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(con, null, rs);
        }
        return exists;
    }
}