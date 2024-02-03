package app.portfoliojofregf.vercel.service;

import app.portfoliojofregf.vercel.dto.ProductRequest;
import app.portfoliojofregf.vercel.dto.ProductResponse;
import java.util.List;

public interface IProductService {
    public void createProduct(ProductRequest productRequest);
    public List<ProductResponse> getAllProduts();
    public ProductResponse getProductByIdOrName(Long product_Id, String product_name);
    public void deleteProduct(Long product_Id);
    public void updateProduct(Long product_Id, ProductRequest productRequest);
    public List<ProductResponse> productSortByPrice(String sorted);
}
