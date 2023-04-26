package com.rubypaper.biz.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

//TODO - h-11. @ToString(exclude = ...) for avoiding circular reference
/*

In this specific case, the Department class has a bidirectional OneToMany relationship with the Employee class,
represented by the employeeList field. The Employee class likely has a reference back to the Department class,
creating a circular reference between these two entities.

If the employeeList field is included in the toString() method,
it could lead to infinite recursion when attempting to print the object's string representation.
This is because when toString() is called on a Department object,
it will try to print the employeeList, which in turn will call toString() on each Employee object.
If the Employee object also includes a reference back to the Department object,
it would try to call toString() on the Department object again, leading to an infinite loop.

 */

@Data
@ToString(exclude = "employeeList")
@Entity
@Table(name = "S_DEPT")
public class Department {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEPT_ID")
	private Long deptId;

	private String name;
	
	@OneToMany(mappedBy = "dept", fetch = FetchType.EAGER)
	private List<Employee> employeeList = new ArrayList<Employee>();
}
