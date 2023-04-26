package com.rubypaper.shopping.biz.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "orderList")
@Table(name = "S_CUSTOMER")
public class Customer {
	
	// ??? ?????
	@Id	@GeneratedValue
	@Column(name = "CUSTOMER_ID")
	private Long id;			

	// ??? ???
	private String name;		

	// ??? ??????
	private String phone;		

	// ??? ?во ????
	private String comments;		

	// ??? ???
	@Embedded
	private Address address; 	
	
	// ??? ???
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private List<Order> orderList = new ArrayList<Order>();

}
