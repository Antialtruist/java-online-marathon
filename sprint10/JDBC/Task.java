package sprint10.JDBC;

//	Develop a database with tables Roles, Employee, Directions and Projects.
//	The Roles table contains the id (primary key), roleName.
//	The Directions table contains the id (primary key), directionName.
//	The Projects table contains id (primary key), projectName, directionId (foreign key).
//	The Employee table contains id (primary key), firstName, roleId (foreign key), projectId (foreign key).
//	
//	Create next methods of the MyUtils class.
//	public Connection createConnection() throws SQLException
//	public void closeConnection() throws SQLException
//	public Statement createStatement() throws SQLException
//	public void closeStatement() throws SQLException
//	public void createSchema(String schemaName) throws SQLException
//	public void dropSchema() throws SQLException
//	public void useSchema() throws SQLException
//	
//	Methods for creating tables
//	public void createTableRoles() throws SQLException
//	public void createTableDirections() throws SQLException
//	public void createTableProjects() throws SQLException
//	public void createTableEmployee() throws SQLException
//	public void dropTable(String tableName) throws SQLException
//	
//	Methods for insert data to tables
//	public void insertTableRoles(String roleName) throws SQLException
//	public void insertTableDirections(String directionName) throws SQLException
//	public void insertTableProjects(String projectName, String directionName) throws SQLException
//	public void insertTableEmployee(String firstName, String roleName, String projectName) throws SQLException
//	
//	Methods to obtaining data from tables
//	public int getRoleId(String roleName) throws SQLException
//	public int getDirectionId(String directionName) throws SQLException
//	public int getProjectId(String projectName) throws SQLException
//	public int getEmployeeId(String firstName) throws SQLException
//	public List<String> getAllRoles() throws SQLException
//	public List<String> getAllDirestion() throws SQLException
//	public List<String> getAllProjects() throws SQLException
//	public List<String> getAllEmployee() throws SQLException
//	public List<String> getAllDevelopers() throws SQLException
//	public List<String> getAllJavaProjects() throws SQLException
//	public List<String> getAllJavaDevelopers() throws SQLException

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyUtils {
	private Connection connection;
	private Statement statement;
	private String schemaName;

    public Connection createConnection() throws SQLException {
    	DriverManager.registerDriver(new org.h2.Driver());
		connection = DriverManager.getConnection("jdbc:h2:mem:test", "", "");
		return connection;
    }
    
    public void closeConnection() throws SQLException {
    	connection.close();
    }
    
    public Statement createStatement() throws SQLException {
    	statement = connection.createStatement();
    	return statement;
    }
    
    public void closeStatement() throws SQLException {
    	statement.close();
    }
    
    public void createSchema(String schemaName) throws SQLException {
    	this.schemaName = schemaName;
    	statement.execute("CREATE SCHEMA " + schemaName + ";");
    }
    
    public void dropSchema() throws SQLException {
    	statement.execute("DROP SCHEMA " + schemaName + ";");
    }
    
    public void useSchema() throws SQLException {
    	statement.execute("USE " + schemaName + ";");
    }
    
    public void createTableRoles() throws SQLException {
    	statement.execute("CREATE TABLE Roles ("
    			+ "id INT NOT NULL AUTO_INCREMENT, "
    			+ "roleName VARCHAR(20) NOT NULL, "
    			+ "PRIMARY KEY (id));");
    }
    
    public void createTableDirections() throws SQLException {
    	statement.execute("CREATE TABLE Directions ("
    			+ "id INT NOT NULL AUTO_INCREMENT, "
    			+ "directionName VARCHAR(20) NOT NULL, "
    			+ "PRIMARY KEY (id));");
    }
    
    public void createTableProjects() throws SQLException {
    	statement.execute("CREATE TABLE Projects ("
    			+ "id INT NOT NULL AUTO_INCREMENT, "
    			+ "projectName VARCHAR(20) NOT NULL, "
    			+ "directionId INT NOT NULL, "
    			+ "PRIMARY KEY (id), "
    			+ "FOREIGN KEY (directionId) REFERENCES Directions(id));");
    }
    
    public void createTableEmployee() throws SQLException {
    	statement.execute("CREATE TABLE Employee (" 
    			+ "id INT NOT NULL AUTO_INCREMENT, "
    			+ "firstName VARCHAR(20) NOT NULL, "
    			+ "roleId INT NOT NULL, "
    			+ "projectId INT NOT NULL, "
    			+ "PRIMARY KEY (id), "
				+ "FOREIGN KEY (roleId) REFERENCES Roles (id), "
    			+ "FOREIGN KEY (projectId) REFERENCES Projects(id));");
    }
    
    public void dropTable(String tableName) throws SQLException {
    	statement.execute("DROP TABLE " + tableName + ";");
    }
    
    public void insertTableRoles(String roleName) throws SQLException {
    	statement.executeUpdate("INSERT INTO Roles (roleName) VALUES ('" + roleName + "');");
    }
    
    public void insertTableDirections(String directionName) throws SQLException {
    	statement.executeUpdate("INSERT INTO Directions (directionName) VALUES ('" + directionName + "');");
    }
    
    public void insertTableProjects(String projectName, String directionName) throws SQLException {
    	 int directionId = getDirectionId(directionName);
         statement.executeUpdate("INSERT INTO Projects (projectName, directionId) " + "VALUES('" + projectName + "', " + directionId + ");");
    }
    
    public void insertTableEmployee(String firstName, String roleName, String projectName) throws SQLException {
    	int roleId = getRoleId(roleName);
        int projectId = getProjectId(projectName);
        statement.executeUpdate("INSERT INTO Employee (firstName, roleId, projectId) " + "VALUES('" + firstName + "', " + roleId + ", " + projectId + ");");
    }
    
	public int getRoleId(String roleName) throws SQLException {
		ResultSet resultSet = statement.executeQuery("SELECT id FROM Roles WHERE roleName='" + roleName + "';");
		int result = -1;
		if (resultSet.next()) {
			result = resultSet.getInt(1);
		}
		resultSet.close();
		return result;
	}
    
    public int getDirectionId(String directionName) throws SQLException {
    	ResultSet resultSet = statement.executeQuery("SELECT id FROM Directions WHERE directionName='" + directionName + "';");
        int result = -1;
        if (resultSet.next()) {
            result = resultSet.getInt(1);
        }
        resultSet.close();
        return result;
    }
    
    public int getProjectId(String projectName) throws SQLException {
    	ResultSet resultSet = statement.executeQuery("SELECT id FROM Projects WHERE projectName='" + projectName + "';");
        int result = -1;
        if (resultSet.next()) {
            result = resultSet.getInt(1);
        }
        resultSet.close();
        return result;
    }
    
    public int getEmployeeId(String firstName) throws SQLException {
    	 ResultSet resultSet = statement.executeQuery("SELECT id FROM Employee WHERE firstName='" + firstName + "';");
         int result = -1;
         if (resultSet.next()) {
             result = resultSet.getInt(1);
         }
         resultSet.close();
         return result;
    }
    
    public List<String> getAllRoles() throws SQLException {
		List<String> roles = new ArrayList<>();
		ResultSet resultSet = statement.executeQuery("SELECT roleName FROM Roles;");
		while (resultSet.next()) {
			roles.add(resultSet.getString(1));
		}
		resultSet.close();
		return roles;
    }
    
    public List<String> getAllDirestion() throws SQLException {
    	List<String> roles = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT directionName FROM Directions;");
        while (resultSet.next()) {
            roles.add(resultSet.getString(1));
        }
        resultSet.close();
        return roles;
    }
    
    public List<String> getAllProjects() throws SQLException {
    	List<String> projects = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT projectName FROM Projects;");
        while (resultSet.next()) {
            projects.add(resultSet.getString(1));
        }
        resultSet.close();
        return projects;
    }
    
    public List<String> getAllEmployee() throws SQLException {
    	List<String> employees = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT firstName FROM Employee;");
        while (resultSet.next()) {
            employees.add(resultSet.getString(1));
        }
        resultSet.close();
        return employees;
    }
    
    public List<String> getAllDevelopers() throws SQLException {
    	 int developerId = getRoleId("Developer");
         List<String> developers = new ArrayList<>();
         ResultSet resultSet = statement.executeQuery("SELECT firstName FROM Employee WHERE roleId=" + developerId + ";");
         while (resultSet.next()) {
             developers.add(resultSet.getString(1));
         }
         resultSet.close();
         return developers;
    }
    
    public List<String> getAllJavaProjects() throws SQLException {
    	int directionId = getDirectionId("Java");
        List<String> projects = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT projectName FROM Projects WHERE directionId=" + directionId + ";");
        while (resultSet.next()) {
            projects.add(resultSet.getString(1));
        }
        resultSet.close();
        return projects;
    }
    
    public List<String> getAllJavaDevelopers() throws SQLException {
    	 int developerId = getRoleId("Developer");
         List<String> javaProjects = getAllJavaProjects();
         List<String> javaDevelopers = new ArrayList<>();
         for(String project : javaProjects) {
             int projectId = getProjectId(project);
             ResultSet resultSet = statement.executeQuery("SELECT firstName FROM Employee WHERE roleId=" + developerId + " AND projectId=" + projectId + ";");
             while (resultSet.next()) {
                 javaDevelopers.add(resultSet.getString(1));
             }
             resultSet.close();
         }
         return javaDevelopers;
    }
}