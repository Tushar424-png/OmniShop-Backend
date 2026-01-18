package com.productservices.Controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.productservices.Service.ProductService;
import com.productservices.dto.ProductRequest;
import com.productservices.dto.ProductResponse;

@RequestMapping("/products")
@RestController
public class ProductController {
	private final ProductService productservice;
	public ProductController(ProductService productservice) {
		this.productservice=productservice;
	}
	
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
	public ProductResponse add(@RequestBody ProductRequest productrequest) {
    	return productservice.add(productrequest);
    }
    
	@GetMapping("/getall")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse>getall(){
		return productservice.getAll();
		
	}
	@GetMapping("/getone/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProductResponse getProductById(@PathVariable Long id){
		return productservice.getOne(id);
		
	}
	@PutMapping("/internal/{productId}/deactivate")
	public void deactivateProduct(@PathVariable Long productId) {
	    productservice.deactivate(productId);
	}

}
