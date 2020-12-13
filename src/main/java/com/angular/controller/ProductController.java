package com.angular.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angular.dto.Message;
import com.angular.dto.ProductRequestDto;
import com.angular.model.Product;
import com.angular.service.ProductService;


@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
	
    @Autowired
    ProductService productService;

    @GetMapping("/lista")
    public ResponseEntity<List<Product>> listAll(){
        List<Product> list = productService.listaAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detalhe/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") long id){
        if(!productService.existsById(id))
            return new ResponseEntity(new Message("Não Existe"), HttpStatus.NOT_FOUND);
        Product producto = productService.getById(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }
    
    @GetMapping("/name/{id}")
    public ResponseEntity<Product> getByNombre(@PathVariable("id") String name){
        if(!productService.existsByName(name))
            return new ResponseEntity(new Message("Não existe"), HttpStatus.NOT_FOUND);
        Product product = productService.getByName(name).get();
        return new ResponseEntity(product, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductRequestDto productDto){
        if(StringUtils.isBlank(productDto.getName()))
            return new ResponseEntity(new Message("É um nome obrigatório"), HttpStatus.BAD_REQUEST);
        if(productDto.getPrice() < 0 )
            return new ResponseEntity(new Message("Preço deve ser maior que 0"), HttpStatus.BAD_REQUEST);
        if(productService.existsByName(productDto.getName()))
            return new ResponseEntity(new Message("Já existe"), HttpStatus.BAD_REQUEST);
        
        Product product = new Product(null, productDto.getName(), productDto.getPrice());
        productService.saveByProduct(product);
        return new ResponseEntity(new Message("Produto criado"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")long id, @RequestBody ProductRequestDto productDto){
        if(!productService.existsById(id))
            return new ResponseEntity(new Message("Não existe"), HttpStatus.NOT_FOUND);
        if(productService.existsByName(productDto.getName()) && productService.getByName(productDto.getName()).get().getId() != id)
            return new ResponseEntity(new Message("Já existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productDto.getName()))
            return new ResponseEntity(new Message("Nome obrigatório"), HttpStatus.BAD_REQUEST);
        if(productDto.getPrice() < 0 )
            return new ResponseEntity(new Message("Preço maior que 0"), HttpStatus.BAD_REQUEST);

        Product product = productService.getById(id).get();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        productService.saveByProduct(product);
        return new ResponseEntity(new Message("Produto atualizado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")long id){
        if(!productService.existsById(id))
            return new ResponseEntity(new Message("Não existe"), HttpStatus.NOT_FOUND);
        productService.deleteByProduct(id);
        return new ResponseEntity(new Message("Produto removido"), HttpStatus.OK);
    }

}
