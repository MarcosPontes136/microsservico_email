package com.microsservico_email;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootTest
class MicrosservicoEmailApplicationTests {
	
    @Mock
    private ConfigurableApplicationContext mockCtx;

    @Mock
    private Environment mockEnv;

    @Mock
    private Logger mockLogger;
    
    @Test
    public void contextLoads() {
        assertThat(true).isTrue();
    }


    @Test
    public void testMain() {
        assertThatCode(() -> {
        	MicrosservicoEmailApplication.main(new String[]{});
        }).doesNotThrowAnyException();
    }


}
