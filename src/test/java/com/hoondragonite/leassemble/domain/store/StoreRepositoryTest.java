package com.hoondragonite.leassemble.domain.store;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import com.hoondragonite.leassemble.domain.user.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    @After
    public void cleanUp(){
        storeRepository.deleteAll();
    }

    @Test
    public void 유저의_상점_조회하기(){
        //given
        User testUser = User.builder()
                .name("테스트")
                .email("abc@naver.com")
                .build();
        //when
        Store store = Store.builder()
                .name("테스트 상점")
                .tel("123-1234")
                .info("테스트 상점 소개내용 입니다.")
                .status("정상영업")
                .ownerUser(testUser)
                .build();

        //then
        assertThat(store.getOwnerUser().getName()).isEqualTo("테스트");
    }
}
