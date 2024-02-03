package app.portfoliojofregf.vercel.service;

import app.portfoliojofregf.vercel.dto.ProductRequest;
import app.portfoliojofregf.vercel.dto.ProductResponse;
import app.portfoliojofregf.vercel.exception.NotFoundException;
import app.portfoliojofregf.vercel.model.Product;
import app.portfoliojofregf.vercel.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService implements IProductService{
    @Autowired
    private ProductRepository productRepository;


    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    @Override
    public List<ProductResponse> getAllProduts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(product -> mapToProductResponse(product)).toList();
    }

    @Override
    public ProductResponse getProductByIdOrName(Long productId, String productName) {
        Optional<Product> optionalProduct = productRepository.findByIdOrNameContainingIgnoreCase(productId, productName);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return mapToProductResponse(product);
        } else {
            throw new NotFoundException("Producto no encontrado");
        }
    }

    @Override
    public void deleteProduct(Long product_Id) {
        Optional<Product> product = productRepository.findById(product_Id);
        if(product.isPresent()){
            productRepository.deleteById(product_Id);
        } else {
            throw new NotFoundException("Producto no encontrado con ID: " + product_Id);
        }
        log.info("Product {} deleted successfully", product_Id);
    }

    @Override
    public void updateProduct(Long product_Id, ProductRequest productRequest) {
        Optional<Product> optionalProduct = productRepository.findById(product_Id);
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get().builder()
                    .id(product_Id)
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .quantity(productRequest.getQuantity())
                    .build();
            productRepository.save(product);
        } else {
            throw new NotFoundException("Producto no encontrado con ID: " + product_Id);
        }
    }

    @Override
    public List<ProductResponse> productSortByPrice(String sorted) {
        Sort sort = "ASC".equalsIgnoreCase(sorted)? Sort.by(Sort.Order.asc("price")):
                    "DESC".equalsIgnoreCase(sorted)? Sort.by(Sort.Order.desc("price")):
                    Sort.unsorted();
        List<Product> productList = productRepository.findAll(sort);
        return productList.stream().map(product -> mapToProductResponse(product)).toList();
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }



    public void createInitialProducts(){
        createProduct(ProductRequest.builder()
                .name("Producto1")
                .description("Primer producto creado")
                .price(new BigDecimal(1500))
                .quantity(3)
                .build()
        );
        createProduct(ProductRequest.builder()
                .name("Producto2")
                .description("Segundo producto creado")
                .price(new BigDecimal(24000))
                .quantity(6)
                .build()
        );
        createProduct(ProductRequest.builder()
                .name("Producto3")
                .description("Tercer producto creado")
                .price(new BigDecimal(2581.35))
                .quantity(9)
                .build()
        );
    }
}
