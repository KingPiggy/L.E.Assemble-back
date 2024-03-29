package com.hoondragonite.leassemble.web;

import com.hoondragonite.leassemble.config.auth.LoginUser;
import com.hoondragonite.leassemble.config.auth.dto.SessionUser;
import com.hoondragonite.leassemble.service.ProductService;
import com.hoondragonite.leassemble.service.StoreEventsService;
import com.hoondragonite.leassemble.service.StoreService;
import com.hoondragonite.leassemble.web.dto.ProductResponseDto;
import com.hoondragonite.leassemble.web.dto.StoreEventsResponseDto;
import com.hoondragonite.leassemble.web.dto.StoreResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MyStoreController {

    private final StoreService storeService;
    private final ProductService productService;
    private final StoreEventsService storeEventsService;

    @GetMapping("/my-store/{id}")
    public String myStoreInfo(Model model, @LoginUser SessionUser user, @PathVariable Long id) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserImg", user.getPicture());
        }

        StoreResponseDto store = storeService.findById(id);
        model.addAttribute("store", store);

        return "my-store-info";
    }

    @GetMapping("/my-store/{id}/products")
    public String myStoreProducts(Model model, @LoginUser SessionUser user, @PathVariable Long id) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserImg", user.getPicture());
        }

        StoreResponseDto store = storeService.findById(id);
        model.addAttribute("store", store);

        List<ProductResponseDto> products = productService.findAllProductsByStoreId(id);
        model.addAttribute("products", products);

        return "my-store-products";
    }

    @GetMapping("/my-store/{id}/store-events")
    public String myStoreEvents(Model model, @LoginUser SessionUser user, @PathVariable Long id) {
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserImg", user.getPicture());
        }

        StoreResponseDto store = storeService.findById(id);
        model.addAttribute("store", store);

        List<StoreEventsResponseDto> storeEvents = storeEventsService.findAllStoreEventsByStoreId(id);
        model.addAttribute("storeEvents", storeEvents);

        return "my-store-events";
    }
}
