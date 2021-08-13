package com.def.controller;

import com.def.model.Order;
import com.def.model.Product;
import com.def.repository.OrderRepository;
import com.def.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomePageController {

    private Order order;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;


    @GetMapping
    public String home(Model model){
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products",products);
        return "home";
    }

    public Order create(){
        return new Order();
    }

    @PostMapping
    public String createOrder(){
        order = create();
        return "redirect:/home";
    }

    @PostMapping("/{id}")
    public String addProductInOrder(@PathVariable Long id,Model model){
        if (!productRepository.existsById(id)){
            return "redirect:/home";
        }
        Optional<Product> temp = productRepository.findById(id);
        Product product = temp.get();
        if (order == null){
            System.out.println("is Empty");
        }
        order.addProduct(product);
        order.addFinalCost();
        order.addQuantity();
        orderRepository.save(order);
        return "redirect:/home";
    }
}
