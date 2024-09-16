package com.online_marketplace.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.online_marketplace.dto.ProductDto;
import com.online_marketplace.mapper.ProductMapper;
import com.online_marketplace.model.Product;
import com.online_marketplace.response.ProductResponse;
import com.online_marketplace.service.ProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/products")
@SecurityRequirement(name = "bearerAuth")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductDto> addProduct(@RequestPart("product") ProductDto product, @RequestPart("image") MultipartFile image) throws IOException{
        Product createdProduct = productService.addProduct(product, image);
        if (createdProduct == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return new ResponseEntity<>(ProductMapper.mappToProductDto(createdProduct), HttpStatus.CREATED);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<ProductResponse>> findAllProducts(){
        List<ProductResponse> allProducts = (List<ProductResponse>) productService.findAllProducts().map(product ->{
            String fileDownloadUri = ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("/products/files/")
            .path(product.getProductId().toString())
            .toUriString();

            return new ProductResponse(
                product.getProductId(),
                product.getName(),
                product.getShortDescription(),
                product.getLongDescription(),
                product.getPrice(),
                product.getCategory().getCategoryID(),
                product.getUser().getUserId(),
                product.getImageName(),
                fileDownloadUri,
                product.getImageType(),
                product.getImageData().length

            );
        }).collect(Collectors.toList());
        if (allProducts.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    
    @GetMapping("/find/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable("id") Long id){
        Product product = productService.findProductById(id);

        String fileDownloadUri = ServletUriComponentsBuilder
        .fromCurrentContextPath()
        .path("/products/files/")
        .path(product.getProductId().toString())
        .toUriString();

        ProductResponse productResponse =  new  ProductResponse(
            product.getProductId(),
            product.getName(),
            product.getShortDescription(),
            product.getLongDescription(),
            product.getPrice(),
            product.getCategory().getCategoryID(),
            product.getUser().getUserId(),
            product.getImageName(),
            fileDownloadUri,
            product.getImageType(),
            product.getImageData().length

        );

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductDto> updateProduct(@RequestPart("product") ProductDto product, @RequestPart("image") MultipartFile image) throws IOException{
        Product updatedProduct = productService.updateProduct(product, image);
        return new ResponseEntity<>(ProductMapper.mappToProductDto(updatedProduct), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id){
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/files/{id}")
  public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
    Product product = productService.findProductById(id);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + product.getImageName() + "\"")
        .body(/*ImageUtils.decompresser(*/product.getImageData()/*)*/);
  }

}
