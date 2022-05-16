package com.goodee.ex05.service;

import java.util.List;
import java.util.Map;

import com.goodee.ex05.domain.ProductDTO;

public interface ProductService {

	public List<ProductDTO> list1();          // products = [ {},  {},  {} ]
	
	public List<Map<String, Object>> list2(); // products = [ {},  {},  {} ]
	
	public Map<String, Object> list3();       // result = {  
}                                             //    "products" : [ {},  {},  {} ]
											  // }