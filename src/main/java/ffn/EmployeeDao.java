package ffn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

public class EmployeeDao extends FFNDao{

	
	/**Returns all Employees in the database**/
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
    	System.out.println("EmployeeDao is running getAllEmployees");
		List<Employee> empList = new ArrayList<Employee>();
		try(Connection conn = getConnection(); Statement st = conn.createStatement();){

			ResultSet rs = st.executeQuery("SELECT EMPLOYEE.EMPLOYEE_NO, EMPLOYEE.FIRST_NAME,"
					+ "EMPLOYEE.LAST_NAME, EMPLOYEE.DEPARTMENT_NO FROM EMPLOYEE");
			while (rs.next()){
				Employee emp = new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
				empList.add(emp);
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return empList;
	}



	/**Returns the Employee with the given ID in the database**/
	public Employee getEmployee(int employeeId) {
		System.out.println("EmployeeDao is running getEmployee");
		Employee emp = null;
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(
				"SELECT EMPLOYEE.EMPLOYEE_NO, EMPLOYEE.FIRST_NAME, EMPLOYEE.LAST_NAME, EMPLOYEE.DEPARTMENT_NO "
						+ " FROM EMPLOYEE WHERE EMPLOYEE.EMPLOYEE_NO = ?");){
			ps.setInt(1, employeeId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				emp = new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return emp;
	}


	/**Attempts to add an Employee to the database.**/
	public String addEmployee(Employee employee) {
		System.out.println("EmployeeDao is running addEmployee");
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO EMPLOYEE VALUES(?,?,?,?)");){
			ps.setInt(1, employee.getEmployeeId());
			ps.setString(2, employee.getFirstName());
			ps.setString(3, employee.getLastName());
			ps.setInt(4, employee.getDepartmentNo());
			ps.execute();
			
		}catch(DerbySQLIntegrityConstraintViolationException e){
			if (e.getMessage().contains("EMPLOYEE_NO_PK")) return "The specified Employee ID is in use. "
					+ "If you are updating a resource, please use a PUT request. Otherwise, please "
					+ "choose a different ID.";
			else if(e.getMessage().contains("DEPARTMENT_NO_FK")) return "The specified department ID is not in use. "
					+ "Please choose an existing department, or add or update the department before adding the employee."; 
			else e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return "Finished";
	}
	
	/**Attempts to update an Employee in the database.**/
	public String updateEmployee(Employee employee) {
		System.out.println("EmployeeDao is running updateEmployee");
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(
				"UPDATE EMPLOYEE SET EMPLOYEE.FIRST_NAME = ?, EMPLOYEE.LAST_NAME = ?, EMPLOYEE.DEPARTMENT_NO = ? "
				+ "WHERE EMPLOYEE.EMPLOYEE_NO = ?");){
			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setInt(3, employee.getDepartmentNo());
			ps.setInt(4, employee.getEmployeeId());
			ps.execute();
			
		}catch(DerbySQLIntegrityConstraintViolationException e){
			if (e.getMessage().contains("DEPARTMENT_NO_FK")) return "The specified department ID is not in use. "
					+ "Please choose an existing department, or add or update the department before updating the employee."; 
			else e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return "Finished";
	}

	/**Deletes an Employee in the database.**/
	public String deleteEmployee(int employeeId) {
		System.out.println("EmployeeDao is running deleteEmployee");
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(
				"DELETE FROM EMPLOYEE WHERE EMPLOYEE.EMPLOYEE_NO = ?");){
			ps.setInt(1, employeeId);
			ps.execute();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return "Finished";
	}
}
