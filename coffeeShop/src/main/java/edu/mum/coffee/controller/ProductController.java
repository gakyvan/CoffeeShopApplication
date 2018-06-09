package edu.mum.coffee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.ProductService;

@Controller
@RequestMapping(value="/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String getAddProductForm(@ModelAttribute Product product) {
		return "adminNewProduct";
	}
	
	@RequestMapping(value="/changestatus/{id}")
	public String changeStatus(@PathVariable int id, RedirectAttributes redirect) {
		Product product=productService.getProduct(id);
		if(product.isEnabled()) {
			product.setEnabled(false);
		}else {
			product.setEnabled(true);
		}
		productService.save(product);
		redirect.addFlashAttribute("message", "The product status changed successfully");
		return "redirect:/product/all";
	}
	
	@RequestMapping(value="/find/{id}", method=RequestMethod.GET)
	public String findProduct(@PathVariable int id, Model model) {
		Product product=productService.getProduct(id);
		model.addAttribute("product", product);
		return "adminEditProductForm";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addNewProduct(@ModelAttribute @Valid Product product, BindingResult result, RedirectAttributes redirect) {
		if(!result.hasErrors()) {
			product.setEnabled(false);
			productService.save(product);
			redirect.addFlashAttribute("message", "The product was successfully saved");
			return "redirect:/product/all";
		}else {
			return "adminNewProduct";
		}
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editProduct(@ModelAttribute @Valid Product product, BindingResult result, RedirectAttributes redirect) {
		if(!result.hasErrors()) {
			productService.save(product);
			redirect.addFlashAttribute("message", "The product was successfully edited");
			return "redirect:/product/all";
		}else {
			return "adminNewProduct";
		}
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public String getProductList(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		return "adminProductsList";
	}
	
	@RequestMapping(value="/menu", method=RequestMethod.GET)
	public String getAllList(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		return "customerProductsList";
	}
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	
	
}
