package com.lgda.backend.product;

import com.lgda.backend.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + " not found"));
    }

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product, Long id) {
        Product foundProduct = getById(id);

        foundProduct.setName(product.getName());
        foundProduct.setDescription(product.getDescription());
        foundProduct.setThumbnail(product.getThumbnail());
        foundProduct.setIsDiscounted(product.getIsDiscounted());
        foundProduct.setIsAvailable(product.getIsAvailable());
        foundProduct.setPrice(product.getPrice());
        foundProduct.setDiscountPercent(product.getDiscountPercent());

        return productRepository.save(foundProduct);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
