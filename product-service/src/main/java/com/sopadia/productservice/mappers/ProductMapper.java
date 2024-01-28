package com.sopadia.productservice.mappers;

import com.sopadia.productservice.models.Product;
import com.sopadia.productservice.requestmodels.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class ProductMapper {

    @Mapping(source = "category", target="category", qualifiedByName = "mapCategory")
    @Mapping(target="unite", expression = "java(mapStringToEnum(productDto.getPriceUnit()))")
    public abstract Product toProduct(ProductDto productDto);

    public static Product.PriceUnit mapStringToEnum(String priceUnit){
         for(Product.PriceUnit unit : Product.PriceUnit.values()){
             if(unit.equals(priceUnit)){
                 return unit;
             }
         }
         return Product.PriceUnit.KG;
    }
    @Named("mapCategory")
    @Mapping(source = "familyName", target="family")
    public abstract Product.Category toCategory(ProductDto.CategoryDto categoryDto);

    @Mapping(source = "category", target="category", qualifiedByName = "mapCategoryDto")
    @Mapping( target="priceUnit", expression = "java(mapEnumToString(product.getUnite()))")
    public abstract  ProductDto toProductDto(Product product);

    public String mapEnumToString(Product.PriceUnit unite){
        return unite!=null ? unite.toString() : "KG";
    }
    @Named("mapCategoryDto")
    @Mapping(source = "family", target="familyName")
    public abstract ProductDto.CategoryDto toCategoryDto(Product.Category category);
}
