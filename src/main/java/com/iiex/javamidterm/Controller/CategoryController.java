package com.iiex.javamidterm.Controller;

import com.iiex.javamidterm.Model.Category;
import com.iiex.javamidterm.Repository.CategoryRepository;
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
public class CategoryController {

  @Autowired
  private CategoryRepository categoryRepository;

  @GetMapping("/categorys")
  public String showcategorys(Model model) {
    List<Category> categoryList = categoryRepository.findAll();
    model.addAttribute("categoryList", categoryList);
    model.addAttribute("prefix", "category");
    return "admin/index";
  }

  @PostMapping("/categorys/save")
  public String save(Category category) {
    categoryRepository.save(category);
    return "redirect:/admin/categorys";
  }

  @GetMapping("/categorys/delete/{id}")
  public String delete(@PathVariable("id") Integer id, RedirectAttributes ra) {
    try {
      categoryRepository.deleteById(id);
      ra.addFlashAttribute("message", "Xóa thành công");
      return "redirect:/admin/categorys";
    } catch (Exception e) {
      ra.addFlashAttribute("error", "Có lỗi xảy ra! Xóa thất bại.");
      e.printStackTrace();
      return "redirect:/admin/categorys";
    }
  }

  @GetMapping("/categorys/edit/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    Category category = categoryRepository
      .findById(id)
      .orElseThrow(() ->
        new IllegalArgumentException("Thương hiệu không tồn tại:" + id)
      );
    model.addAttribute("category", category);
    return "redirect:/categorys";
  }

  @PostMapping("/categorys/update/{id}")
  public String updateUser(
    @PathVariable("id") Integer id,
    @Valid Category user,
    Model model
  ) {
    categoryRepository.save(user);
    return "redirect:/admin/categorys";
  }

  @ModelAttribute
  Category setupForm() {
    return new Category();
  }
}
