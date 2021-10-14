package com.hoondragonite.leassemble.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hoondragonite.leassemble.config.auth.dto.SessionUser;
import com.hoondragonite.leassemble.domain.store.Store;
import com.hoondragonite.leassemble.domain.store.StoreRepository;
import com.hoondragonite.leassemble.domain.user.Role;
import com.hoondragonite.leassemble.domain.user.User;
import com.hoondragonite.leassemble.domain.user.UserRepository;
import com.hoondragonite.leassemble.web.dto.StoreSaveRequestDto;
import com.hoondragonite.leassemble.web.dto.StoreUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
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
public class StoreApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

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

        createUser();
    }

    public void createUser(){
        userRepository.save(User.builder()
                .name("테스트")
                .email("a@naver.com")
                .picture("none")
                .role(Role.USER)
                .build()
        );
    }

    @After
    public void tearDown() throws Exception {
        storeRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 유저는_여러상점을_갖고있다() throws Exception {
        //given
        User testUser = userRepository.findAll().get(0);
        SessionUser sessionUser = new SessionUser(testUser);

        storeRepository.save(Store.builder()
                .name("test1")
                .ownerUser(testUser)
                .build()
        );

        storeRepository.save(Store.builder()
                .name("test2")
                .ownerUser(testUser)
                .build()
        );

        //when
        String url = "http://localhost:" + port + "/api/user/stores";
        mockHttpSession.setAttribute("user", sessionUser);

        //then
        mvc.perform(get(url).session(mockHttpSession))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 유저는_상점을_만든다() throws Exception {
        //given
        User testUser = userRepository.findAll().get(0);
        SessionUser sessionUser = new SessionUser(testUser);

        StoreSaveRequestDto dto = StoreSaveRequestDto.builder()
                .name("상점")
                .info("정보")
                .tel("0101")
                .status("영업중")
                //.ownerUser() // 클라이언트에서 User 빼고 전송하는 상황
                .build();

        //when
        String url = "http://localhost:" + port + "/api/user/stores";
        mockHttpSession.setAttribute("user", sessionUser);


        //then
        String dtoContent = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(dto);

        mvc.perform(post(url)
                        .session(mockHttpSession)
                        .content(dtoContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 상점을_수정한다() throws Exception {
        // given
        User testUser = userRepository.findAll().get(0);
        SessionUser sessionUser = new SessionUser(testUser);

        storeRepository.save(Store.builder()
                .name("test1")
                .info("정보")
                .tel("1234")
                .ownerUser(testUser)
                .build()
        );

        StoreUpdateRequestDto dto = StoreUpdateRequestDto.builder()
                .name("수정된 test1")
                .info("수정된 정보")
                .tel("1234")
                .status("영업중")
                .build();

        Store store = storeRepository.findAll().get(0);

        // when
        String url = "http://localhost:" + port + "/api/user/stores" + "/" + store.getId().toString();
        mockHttpSession.setAttribute("user", sessionUser);


        //then
        String dtoContent = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(dto);

        mvc.perform(put(url)
                        .session(mockHttpSession)
                        .content(dtoContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        Store resultStore = storeRepository.findAll().get(0);

        assertThat(resultStore.getName()).isEqualTo("수정된 test1");
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 상점을_삭제한다() throws Exception {
        // given
        User testUser = userRepository.findAll().get(0);
        SessionUser sessionUser = new SessionUser(testUser);

        storeRepository.save(Store.builder()
                .name("test1")
                .info("정보")
                .tel("1234")
                .ownerUser(testUser)
                .build()
        );

        Store store = storeRepository.findAll().get(0);

        // when
        String url = "http://localhost:" + port + "/api/user/stores" + "/" + store.getId().toString();
        mockHttpSession.setAttribute("user", sessionUser);

        //then
        mvc.perform(delete(url)
                        .session(mockHttpSession))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(storeRepository.count()).isEqualTo(0);
    }
}