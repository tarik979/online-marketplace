package com.online_marketplace.mapper;

import com.online_marketplace.model.Category;

import com.online_marketplace.dto.ProductDto;
import com.online_marketplace.model.LocalUser;
import com.online_marketplace.model.Product;

public class ProductMapper {
    public static ProductDto  mappToProductDto(Product product){
        ProductDto dto = new ProductDto();
        dto.setId(product.getProductId());
        dto.setSold(product.isSold());
        dto.setName(product.getName());
        dto.setDelete(product.isDelete());
        dto.setShortDescription(product.getShortDescription());
        dto.setLongDescription(product.getLongDescription());
        dto.setPrice(product.getPrice());
        dto.setImageName(product.getImageName());
        dto.setImageType(product.getImageType());
        dto.setImageData(product.getImageData());
        dto.setCategoryId(product.getCategory().getCategoryID());
        dto.setSellerId(product.getUser().getUserId());
        return dto;
    }

    public static Product mappToEntity(ProductDto dto, Category category, LocalUser user, boolean setId){
        Product product = new Product();
        if (setId) {
            product.setProductId(dto.getId());
        }
        product.setName(dto.getName());
        product.setShortDescription(dto.getShortDescription());
        product.setLongDescription(dto.getLongDescription());
        product.setPrice(dto.getPrice());
        product.setDelete(dto.isDelete());
        product.setImageName(dto.getImageName());
        product.setSold(dto.isSold());
        product.setImageType(dto.getImageType());
        product.setImageData(/*ImageUtils.decompresser(*/dto.getImageData())/*)*/;
        product.setCategory(category);
        product.setUser(user);
        return product;
    }
}
