package com.productmanager.controller;

import java.util.Optional;
import com.productmanager.entity.Product;
import com.productmanager.entity.ProductRequest;
import com.productmanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import java.util.HashMap;
import java.util.Map;

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
        Product current = this.productRepository.findProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        model.addAttribute("product", current);
        return "view-product";
    }



    @GetMapping("/product/update/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product productToEdit = this.productRepository.findProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        model.addAttribute("product",productToEdit);
        return "update-product";
    }


    @PostMapping("/product/update")
    public String handleUpdateProduct(@ModelAttribute ("product") Product updatedProduct) {
        Product existingProduct = this.productRepository.findProductById(updatedProduct.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + updatedProduct.getId()));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        existingProduct.setImageUrl(updatedProduct.getImageUrl());

        this.productRepository.save(existingProduct);
        return "redirect:/";
    }

    @RequestMapping("/update")
    public String update(){
        return "redirect:/";
    }



    @DeleteMapping("/product/delete/{id}")
    @RequestMapping(value="/product/delete/{id}", method={RequestMethod.DELETE,RequestMethod.GET})
    public String handleDeleteProduct(@PathVariable long id) {
       productRepository.deleteById(id);
        return "redirect:/";
    }

}
