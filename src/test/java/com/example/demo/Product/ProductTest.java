package com.example.demo.Product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.util.LangUtil.ProcessController;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Controller.CategoryServiceController;
import com.example.demo.Controller.ProductController;
//import com.example.demo.Controller.UserController;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Service.CategoryService;
import com.example.demo.Service.ProductService;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.CategoryDto;
import com.example.demo.payload.ProductDto;
import com.example.demo.payload.ProductResponse;

//@RunWith(SpringRunner.class)
//@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ProductTest {

	
	@Autowired
	private	ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private	CategoryService categoryService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductController productController;
	
	@Autowired
	private CategoryServiceController categoryController;
	
	private ProductDto productDto;
	static int categoryId ;
	static int productId;
//	@Before(value = "")
//	public void setup() {
//		MockitoAnnotations.initMocks(this);
//	}
//	
	@Test
	@Order(1)
	public void testCreateProduct() {
//		int pid = 1;
//		int categoryId = 101;
		CategoryDto catDto = new CategoryDto();
//		catDto.setCategoryId(categoryId);
		catDto.setTitle("test");
		
//		System.out.println(categoryId+" this is Id");
		productDto = new ProductDto();
		productDto.setProductName("testproduct");
		productDto.setProductDesc("Test Description");
		productDto.setProductPrize(123);
		productDto.setStock(true);
		productDto.setProductQuantity(21);
		productDto.setLive(true);
		productDto.setImageName("testImagename");
		

		// Perform the API call
		CategoryDto response = categoryService.Create(catDto);
		categoryId = response.getCategoryId();
//		assertThat(response.getCategoryId()).isEqualTo(categoryId);
		assertThat(response.getTitle()).isEqualTo("test");
		ProductDto response1 = productService.createProduct(productDto, categoryId);
		productId = response1.getProductId();
		assertThat(response1.getProductName()).isEqualTo("testproduct");


//		
	}
	
	
	@Test
	@Order(2)
	public void testViewAllProduct() {
		
		ProductResponse res =  productController.viewAllProduct(0, 2, null, null);
//		System.out.println("res of all prod"+res.getContent().get(productId));
//		System.out.println(res.getContent()..getProductName());
//		System.out.println("This is res: "+res.getContent().get(0).getProductName());
		assertEquals(res.getPageSize(), 2);
		assertThat(res.getContent().get(0).getProductName()).isEqualTo("Iphone 12 pro");
		
	}
	
	
	@Test
	@Order(3)
	public void testgetProductById() {
		ResponseEntity<ProductDto> res =  productController.getProductById(productId);
		assertEquals(res.getStatusCode(), HttpStatus.OK);
		assertEquals(res.getBody().getProductId(), productId);
		
	}
	
	@Test
	@Order(4)
	public void testUpdate() {
//		int pId = 6;
		ProductDto newProduct = new ProductDto();
		newProduct.setProductName("Updatetestproduct");
		newProduct.setProductDesc("Update Test Description");
		newProduct.setProductPrize(1234);
		newProduct.setStock(true);
		newProduct.setProductQuantity(21);
		newProduct.setLive(true);
		newProduct.setImageName("UpdatetestImagename");
		
		ResponseEntity<ProductDto> res =  productController.update(productId,newProduct);
		assertEquals(res.getStatusCode(), HttpStatus.OK);
		assertEquals(res.getBody().getProductId(), productId);
		assertThat(res.getBody().getProductName()).isEqualTo(newProduct.getProductName());
		
	}
	
	@Test
	@Order(5)
	public void testDeleteProduct() {
		
		int res = productService.deleteProduct(productId);
		assertThat(res).isEqualTo(0);
		
		categoryService.deleteCategory(categoryId);
		try {
			CategoryDto cat = categoryService.getCategoryByid(categoryId);			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("No category with id :"+categoryId+" is present");
		}

		
	}
	
}






///////////






