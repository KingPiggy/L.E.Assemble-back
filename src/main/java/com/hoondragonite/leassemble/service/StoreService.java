package com.hoondragonite.leassemble.service;

import com.hoondragonite.leassemble.domain.posts.Posts;
import com.hoondragonite.leassemble.domain.store.Store;
import com.hoondragonite.leassemble.domain.store.StoreRepository;
import com.hoondragonite.leassemble.web.dto.PostsResponseDto;
import com.hoondragonite.leassemble.web.dto.StoreResponseDto;
import com.hoondragonite.leassemble.web.dto.StoreSaveRequestDto;
import com.hoondragonite.leassemble.web.dto.StoreUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public Long save(StoreSaveRequestDto requestDto) {
        return storeRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, StoreUpdateRequestDto requestDto){
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상점은 없습니다. id = " + id));
        store.update(requestDto.getName(), requestDto.getInfo(), requestDto.getTel(), requestDto.getStatus());

        return id;
    }

    public StoreResponseDto findByUId(Long id) {
        Store entity = storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상점은 없습니다. id = " + id));

        return new StoreResponseDto(entity);
    }

    // 유저는 상점을 가진다.
    // 상점은 유저를 안다.
}
