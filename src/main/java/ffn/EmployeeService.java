package ffn;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

// the URI path will be "/employee_service"
@Path("/employee_service")
public class EmployeeService {
	
	EmployeeDao dao = new EmployeeDao();
    
	/**Returns all employees in the database, called with a GET request**/
    @GET
    @Path("/employees")
    @Produces(MediaType.APPLICATION_XML)
    public List<Employee> getAllEmployees() {
        // TO-DO
    	System.out.println("EmployeeService is running getAllEmployees");
    	List<Employee> empList = dao.getAllEmployees();
    	System.out.println("getAllEmployees finished");
    	return empList;
    }
	
    /**Returns a single employee identified by their ID, called with a GET request**/
    @GET
    @Path("/employees/{employeeid}")
    @Produces(MediaType.APPLICATION_XML)
    public Employee getEmployee(@PathParam("employeeid") int employeeId){
    	System.out.println("EmployeeService is running getEmployee");
    	Employee emp = dao.getEmployee(employeeId);
    	return emp;
    }
    
    /**Adds a new employee, called with a POST request**/
    @POST
    @Path("/employees")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createEmployee(@FormParam("employeeid") int employeeId,
       @FormParam("firstname") String firstName, @FormParam("lastname") String lastName,
       @FormParam("departmentno") int departmentNo,  @Context HttpServletResponse servletResponse) throws IOException{
    	System.out.println("EmployeeService is running createEmployee");
       Employee employee = new Employee(employeeId, firstName, lastName, departmentNo);
       String result = dao.addEmployee(employee);
       return result;
    }

    /**Updates an employee identified by their ID, called with a PUT request**/
    @PUT
    @Path("/employees")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String updateEmployee(@FormParam("employeeid") int employeeId,
       @FormParam("firstname") String firstName, @FormParam("lastname") String lastName,
       @FormParam("departmentno") int departmentNo,
       @Context HttpServletResponse servletResponse) throws IOException{
    	System.out.println("EmployeeService is running updateEmployee");
       Employee employee = new Employee(employeeId, firstName, lastName, departmentNo);
       String result = dao.updateEmployee(employee);
       return result;
    }
    
    

    /**Deletes an employee identified by their ID, called with a DELETE request**/
    @DELETE
    @Path("/employees/{employeeid}")
    @Produces(MediaType.APPLICATION_XML)
    public String deleteEmployee(@PathParam("employeeid") int employeeId){
       String result = dao.deleteEmployee(employeeId);
       return result;
    }

}