////@RunWith(SpringRunner.class)
////@AutoConfigureTestDatabase(replace=Replace.NONE)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest
//public class ProductTest {
//
//	
//	@Mock
//	private	ProductService productService;
//	
//	@Mock
//	private ProductRepository productRepository;
//	
//	@Mock
//	private	CategoryService categoryService;
//	
//	@Mock
//	private CategoryRepository categoryRepository;
//	
//	@InjectMocks
//	private ProductController productController;
//	
//	
//	@InjectMocks
//	private CategoryServiceController categoryController;
//	
//	@Before(value = "")
//	public void setup() {
//		MockitoAnnotations.initMocks(this);
//	}
//	
//	@Test
//	public void testCreateProduct() {
//		int pid = 1;
//		int categoryId = 2;
//		CategoryDto catDto = new CategoryDto();
//		catDto.setCategoryId(categoryId);
//		catDto.setTitle("test");
//		
//		CategoryDto savedcatDto = new CategoryDto();
//		catDto.setCategoryId(categoryId);
//		catDto.setTitle("test");
//
//		System.out.println(categoryId+" this is Id");
//		ProductDto productDto = new ProductDto();
//		productDto.setProductName("testproduct");
//		productDto.setProductDesc("Test Description");
//		productDto.setProductPrize(123);
//		productDto.setStock(true);
//		productDto.setProductQuantity(21);
//		productDto.setLive(true);
//		productDto.setImageName("testImagename");
//		
//		ProductDto savedProductDto = new ProductDto();
//		savedProductDto.setProductId(pid);
//		savedProductDto.setProductName("testproduct");
//		savedProductDto.setProductDesc("Test Description");
//		savedProductDto.setProductPrize(123);
//		savedProductDto.setStock(true);
//		savedProductDto.setProductQuantity(21);
//		savedProductDto.setLive(true);
//		productDto.setImageName("testImagename");
//		
//		
//		when(categoryService.Create(catDto)).thenReturn(savedcatDto);
//		when(productService.createProduct(productDto,categoryId)).thenReturn(savedProductDto);
//
//		// Perform the API call
//		ResponseEntity<CategoryDto> response = categoryController.createCategory(catDto);
//		ResponseEntity<ProductDto> response1 = productController.createProduct(productDto, categoryId);
//
//		System.out.println(response1.getStatusCodeValue());
//		System.out.println(response1.getBody());
//		assertEquals(HttpStatus.CREATED, response1.getStatusCode());
//		assertEquals(savedProductDto, response1.getBody());
//		assertEquals(savedProductDto.getProductId(), pid);
//		
//	}
//	
//	@Test
//	public void testDeleteProduct() {
//		int pid = 1;
//		int categoryId = 2;
//		CategoryDto catDto = new CategoryDto();
//		catDto.setCategoryId(categoryId);
//		catDto.setTitle("test");
//		
//		CategoryDto savedcatDto = new CategoryDto();
//		catDto.setCategoryId(categoryId);
//		catDto.setTitle("test");
//
//		ProductDto productDto = new ProductDto();
//		productDto.setProductName("testproduct");
//		productDto.setProductDesc("Test Description");
//		productDto.setProductPrize(123);
//		productDto.setStock(true);
//		productDto.setProductQuantity(21);
//		productDto.setLive(true);
//		productDto.setImageName("testImagename");
//		
//		ProductDto savedProductDto = new ProductDto();
//		savedProductDto.setProductId(pid);
//		savedProductDto.setProductName("testproduct");
//		savedProductDto.setProductDesc("Test Description");
//		savedProductDto.setProductPrize(123);
//		savedProductDto.setStock(true);
//		savedProductDto.setProductQuantity(21);
//		savedProductDto.setLive(true);
//		productDto.setImageName("testImagename");
//		
//		when(categoryService.Create(catDto)).thenReturn(savedcatDto);
//		when(productService.createProduct(productDto,categoryId)).thenReturn(savedProductDto);
//		when(productService.deleteProduct(pid)).thenReturn(0);
//		
//		ResponseEntity<ApiResponse> res = productController.deleteProduct(categoryId);
//		
//		assertEquals(res.getStatusCode(), HttpStatus.OK );
//		assertEquals(res.getBody().isSuccess(), true);
//		
//		
//	}
//	
//}
