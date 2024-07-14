import com.service.patient.PatientApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = PatientApplication.class)
public class PatientApplicationTests {

    @Autowired
    private PatientApplication patientApplication;

    @Test
    public void contextLoads() {
        assertThat(patientApplication).isNotNull();
    }

    @Test
    public void main() {

        patientApplication.main(new String[] {});

    }
}
