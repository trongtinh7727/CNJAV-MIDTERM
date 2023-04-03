package com.iiex.javamidterm.Controller;

import com.iiex.javamidterm.Model.Transaction;
import com.iiex.javamidterm.Repository.TransactionRepository;
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
public class TransactionController {

  @Autowired
  private TransactionRepository transactionRepository;

  @GetMapping("")
  public String adminIndex(Model model) {
    return "redirect:/admin/transactions";
  }

  @GetMapping("/transactions")
  public String showtransactions(Model model) {
    List<Transaction> transactionList = transactionRepository.findAll();
    model.addAttribute("transactionList", transactionList);
    model.addAttribute("prefix", "transaction");
    return "admin/index";
  }

  @PostMapping("/transactions/save")
  public String save(Transaction transaction) {
    transactionRepository.save(transaction);
    return "redirect:/admin/transactions";
  }

  @GetMapping("/transactions/delete/{id}")
  public String delete(@PathVariable("id") Integer id, RedirectAttributes ra) {
    try {
      transactionRepository.deleteTransactionById(id);
      ra.addFlashAttribute("message", "Xóa thành công");
      return "redirect:/admin/transactions";
    } catch (Exception e) {
      ra.addFlashAttribute("error", "Có lỗi xảy ra! Xóa thất bại.");
      e.printStackTrace();
      return "redirect:/admin/transactions";
    }
  }

  @GetMapping("/transactions/edit/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    Transaction transaction = transactionRepository
      .findById(id)
      .orElseThrow(() ->
        new IllegalArgumentException("Thương hiệu không tồn tại:" + id)
      );
    model.addAttribute("transaction", transaction);
    return "redirect:/admin/transactions";
  }

  @PostMapping("/transactions/update/{id}")
  public String updateUser(
    @PathVariable("id") Integer id,
    @Valid Transaction user,
    Model model
  ) {
    transactionRepository.save(user);
    return "redirect:/admin/transactions";
  }

  @ModelAttribute
  Transaction setupForm() {
    return new Transaction();
  }
}
