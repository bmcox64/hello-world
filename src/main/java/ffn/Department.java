package ffn;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "department")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int departmentId;
	private String departmentName;
	
	public Department(){}
	
	public Department(int departmentId, String departmentName) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	@XmlElement
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	@XmlElement
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
}
