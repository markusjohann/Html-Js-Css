package com.productmanager.controller;

import com.productmanager.entity.Product;
import com.productmanager.entity.ProductRequest;
import com.productmanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String displayProducts(Model model) {
        model.addAttribute("products", this.productRepository.findAll());

        System.out.println(this.productRepository.findAll());
        return "all-products";
    }

    @GetMapping("/create")
    public String displayAddProductPage() {

        return "add-product";
    }

    @PostMapping("/create-product")
    public String handleCreateProduct(ProductRequest productRequest) {
        Product productToCreate = new Product();

        productToCreate.setName(productRequest.getName());
        productToCreate.setPrice(productRequest.getPrice());
        productToCreate.setQuantity(productRequest.getQuantity());
        productToCreate.setImageUrl(productRequest.getImageUrl());

        this.productRepository.save(productToCreate);
        return "redirect:/";
    }

    @GetMapping("/product/{id}")
    public String handleViewProduct(@PathVariable Long id, Model model) {
        Product current = this.productRepository.findProductById(id);
        model.addAttribute("product", current);
        return "view-product";
    }


    @PutMapping("/product/update/{id}")
    public String handleUpdateProduct(ProductRequest productRequest, Long id, Model model) {
        Product productToUpdate = new Product();

        Product updated = this.productRepository.findProductById(id);
        model.addAttribute("product", updated);

        productToUpdate.setName(productRequest.getName());
        productToUpdate.setPrice(productRequest.getPrice());
        productToUpdate.setQuantity(productRequest.getQuantity());
        productToUpdate.setImageUrl(productRequest.getImageUrl());

        this.productRepository.save(productToUpdate);
        return "update-product";
    }

    @DeleteMapping("/product/delete/{id}")
    @RequestMapping(value="/product/delete/{id}", method={RequestMethod.DELETE,RequestMethod.GET})
    public String handleDeleteProduct(@PathVariable long id) {
       productRepository.deleteById(id);


        return "redirect:/";
    }

}
