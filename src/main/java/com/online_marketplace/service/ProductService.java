package com.online_marketplace.service;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.online_marketplace.exception.ProductNotFoundException;
import com.online_marketplace.mapper.ProductMapper;
import com.online_marketplace.dto.ProductDto;
import com.online_marketplace.exception.ProductAlreadyExistException;
import com.online_marketplace.model.Category;
import com.online_marketplace.model.LocalUser;
import com.online_marketplace.model.Product;
import com.online_marketplace.repository.ProductRepository;
import com.online_marketplace.utils.ImageUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService{
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final UserService userService;

   

    public Product addProduct(ProductDto product, MultipartFile image) throws IOException{
        Optional<Product> opProduct =  productRepository.findByName(product.getName());
        if(opProduct.isPresent()){
            throw new ProductAlreadyExistException("the product withe name " + product.getName() + "already exists");
        }
        if (image != null) {
            product.setImageName(StringUtils.cleanPath(image.getOriginalFilename()));
            product.setImageType(image.getContentType());
            product.setImageData(/*ImageUtils.compressImage(*/image.getBytes())/*)*/;
            if (categoryService.findCategoryById(product.getCategoryId()) != null) {
                Category category =  categoryService.findCategoryById(product.getCategoryId());
                if (userService.findUserById(product.getSellerId()) != null) {
                    LocalUser seller = userService.findUserById(product.getSellerId());
                    Product productEntity =  ProductMapper.mappToEntity(product, category, seller, false);
                    return productRepository.save(productEntity);
                } else return null;
            }else{
                return null;
            } 
        }

        return null;
    }

    public Stream<Product> findAllProducts(){
        return productRepository.findAll().stream();
    }

    public Product findProductById(Long id){
        return productRepository.findById(id)
        .orElseThrow(()-> new ProductNotFoundException("Product by id " + id + "was not found"));
    }

    public Product updateProduct(ProductDto product, MultipartFile image) throws IOException{
        if(productRepository.findById(product.getId()) != null){
            if (image != null) {
                product.setImageName(image.getOriginalFilename());
                product.setImageType(image.getContentType());
                product.setImageData(/*ImageUtils.compressImage(*/image.getBytes()/* )*/);
            }
            if (categoryService.findCategoryById(product.getCategoryId()) != null) {
                Category category =  categoryService.findCategoryById(product.getCategoryId());
                if (userService.findUserById(product.getSellerId()) != null) {
                    LocalUser seller = userService.findUserById(product.getSellerId());
                    return productRepository.save(ProductMapper.mappToEntity(product, category, seller, true));
                }
            }

        }
        return null;
       
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
}
