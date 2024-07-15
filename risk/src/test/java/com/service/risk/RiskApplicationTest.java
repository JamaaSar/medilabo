package com.service.risk;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = RiskApplication.class)
public class RiskApplicationTest {

    @Autowired
    private RiskApplication riskApplication;

    @Test
    public void contextLoads() {
        assertThat(riskApplication).isNotNull();
    }

    @Test
    public void main() {

        riskApplication.main(new String[] {});

    }
}
