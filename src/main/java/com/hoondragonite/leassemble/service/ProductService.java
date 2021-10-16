package com.hoondragonite.leassemble.service;

import com.hoondragonite.leassemble.domain.product.Product;
import com.hoondragonite.leassemble.domain.product.ProductRepository;
import com.hoondragonite.leassemble.domain.store.Store;
import com.hoondragonite.leassemble.web.dto.ProductResponseDto;
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
}
