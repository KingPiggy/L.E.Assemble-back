package com.hoondragonite.leassemble.service;

import com.hoondragonite.leassemble.domain.product.Product;
import com.hoondragonite.leassemble.domain.product.ProductRepository;
import com.hoondragonite.leassemble.domain.store.Store;
import com.hoondragonite.leassemble.domain.store.StoreRepository;
import com.hoondragonite.leassemble.web.dto.ProductResponseDto;
import com.hoondragonite.leassemble.web.dto.ProductSaveRequestDto;
import com.hoondragonite.leassemble.web.dto.ProductUpdateRequestDto;
import com.hoondragonite.leassemble.web.dto.StoreResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final StoreRepository storeRepository;

    //코드 개선 필요
    @Transactional(readOnly = true)
    public List<ProductResponseDto> findAllProductsByStoreId(Long storeId) {
        List<Product> productList = productRepository.findByStore_Id(storeId);
        List<ProductResponseDto> responseDtos = new ArrayList<>();

        for (Product p : productList) {
            responseDtos.add(new ProductResponseDto(p));
        }

        return responseDtos;
    }

    @Transactional(readOnly = true)
    public ProductResponseDto findById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품은 없습니다. id = " + productId));
        return new ProductResponseDto(product);
    }

    @Transactional
    public Long save(ProductSaveRequestDto dto, Long storeId) {
        // DTO의 상점 정보 업데이트 후 저장
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상점은 없습니다. id = " + storeId));
        dto.setStore(store);

        return productRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ProductUpdateRequestDto dto){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품은 없습니다. id = " + id));

        product.update(dto);
        return product.getId();
    }
}
