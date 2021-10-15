package com.hoondragonite.leassemble.service;

import com.hoondragonite.leassemble.domain.store.Store;
import com.hoondragonite.leassemble.domain.store.StoreRepository;
import com.hoondragonite.leassemble.domain.user.User;
import com.hoondragonite.leassemble.domain.user.UserRepository;
import com.hoondragonite.leassemble.web.dto.StoreResponseDto;
import com.hoondragonite.leassemble.web.dto.StoreSaveRequestDto;
import com.hoondragonite.leassemble.web.dto.StoreUpdateRequestDto;
import com.hoondragonite.leassemble.web.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    private final UserRepository userRepository;

    @Transactional
    public Long save(StoreSaveRequestDto requestDto, UserRequestDto userDto) {
        // DTO의 사용자 정보 업데이트 후 저장
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 없습니다. id = " + userDto.getId()));
        requestDto.setOwnerUser(user);

        return storeRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, StoreUpdateRequestDto requestDto) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상점은 없습니다. id = " + id));
        store.update(requestDto);
        return storeRepository.save(store).getId();
    }

    @Transactional
    public void delete(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상점은 없습니다. id = " + id));
        storeRepository.delete(store);
    }

    @Transactional(readOnly = true)
    public StoreResponseDto findById(Long id) {
        Store entity = storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상점은 없습니다. id = " + id));
        return new StoreResponseDto(entity);
    }

    //코드 개선 필요
    @Transactional(readOnly = true)
    public List<StoreResponseDto> findAllByUserId(Long ownerUserId) {
        List<Store> storeList = storeRepository.findByOwnerUser_Id(ownerUserId);
        List<StoreResponseDto> responseDtos = new ArrayList<>();

        for (Store s : storeList) {
            responseDtos.add(new StoreResponseDto(s));
        }

        return responseDtos;
    }
}
