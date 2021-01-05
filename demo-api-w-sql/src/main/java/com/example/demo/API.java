package com.example.demo;

import com.example.demo.Repositories.CartsRepo;
import com.example.demo.Repositories.ProductsRepo;
import com.example.demo.doa.Carts;
import com.example.demo.doa.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class API {
    @Autowired
    CartsRepo cartsRepo;

    @Autowired
    ProductsRepo productsRepo;


    @GetMapping("/api/v1/carts")
    public List<Carts> findAllCarts() {
        return cartsRepo.findAll();
    }



    @GetMapping("/api/v1/carts/add")
    public Carts addProduct(@ModelAttribute Carts carts) {
        cartsRepo.save(carts);
        return carts;
    }

    @GetMapping("/api/v1/carts/{id}")
    public Carts findCartById(@PathVariable Integer id) {
        return cartsRepo.findById(id);
    }

    @GetMapping("/api/product/add_to_cart")
    public Carts addToCart(@ModelAttribute Products products, @RequestParam Integer cartId) {
        products.setCarts(cartsRepo.findById(cartId));
        productsRepo.save(products);
        return cartsRepo.findById(cartId);
    }

}
