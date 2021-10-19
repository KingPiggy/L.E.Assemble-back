package com.hoondragonite.leassemble.domain.events;

import static org.assertj.core.api.Assertions.assertThat;

import com.hoondragonite.leassemble.domain.store.Store;
import com.hoondragonite.leassemble.domain.store.StoreRepository;
import com.hoondragonite.leassemble.domain.user.Role;
import com.hoondragonite.leassemble.domain.user.User;
import com.hoondragonite.leassemble.domain.user.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreEventsRepositoryTest {
    @Autowired
    StoreEventsRepository storeEventsRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanUp() {
        storeEventsRepository.deleteAll();
    }

    @Before
    public void makeUserAndStore() {
        userRepository.save(User.builder()
                .name("테스트")
                .email("a@naver.com")
                .picture("none")
                .role(Role.USER)
                .build()
        );

        User testUser = userRepository.findAll().get(0);

        storeRepository.save(Store.builder()
                .name("테스트 상점")
                .info("테스트 상점 소개내용 입니다.")
                .status("d영업중")
                .ownerUser(testUser)
                .build());
    }

    @Test
    public void 이벤트_생성후_불러오기() {
        // given
        Store store = storeRepository.findAll().get(0);

        storeEventsRepository.save(StoreEvents.builder()
                .name("이벤트")
                .info("설")
                .startDate("2021-10-19")
                .endDate("2021-10-19")
                .build());

        // when
        StoreEvents storeEvents = storeEventsRepository.findAll().get(0);

        // then
        assertThat(storeEvents.getName()).isEqualTo("이벤트");
    }
}