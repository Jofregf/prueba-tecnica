package app.portfoliojofregf.vercel.controller;

import app.portfoliojofregf.vercel.dto.ProductRequest;
import app.portfoliojofregf.vercel.dto.ProductResponse;
import app.portfoliojofregf.vercel.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear producto", description = "Esto permite crear un producto")
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista de productos", description = "Obtenemos una lista de los productos")
    public List<ProductResponse> getAll(){
        return productService.getAllProduts();
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Buscar producto por Id o Nombre", description = "Permite buscar un producto " +
            "al pasar el N° de Id o el nombre")
    public ProductResponse getProductByIdOrName(
            @RequestParam(name = "productId", required = false) Long productId,
            @RequestParam(name = "productName", required = false) String productName) {
        return productService.getProductByIdOrName(productId, productName);
    }

    @DeleteMapping("/{product_Id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Borrar producto", description = "Borramos un producto al pasar el N° de Id")
    public void deleteProductById(@PathVariable Long product_Id){
        productService.deleteProduct(product_Id);
    }

    @PutMapping("/{product_Id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Actualizar producto", description = "Actualizamos los datos de un producto del Id deseado")
    public void updateProduct(@RequestBody ProductRequest productRequest, @PathVariable Long product_Id){
        productService.updateProduct(product_Id, productRequest);
    }

    @GetMapping("/sort/{sorted}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Ordenar lista de productos",
            description = "Lista de productos ordenados en forma ascendente o descendente según su precio, debemos pasar " +
                    "como parametros la forma de orden, asc (ascendente), desc (descendente) " +
                    "y cualquier otro valor devuelve la lista sin ordenar")
    public List<ProductResponse> sortedProductByPrice(@PathVariable String sorted){
        return productService.productSortByPrice(sorted);
    }
}
