package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Product;
import com.example.demo.entity.Purchase;
import com.example.demo.entity.PurchaseItem;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ProductService;
import com.example.demo.service.PurchaseService;
import com.example.demo.service.SupplierService;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ProductService productService;
	
	// 採購單主檔 CRUD --------------------------------------------------
	@GetMapping("/")
	public String index(@ModelAttribute Purchase purchase, Model model) {
		model.addAttribute("purchases", purchaseService.findAll());
		model.addAttribute("suppliers", supplierService.findAll());
		model.addAttribute("employees", employeeService.findAll());
		return "purchase";
	}
	
	@PostMapping("/")  // 新增採購單
	public String add(Purchase purchase) {
		purchaseService.save(purchase);
		return "redirect:/purchase/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		Purchase purchase = purchaseService.findById(id);
		model.addAttribute("purchase", purchase);
		model.addAttribute("suppliers", supplierService.findAll());
		model.addAttribute("employees", employeeService.findAll());
		return "purchase-edit";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long id, Purchase purchase) {
		purchase.setId(id);
		purchaseService.save(purchase);
		return "redirect:/purchase/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		purchaseService.deleteById(id);
		return "redirect:/purchase/";
	}
	
	// 採購單明細檔 CRUD --------------------------------------------------
	@GetMapping("/{pid}/item")
	public String indexItem(@PathVariable("pid") Long pid, 
							@ModelAttribute PurchaseItem purchaseItem, 
							Model model) {
		// 透過 pid 先找到該筆採購單
		Purchase purchase = purchaseService.findById(pid);
		// 取得所有商品
		List<Product> products = productService.findAll();
		model.addAttribute("purchase", purchase);
		model.addAttribute("products", products);
		
		return "purchase-item";
	}
	
	// 新增採購單明細
	@PostMapping("/{pid}/item")
	public String addItem(PurchaseItem purchaseItem, @PathVariable("pid") Long pid) {
		purchaseService.savePurchaseItem(purchaseItem, pid);
		return "redirect:/purchase/" + pid + "/item";
	}
	
	// 取得採購單明細 /edit/4/item/7, 4:採購單id, 7:採購單明細id
	@GetMapping("/edit/{pid}/item/{iid}")
	public String getItem(@PathVariable("pid") Long pid, 
						  @PathVariable("iid") Long iid,
						  Model model) {
		Purchase purchase = purchaseService.findById(pid);
		PurchaseItem purchaseItem = purchaseService.findpItemById(iid);
		List<Product> products = productService.findAll();
		
		model.addAttribute("purchase", purchase);
		model.addAttribute("purchaseItem", purchaseItem);
		model.addAttribute("products", products);
		
		return "purchase-item";
	}
	
	// 修改採購明細
	@PutMapping("/{pid}/item")
	public String updateItem(PurchaseItem purchaseItem, @PathVariable("pid") Long pid) {
		purchaseService.savePurchaseItem(purchaseItem, pid);
		
		return "redirect:/purchase/" + pid + "/item";
	}
	
	// 刪除採購明細
	@GetMapping("/delete/{pid}/item/{iid}")
	public String deleteItem(@PathVariable("pid") Long pid, @PathVariable("iid") Long iid){
		purchaseService.deletePurchaseItem(iid);
		return "redirect:/purchase/" + pid + "/item";
	}
}
