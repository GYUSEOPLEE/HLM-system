package kr.co.hlm.system.kickboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class KickboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getKickboards() {
    }

    @Test
    void testGetKickboards() throws Exception {
        Kickboard kickboard = new Kickboard();
        kickboard.setNo("1");
        kickboard.setActivation('Y');
        String content = objectMapper.writeValueAsString(kickboard);
        mockMvc.perform(post("/kickboards")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(kickboard.getNo()))
                .andDo(print());
    }

    @Test
    void getKickboard() throws Exception{
        Kickboard kickboard = new Kickboard();
        kickboard.setNo("1");
        kickboard.setActivation('Y');
        mockMvc.perform(get("/kickboards/1")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(handler().handlerType(KickboardController.class))
                        .andExpect(handler().methodName("getKickboard"))
                        .andDo(print());
    }

    @Test
    void editKickboard() {
    }

    @Test
    void receiveKickboard() {
    }

    @AfterEach
    void tearDown() {
    }
}