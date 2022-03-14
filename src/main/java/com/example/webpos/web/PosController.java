package com.example.webpos.web;

import com.example.webpos.biz.PosService;
import com.example.webpos.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PosController {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @GetMapping("/")
    public String pos(Model model) {
        model.addAttribute("products", posService.products());
        model.addAttribute("cart", posService.getCart());
        model.addAttribute("total", posService.total(posService.getCart()));
        return "index";
    }

    @GetMapping("/add/{id}/{amount}")
    public String add(Model model, @PathVariable String id, @PathVariable int amount) {
        if (amount > 0) posService.add(id, amount);
        return "redirect:/";
    }

    @GetMapping("delete/{id}/{amount}")
    public String delete(Model model, @PathVariable String id, @PathVariable int amount) {
        if (amount > 0) posService.delete(id, amount);
        return "redirect:/";
    }

    @GetMapping("/remove/{id}")
    public String remove(Model model, @PathVariable String id) {
        posService.remove(id);
        return "redirect:/";
    }

    @GetMapping("/clear")
    public String clear(Model model) {
        posService.newCart();
        return "redirect:/";
    }
}
