package com.hoondragonite.leassemble.web;

import com.hoondragonite.leassemble.config.auth.dto.SessionUser;
import com.hoondragonite.leassemble.domain.product.Product;
import com.hoondragonite.leassemble.domain.product.ProductRepository;
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
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

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
        productRepository.deleteAll();
        storeRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 상점의_상품하나를_조회한다() throws Exception {
        // given
        User testUser = userRepository.findAll().get(0);
        SessionUser sessionUser = new SessionUser(testUser);
        Store testStore = storeRepository.findByOwnerUser_Id(sessionUser.getId()).get(0);

        productRepository.save(Product.builder()
                .name("상품1")
                .info("정보1")
                .price(10000)
                .store(testStore)
                .build());

        Long toFindProductId = productRepository.findAll().get(0).getId();

        // when
        String url = "http://localhost:" + port + "/api/user/stores/"
                + testStore.getId().toString() + "/products/" + toFindProductId.toString();
        mockHttpSession.setAttribute("user", sessionUser);

        // then
        mvc.perform(get(url).session(mockHttpSession))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 상점의_여러상품을_조회한다() throws Exception {
        // given
        User testUser = userRepository.findAll().get(0);
        SessionUser sessionUser = new SessionUser(testUser);
        Store testStore = storeRepository.findByOwnerUser_Id(sessionUser.getId()).get(0);

        productRepository.save(Product.builder()
                .name("상품1")
                .info("정보1")
                .price(10000)
                .store(testStore)
                .build());

        productRepository.save(Product.builder()
                .name("상품2")
                .info("정보2")
                .price(20000)
                .store(testStore)
                .build());

        // when
        String url = "http://localhost:" + port + "/api/user/stores/"
                + testStore.getId().toString() + "/products";
        mockHttpSession.setAttribute("user", sessionUser);

        // then
        mvc.perform(get(url).session(mockHttpSession))
                .andDo(print())
                .andExpect(status().isOk());
    }
}