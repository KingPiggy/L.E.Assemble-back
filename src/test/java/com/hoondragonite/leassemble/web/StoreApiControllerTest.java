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
        userRepository.save(User.builder()
                .name("테스트")
                .email("a@naver.com")
                .picture("none")
                .role(Role.USER)
                .build()
        );

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
        userRepository.save(User.builder()
                .name("테스트")
                .email("a@naver.com")
                .picture("none")
                .role(Role.USER)
                .build()
        );

        User testUser = userRepository.findAll().get(0);
        SessionUser sessionUser = new SessionUser(testUser);

        StoreSaveRequestDto dto = StoreSaveRequestDto.builder()
                .name("상점")
                .info("정보")
                .tel("0101")
                .status("영업")
                .ownerUser(testUser)
                .build();

        //when
        String url = "http://localhost:" + port + "/api/user/stores";
        mockHttpSession.setAttribute("user", sessionUser);


        //then
        // new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString("DTO객체");

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

    }
}