package com.iiex.javamidterm.Controller;

import com.iiex.javamidterm.Model.Product;
import com.iiex.javamidterm.Repository.BrandRepository;
import com.iiex.javamidterm.Repository.CategoryRepository;
import com.iiex.javamidterm.Repository.ColorRepository;
import com.iiex.javamidterm.Repository.ProductRepository;
import jakarta.validation.Valid;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class ProductController {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private BrandRepository brandRepository;

  @Autowired
  private ColorRepository colorRepository;

  @GetMapping("/products")
  public String showproducts(Model model) {
    List<Product> productList = productRepository.findAll();
    model.addAttribute("productList", productList);
    model.addAttribute("prefix", "product");
    model.addAttribute("listCategory", categoryRepository.findAll());
    model.addAttribute("listBrand", brandRepository.findAll());
    model.addAttribute("listColor", colorRepository.findAll());
    return "admin/index";
  }

  @PostMapping("/products/save")
  public String save(Product product) {
    if (!(product.getColors() instanceof List)) {
      product.setColors(product.getColors());
    }
    productRepository.save(product);
    return "redirect:/admin/products";
  }

  @GetMapping("/products/delete/{id}")
  public String delete(@PathVariable("id") Integer id, RedirectAttributes ra) {
    try {
      productRepository.deleteProductById(id);
      ra.addFlashAttribute("message", "Xóa thành công");
      return "redirect:/admin/products";
    } catch (Exception e) {
      ra.addFlashAttribute("error", "Có lỗi xảy ra! Xóa thất bại.");
      e.printStackTrace();
      return "redirect:/admin/products";
    }
  }

  @GetMapping("/products/edit/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    Product product = productRepository
      .findById(id)
      .orElseThrow(() ->
        new IllegalArgumentException("Thương hiệu không tồn tại:" + id)
      );
    model.addAttribute("product", product);
    return "redirect:/admin/products";
  }

  @PostMapping("/products/update/{id}")
  public String updateUser(
    @PathVariable("id") Integer id,
    @Valid Product user,
    Model model
  ) {
    productRepository.save(user);
    return "redirect:/admin/products";
  }

  @ModelAttribute
  Product setupForm() {
    return new Product();
  }
}
