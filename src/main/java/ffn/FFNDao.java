package ffn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FFNDao {
	//For this project I'm just going to embed a Derby database, as it's quick and simple
		//but still gets the idea across. The drivers and connection information are below
		private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
		private static final String DB_NAME="/database/FFDB";
		private static final String CONN_URL = "jdbc:derby:" + DB_NAME; 
		private static final String CREATE_URL = CONN_URL + ";create=true"; 


		/** Creates a Connection to the database. If the database does not exist, it creates 
		 * and initializes it first.*/
		protected Connection getConnection() {
			// TODO Auto-generated method stub
			System.out.println("Getting DB connection");
			Connection conn = null;

			try{
				Class.forName(DRIVER); 
			} catch(java.lang.ClassNotFoundException e) {
				e.printStackTrace();
			}

			try {
				conn = DriverManager.getConnection(CONN_URL); 

			}  catch (SQLException e)  {   
				//If the database or its tables do not exist for whatever reason, we should check for that and 
				//make them ourselves
				if (e.getMessage().contains("not found") || e.getMessage().contains("does not exist")){
					boolean creationSuccessful = FFNDao.createDatabase();
					if (creationSuccessful) return getConnection();
					else return conn;
				}else{
					e.printStackTrace();
				}
			} 
			return conn;
		}

		/**Creates and initializes a basic embedded Derby database.**/
		private static boolean createDatabase() {
			// TODO Auto-generated method stub
			System.out.println("DB is missing. Recreating at " + DB_NAME);
			boolean isSuccessful = false;

			try (Connection conn = DriverManager.getConnection(CREATE_URL); Statement st = conn.createStatement();){
				st.execute("CREATE TABLE DEPARTMENT ( "
						+ "DEPARTMENT_NO INTEGER CONSTRAINT DEPARTMENT_NO_PK PRIMARY KEY, "
						+ "DEPARTMENT_NAME VARCHAR(30))");
				st.execute("CREATE TABLE EMPLOYEE ( "
						+ "EMPLOYEE_NO INTEGER CONSTRAINT EMPLOYEE_NO_PK PRIMARY KEY, "
						+ "FIRST_NAME VARCHAR(30),"
						+ "LAST_NAME VARCHAR(30),"
						+ "DEPARTMENT_NO INTEGER CONSTRAINT DEPARTMENT_NO_FK REFERENCES DEPARTMENT "
						+ "ON DELETE SET NULL)");

				System.out.println("DB creation successful, populating sample data");
				
			    st.execute("INSERT INTO DEPARTMENT VALUES(1, 'Marketing')");
			    st.execute("INSERT INTO DEPARTMENT VALUES(2, 'R&D')");
			    st.execute("INSERT INTO EMPLOYEE VALUES(1, 'Alice','Smith', 1)");
			    st.execute("INSERT INTO EMPLOYEE VALUES(2, 'Bob','Johnson', 2)");
				
				isSuccessful = true;
				System.out.println("DB initialization successful. Resuming request.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			return isSuccessful;
		}



}
