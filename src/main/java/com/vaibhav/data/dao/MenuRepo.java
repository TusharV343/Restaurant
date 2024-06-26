package com.vaibhav.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vaibhav.data.controler.Menu;


public interface MenuRepo extends JpaRepository<Menu,Integer>{
	
	@Query(value="select id, item_name as itemName,item_price as itemPrice from menu",nativeQuery=true)
	public List<Menu> findAll1();

	
	

}
