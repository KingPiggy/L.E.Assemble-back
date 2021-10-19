package com.hoondragonite.leassemble.web;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hoondragonite.leassemble.config.auth.dto.SessionUser;
import com.hoondragonite.leassemble.domain.events.StoreEvents;
import com.hoondragonite.leassemble.domain.events.StoreEventsRepository;
import com.hoondragonite.leassemble.domain.product.Product;
import com.hoondragonite.leassemble.domain.store.Store;
import com.hoondragonite.leassemble.domain.store.StoreRepository;
import com.hoondragonite.leassemble.domain.user.Role;
import com.hoondragonite.leassemble.domain.user.User;
import com.hoondragonite.leassemble.domain.user.UserRepository;
import com.hoondragonite.leassemble.web.dto.StoreEventsSaveRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StoreEventsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreEventsRepository storeEventsRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
    protected MockHttpSession mockHttpSession;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        mockHttpSession = new MockHttpSession();

        makeUserAndStore();
    }

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

    @After
    public void tearDown() throws Exception {
        storeEventsRepository.deleteAll();
        storeRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 상점의_이벤트하나를_조회한다() throws Exception {
        // given
        User testUser = userRepository.findAll().get(0);
        SessionUser sessionUser = new SessionUser(testUser);
        Store testStore = storeRepository.findByOwnerUser_Id(sessionUser.getId()).get(0);

        storeEventsRepository.save(StoreEvents.builder()
                .name("이벤트1")
                .info("설1")
                .startDate("2021-10-19")
                .endDate("2021-10-19")
                .build());

        Long toFindStoreEventsId = storeEventsRepository.findAll().get(0).getId();

        // when
        String url = "http://localhost:" + port + "/api/user/stores/"
                + testStore.getId().toString() + "/store-events/" + toFindStoreEventsId.toString();

        // then
        mvc.perform(get(url).session(mockHttpSession))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 상점의_여러_이벤트를_조회한다() throws Exception {
        // given
        User testUser = userRepository.findAll().get(0);
        SessionUser sessionUser = new SessionUser(testUser);
        Store testStore = storeRepository.findByOwnerUser_Id(sessionUser.getId()).get(0);

        storeEventsRepository.save(StoreEvents.builder()
                .name("이벤트1")
                .info("설1")
                .startDate("2021-10-19")
                .endDate("2021-10-19")
                .build());

        storeEventsRepository.save(StoreEvents.builder()
                .name("이벤트2")
                .info("설2")
                .startDate("2021-10-19")
                .endDate("2021-10-19")
                .build());

        // when
        String url = "http://localhost:" + port + "/api/user/stores/"
                + testStore.getId().toString() + "/store-events";

        // then
        mvc.perform(get(url).session(mockHttpSession))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 유저는_상점의_이벤트를_등록한다() throws Exception {
        // given
        User testUser = userRepository.findAll().get(0);
        SessionUser sessionUser = new SessionUser(testUser);
        Store testStore = storeRepository.findByOwnerUser_Id(sessionUser.getId()).get(0);

        StoreEventsSaveRequestDto dto = StoreEventsSaveRequestDto.builder()
                .name("이벤트")
                .info("정보")
                .startDate("2021-10-19")
                .endDate("2021-10-19")
                .build();

        // when
        String url = "http://localhost:" + port + "/api/user/stores/"
                + testStore.getId().toString() + "/store-events";

        // then
        String dtoContent = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(dto);

        mvc.perform(post(url)
                        .session(mockHttpSession)
                        .content(dtoContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        StoreEvents result = storeEventsRepository.findAll().get(0);
        assertThat(result.getName()).isEqualTo("이벤트");
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 유저는_상점의_이벤트를_수정한다() throws Exception {
        // given
        User testUser = userRepository.findAll().get(0);
        SessionUser sessionUser = new SessionUser(testUser);
        Store testStore = storeRepository.findByOwnerUser_Id(sessionUser.getId()).get(0);

        StoreEventsSaveRequestDto dto = StoreEventsSaveRequestDto.builder()
                .name("수정된 이벤트")
                .info("수정된 정보")
                .startDate("2021-10-19")
                .endDate("2021-10-19")
                .build();

        storeEventsRepository.save(StoreEvents.builder()
                .name("이벤트1")
                .info("설1")
                .startDate("2021-10-19")
                .endDate("2021-10-19")
                .build());

        StoreEvents storeEvents = storeEventsRepository.findAll().get(0);
        Long toUpdateStoreEventsId  = storeEvents.getId();

        // when
        String url = "http://localhost:" + port + "/api/user/stores/"
                + testStore.getId().toString() + "/store-events/" + toUpdateStoreEventsId.toString();

        // then
        String dtoContent = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(dto);

        mvc.perform(put(url)
                        .session(mockHttpSession)
                        .content(dtoContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        StoreEvents result = storeEventsRepository.findAll().get(0);
        assertThat(result.getName()).isEqualTo("수정된 이벤트");
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 유저는_상점의_이벤트를_삭제한다() throws Exception {
        // given
        User testUser = userRepository.findAll().get(0);
        SessionUser sessionUser = new SessionUser(testUser);
        Store testStore = storeRepository.findByOwnerUser_Id(sessionUser.getId()).get(0);

        storeEventsRepository.save(StoreEvents.builder()
                .name("이벤트1")
                .info("설1")
                .startDate("2021-10-19")
                .endDate("2021-10-19")
                .build());

        StoreEvents storeEvents = storeEventsRepository.findAll().get(0);
        Long toDeleteStoreEventsId  = storeEvents.getId();

        // when
        String url = "http://localhost:" + port + "/api/user/stores/"
                + testStore.getId().toString() + "/store-events/" + toDeleteStoreEventsId.toString();

        // then
        mvc.perform(delete(url)
                        .session(mockHttpSession))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(storeEventsRepository.count()).isEqualTo(0);
    }
}
