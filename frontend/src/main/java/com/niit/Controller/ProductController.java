package com.niit.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.dao.ProductDAO;
import com.niit.model.Product;

@Controller
public class ProductController {
    @Autowired
    ProductDAO productDao;
	
	
	@RequestMapping("/product")
	public String showProduct(Model m)
	{
		List<Product> listProducts=productDao.listProducts();
		m.addAttribute("listProducts",listProducts);
		m.addAttribute("pageinfo","Manage Product");
		return "Product";
	}
	@RequestMapping(value="/AddProduct",method=RequestMethod.POST)
	public String addProduct(Model m,@RequestParam("cName")String productName,@RequestParam("cDesc")String productDesc)
	{
		Product product=new Product();
		product.setProductName(productName);
		product.setProductDesc(productDesc);
		productDao.addProduct(product);
		
		List<Product> listProducts=productDao.listProducts();
		m.addAttribute("listProducts", listProducts);
		
		m.addAttribute("pageinfo","Manage Product");
		return "Product";
	}
	
	@RequestMapping(value="/deleteProduct/{productID}")
	public String deleteProduct(Model m,@PathVariable("productID")int productID)
	{
		Product product=productDao.getProduct(productID);
		productDao.deleteProduct(product);
		
		m.addAttribute("pageinfo","Manage Product");
		return "Product";
	}
		@RequestMapping(value="/UpdateProduct",method=RequestMethod.POST)
	public String updateProduct(Model m,@RequestParam("cID")int productID,@RequestParam("cName")String productName,@RequestParam("cDesc")String productDesc)
	{
			Product product=productDao.getProduct(productID);
			product.setProductName(productName);
			product.setProductDesc(productDesc);
			productDao.updateProduct(product);
		
		List<Product> listProducts=productDao.listProducts();
		m.addAttribute("listProducts", listProducts);
		
		m.addAttribute("pageinfo","Manage Product");
		return "Product";
	}
		@RequestMapping(value="/editProduct/{productID}")
		public String editProduct(Model m,@PathVariable("productID")int productID)
		{
			Product product=productDao.getProduct(productID);
			
			m.addAttribute("product",product);
			m.addAttribute("pageinfo","Manage Product");
			return "UpdateProduct";
		}

}