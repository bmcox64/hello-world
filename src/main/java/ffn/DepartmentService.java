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

// the URI path will be "/department_service"
@Path("/department_service")
public class DepartmentService {
	
	DepartmentDao dao = new DepartmentDao();
    
	/**Returns all departments in the database, called with a GET request**/
    @GET
    @Path("/departments")
    @Produces(MediaType.APPLICATION_XML)
    public List<Department> getAllDepartments() {
        // TO-DO
    	System.out.println("DepartmentService is running getAllDepartments");
    	List<Department> depList = dao.getAllDepartments();
    	System.out.println("getAllDepartments finished");
    	return depList;
    }
	
    /**Returns a single department identified by its ID, called with a GET request**/
    @GET
    @Path("/departments/{departmentid}")
    @Produces(MediaType.APPLICATION_XML)
    public Department getDepartment(@PathParam("departmentid") int departmentId){
    	System.out.println("DepartmentService is running getDepartment");
    	Department dep = dao.getDepartment(departmentId);
    	return dep;
    }
    
    /**Adds a new department, called with a POST request**/
    @POST
    @Path("/departments")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createDepartment(@FormParam("departmentid") int departmentId,
       @FormParam("departmentname") String departmentName,  @Context HttpServletResponse servletResponse) throws IOException{
    	System.out.println("DepartmentService is running createDepartment");
       Department department = new Department(departmentId, departmentName);
       String result = dao.addDepartment(department);
       return result;
    }

    /**Updates an department identified by their ID, called with a PUT request**/
    @PUT
    @Path("/departments")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String updateDepartment(@FormParam("departmentid") int departmentId,
       @FormParam("departmentname") String departmentName,
       @Context HttpServletResponse servletResponse) throws IOException{
    	System.out.println("DepartmentService is running updateDepartment");
       Department department = new Department(departmentId, departmentName);
       String result = dao.updateDepartment(department);
       return result;
    }
    
    

    /**Deletes an department identified by their ID, called with a DELETE request**/
    @DELETE
    @Path("/departments/{departmentid}")
    @Produces(MediaType.APPLICATION_XML)
    public String deleteDepartment(@PathParam("departmentid") int departmentId){
       String result = dao.deleteDepartment(departmentId);
       return result;
    }

}
