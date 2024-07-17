package com.service.gateway;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = GatewayApplication.class)
class GatewayApplicationTest {
    @Autowired
    private GatewayApplication gatewayApplication;

    @Test
    public void contextLoads() {
        assertThat(gatewayApplication).isNotNull();
    }

    @Test
    public void main() {

        gatewayApplication.main(new String[] {});

    }

}
