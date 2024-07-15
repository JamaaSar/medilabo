import com.service.note.NoteApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = NoteApplication.class)
public class NoteApplicationTest {

    @Autowired
    private NoteApplication noteApplication;

    @Test
    public void contextLoads() {
        assertThat(noteApplication).isNotNull();
    }

    @Test
    public void main() {

        noteApplication.main(new String[] {});

    }
}
