package com.hoondragonite.leassemble.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hoondragonite.leassemble.domain.events.StoreEvents;
import com.hoondragonite.leassemble.domain.events.StoreEventsItem;
import com.hoondragonite.leassemble.domain.events.StoreEventsItemRepository;
import com.hoondragonite.leassemble.domain.events.StoreEventsRepository;
import com.hoondragonite.leassemble.domain.store.Store;
import com.hoondragonite.leassemble.domain.store.StoreRepository;
import com.hoondragonite.leassemble.domain.user.Role;
import com.hoondragonite.leassemble.domain.user.User;
import com.hoondragonite.leassemble.domain.user.UserRepository;
import com.hoondragonite.leassemble.web.dto.StoreEventsItemSaveRequestDto;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StoreEventsItemApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreEventsRepository storeEventsRepository;

    @Autowired
    private StoreEventsItemRepository storeEventsItemRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        makeUserAndStoreAndStoreEvents();
    }

    public void makeUserAndStoreAndStoreEvents() {
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

        Store store = storeRepository.findAll().get(0);

        storeEventsRepository.save(StoreEvents.builder()
                .name("테스트 이벤트")
                .info("테스트 설명")
                .startDate("2021-10-20")
                .endDate("2021-10-20")
                .store(store)
                .build());
    }

    @After
    public void tearDown() throws Exception {
        storeEventsItemRepository.deleteAll();
        storeEventsRepository.deleteAll();
        storeRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 상점이벤트의_이벤트상품_하나를_조회한다() throws Exception {
        Long storeId = storeRepository.findAll().get(0).getId();

        StoreEvents storeEvents = storeEventsRepository.findAll().get(0);
        Long storeEventsId = storeEvents.getId();

        Long toFindStoreEventsItemId = storeEventsItemRepository.save(StoreEventsItem.builder()
                .name("이벤트 상품1")
                .info("gd")
                .qty(10)
                .price(10000)
                .storeEvents(storeEvents)
                .build()).getId();

        // when
        String url = "http://localhost:" + port + "/api/user/stores/"
                + storeId.toString() + "/store-events/" + storeEventsId.toString()
                + "/store-events-items/" + toFindStoreEventsItemId.toString();

        // then
        mvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 상점이벤트의_이벤트상품_모두를_조회한다() throws Exception {
        Long storeId = storeRepository.findAll().get(0).getId();

        StoreEvents storeEvents = storeEventsRepository.findAll().get(0);
        Long storeEventsId = storeEvents.getId();

        storeEventsItemRepository.save(StoreEventsItem.builder()
                .name("event item1")
                .info("gd")
                .qty(10)
                .price(10000)
                .storeEvents(storeEvents)
                .build());

        storeEventsItemRepository.save(StoreEventsItem.builder()
                .name("event item2")
                .info("gdgd")
                .qty(20)
                .price(20000)
                .storeEvents(storeEvents)
                .build());

        // when
        String url = "http://localhost:" + port + "/api/user/stores/"
                + storeId.toString() + "/store-events/" + storeEventsId.toString()
                + "/store-events-items";

        // then
        mvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 유저는_상점이벤트에_여러_이벤트상품을_등록한다() throws Exception {
        Long storeId = storeRepository.findAll().get(0).getId();
        Long storeEventsId = storeEventsRepository.findAll().get(0).getId();

        List<StoreEventsItemSaveRequestDto> dtoList = new ArrayList<>();
        dtoList.add(StoreEventsItemSaveRequestDto.builder()
                .name("event item1")
                .info("gd")
                .qty(10)
                .price(10000)
                .build());
        dtoList.add(StoreEventsItemSaveRequestDto.builder()
                .name("event item2")
                .info("gdgd")
                .qty(20)
                .price(20000)
                .build());
        dtoList.add(StoreEventsItemSaveRequestDto.builder()
                .name("event item3")
                .info("gdgdgd")
                .qty(30)
                .price(30000)
                .build());

        // when
        String url = "http://localhost:" + port + "/api/user/stores/"
                + storeId.toString() + "/store-events/" + storeEventsId.toString()
                + "/store-events-items";

        // then
        String dtoContent = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(dtoList);

        mvc.perform(post(url)
                        .content(dtoContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        System.out.println(">>>>>>>>>>>>>>>>");
        System.out.println(storeEventsItemRepository.findAll().size());
    }


}