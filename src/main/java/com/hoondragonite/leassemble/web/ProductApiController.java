package com.hoondragonite.leassemble.web;

import com.hoondragonite.leassemble.config.auth.LoginUser;
import com.hoondragonite.leassemble.config.auth.dto.SessionUser;
import com.hoondragonite.leassemble.service.ProductService;
import com.hoondragonite.leassemble.web.dto.ProductResponseDto;
import com.hoondragonite.leassemble.web.dto.StoreResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/api/user/stores/{storeId}")
@RestController
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;

    @GetMapping("/products")
    List<ProductResponseDto> findAllProductsByStoreId(@PathVariable Long storeId){
        return productService.findAllProductsByStoreId(storeId);
    }

}
