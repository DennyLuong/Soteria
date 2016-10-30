import java.sql.*;

public class DatabaseManager {

		public DatabaseManager() {
	    	String url = "jdbc:mysql://ec2-54-172-30-46.compute-1.amazonaws.com:3306/";
	    	String userName = "root";
	    	String password = "5aRQKp2O";
	    	String dbName = "location";
	    	String driver = "com.mysql.jdbc.Driver";
	    	try {
				Connection connection= DriverManager.getConnection(url + dbName, userName, password);
				System.out.println("Connection successful.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    public float getLatitudeLatest(Connection connection) {
	    	Statement stmt = null;
		    String query = "select * from gps order by idgps DESC limit 1";
		    float latitude = 0;
		    try {
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query);
	    		latitude = rs.getFloat("latitude");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    		return latitude;
	    }

	    public boolean setValueA(Connection connection, float latValue, float longValue, float distfromb) {
	    	Statement stmt = null;
	    	String query = "INSERT INTO `location`.`gpsa` (`latitude`, `longitude`, `distfromb`) VALUES (" + latValue + ", " + longValue + "," + distfromb + ")";

	    	try {
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    	return true;
	    }

	    public boolean setValueB(Connection connection, float latValue, float longValue, float distfroma) {
	    	Statement stmt = null;
	    	String query = "INSERT INTO `location`.`gpsb` (`latitude`, `longitude`, `distfroma`) VALUES (" + latValue + ", " + longValue + "," + distfroma + ")";

	    	try {
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    	return true;
	    }

	    public float getLongitudeLatest(Connection connection) {
	    	Statement stmt = null;
		    String query = "select * from gps order by idgps DESC limit 1";
		    float longitude = 0;
		    try {
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query);
	    		longitude = rs.getFloat("latitude");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    		return longitude;
	    }

}
