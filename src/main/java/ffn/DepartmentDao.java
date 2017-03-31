package ffn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

public class DepartmentDao extends FFNDao{


	/**Returns all Departments in the database**/
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
    	System.out.println("DepartmentDao is running getAllDepartments");
		List<Department> depList = new ArrayList<Department>();
		try(Connection conn = getConnection(); Statement st = conn.createStatement();){

			ResultSet rs = st.executeQuery("SELECT DEPARTMENT.DEPARTMENT_NO, "
					+ "DEPARTMENT.DEPARTMENT_NAME FROM DEPARTMENT");
			while (rs.next()){
				Department dep = new Department(rs.getInt(1),rs.getString(2));
				depList.add(dep);
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return depList;
	}



	/**Returns the Department with the given ID in the database**/
	public Department getDepartment(int departmentId) {
		System.out.println("DepartmentDao is running getDepartment");
		Department dep = null;
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(
				"SELECT DEPARTMENT.DEPARTMENT_NO, DEPARTMENT.DEPARTMENT_NAME "
						+ " FROM DEPARTMENT WHERE DEPARTMENT.DEPARTMENT_NO = ?");){
			ps.setInt(1, departmentId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				dep = new Department(rs.getInt(1),rs.getString(2));
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return dep;
	}


	/**Attempts to add a Department to the database.**/
	public String addDepartment(Department department) {
		System.out.println("DepartmentDao is running addDepartment");
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO DEPARTMENT VALUES(?,?)");){
			ps.setInt(1, department.getDepartmentId());
			ps.setString(2, department.getDepartmentName());
			ps.execute();
			
		}catch(DerbySQLIntegrityConstraintViolationException e){
			if (e.getMessage().contains("DEPARTMENT_NO_FK")) return "The specified department ID is in use. "
					+ "If you are updating a resource, please use a PUT request. Otherwise, please "
					+ "choose a different ID.";
			else e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return "Finished";
	}
	
	/**Attempts to update a Department in the database.**/
	public String updateDepartment(Department department) {
		System.out.println("DepartmentDao is running updateDepartment");
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(
				"UPDATE DEPARTMENT SET DEPARTMENT.DEPARTMENT_NAME = ? WHERE DEPARTMENT.DEPARTMENT_NO = ?");){
			ps.setString(1, department.getDepartmentName());
			ps.setInt(2, department.getDepartmentId());
			ps.execute();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return "Finished";
	}

	/**Deletes a Department in the database.**/
	public String deleteDepartment(int departmentId) {
		System.out.println("DepartmentDao is running deleteDepartment");
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(
				"DELETE FROM DEPARTMENT WHERE DEPARTMENT.DEPARTMENT_NO = ?");){
			ps.setInt(1, departmentId);
			ps.execute();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return "Finished";
	}
}
