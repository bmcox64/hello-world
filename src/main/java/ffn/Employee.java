package ffn;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int employeeId;
	private String firstName;
	private String lastName;
	private int departmentNo;


	public Employee(){}

	public Employee(int employeeId, String firstName, String lastName, int departmentNo) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.departmentNo = departmentNo;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	@XmlElement
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	@XmlElement
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	@XmlElement
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getDepartmentNo() {
		return departmentNo;
	}
	@XmlElement
	public void setDepartmentNo(int departmentNo) {
		this.departmentNo = departmentNo;
	}
	
//	
//	@Override
//	public boolean equals(Object object){
//		if(object == null){
//			return false;
//		}else if(!(object instanceof Employee)){
//			return false;
//		}else {
//			Employee emp = (Employee) object;
//			if(firstName == null && emp.getFirstName() != null || lastName == null && emp.getLastName() != null){
//				return false;
//			}else {
//				if(employeeId == emp.getEmployeeId()
//						&& departmentNo == emp.getDepartmentNo()
//						&& firstName.equals(emp.getFirstName())
//						&& lastName.equals(emp.getLastName())){
//					return true;
//				}			
//			}
//			return false;
//		}	
//	}
}
