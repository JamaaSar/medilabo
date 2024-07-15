import com.service.note.DataLoader;
import com.service.note.entity.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DataLoaderTest {

    @Mock
    ApplicationContext context;

    @Mock
    MongoTemplate mongoTemplate;

    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private DataLoader dataLoader;

    @BeforeEach
    public void setUp() {
        when(context.getBean(MongoTemplate.class)).thenReturn(mongoTemplate);
    }

    @Test
    public void testRun_createsCollectionAndLoadsData() throws Exception {
        when(mongoTemplate.collectionExists("note")).thenReturn(false);
        when(mongoTemplate.count(any(Query.class), eq(Note.class))).thenReturn(0L);

        dataLoader.run();

        verify(mongoTemplate, times(1)).createCollection("note");
        verify(mongoTemplate, times(1)).insertAll(any(List.class));
    }

    @Test
    public void testRun_collectionExistsAndDataNotEmpty() throws Exception {
        when(mongoTemplate.collectionExists("note")).thenReturn(true);
        when(mongoTemplate.count(any(Query.class), eq(Note.class))).thenReturn(10L);

        dataLoader.run();

        verify(mongoTemplate, never()).createCollection("note");
        verify(mongoTemplate, never()).insertAll(any(List.class));
    }
}

