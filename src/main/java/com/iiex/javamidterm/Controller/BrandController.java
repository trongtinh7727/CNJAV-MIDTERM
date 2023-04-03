package com.iiex.javamidterm.Controller;

import com.iiex.javamidterm.Model.Brand;
import com.iiex.javamidterm.Repository.BrandRepository;
import jakarta.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/admin")
@Controller
public class BrandController {

  @Autowired
  private BrandRepository brandRepository;

  @GetMapping("/brands")
  public String showBrands(Model model) {
    List<Brand> brandList = brandRepository.findAll();
    model.addAttribute("brandList", brandList);
    model.addAttribute("prefix", "brand");
    return "admin/index";
  }

  @PostMapping("/brands/save")
  public String save(Brand brand) {
    brandRepository.save(brand);
    return "redirect:/admin/brands";
  }

  @GetMapping("/brands/delete/{id}")
  public String delete(@PathVariable("id") Integer id, RedirectAttributes ra) {
    try {
      brandRepository.deleteById(id);
      ra.addFlashAttribute("message", "Xóa thành công");
      return "redirect:/brands";
    } catch (Exception e) {
      ra.addFlashAttribute("error", "Có lỗi xảy ra! Xóa thất bại.");
      e.printStackTrace();
      return "redirect:/admin/brands";
    }
  }

  @GetMapping("/brands/edit/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    Brand brand = brandRepository
      .findById(id)
      .orElseThrow(() ->
        new IllegalArgumentException("Thương hiệu không tồn tại:" + id)
      );
    model.addAttribute("brand", brand);
    return "redirect:/admin/brands";
  }

  @PostMapping("/brands/update/{id}")
  public String updateUser(
    @PathVariable("id") Integer id,
    @Valid Brand user,
    Model model
  ) {
    brandRepository.save(user);
    return "redirect:/admin/brands";
  }

  @ModelAttribute
  Brand setupForm() {
    return new Brand();
  }
}
