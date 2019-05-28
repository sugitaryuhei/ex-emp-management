package jp.co.sample.form;

/**
 * 扶養を更新するためのフォーム.
 *  
 * @author 81907
 */
public class UpdateEmployeeForm {
	
	/** ID */
	private Integer id;
	/** 扶養人数 */	
	private Integer dependentsCount;
	
	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDependentsCount() {
		return dependentsCount;
	}
	public void setDependentsCount(Integer dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

}
