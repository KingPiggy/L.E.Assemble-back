package com.hoondragonite.leassemble.domain.store;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import com.hoondragonite.leassemble.domain.user.Role;
import com.hoondragonite.leassemble.domain.user.User;
import com.hoondragonite.leassemble.domain.user.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StoreRepository storeRepository;

    @After
    public void cleanUp() {
        storeRepository.deleteAll();
    }

    @Test
    public void 상점생성_후_불러오기() {
        //given
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

        //when
        List<Store> storeList = storeRepository.findAll();

        //then
        assertThat(storeList.get(0).getName()).isEqualTo("테스트 상점");
        System.out.println(storeList.get(0).getName());
        System.out.println(storeList.get(0).getTel());
    }
}
