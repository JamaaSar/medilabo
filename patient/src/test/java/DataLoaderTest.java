import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;

import com.service.patient.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DataLoaderTest {

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private DataLoader dataLoader;

    @BeforeEach
    public void setUp() throws Exception {
        when(dataSource.getConnection()).thenReturn(connection);
    }

    @Test
    public void testRunExecutesSqlScriptWhenDataIsEmpty() throws Exception {
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(0);

        dataLoader.run();

        verify(dataSource).getConnection();
        verify(statement).executeQuery("SELECT COUNT(*) FROM patient");
        verify(resultSet).getInt(1);
        verify(connection).close();
    }

    @Test
    public void testRunDoesNotExecuteSqlScriptWhenNoNext() throws Exception {
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(false);

        dataLoader.run();

        verify(dataSource).getConnection();
        verify(statement).executeQuery("SELECT COUNT(*) FROM patient");
        verify(connection).close();
    }

    @Test
    public void testRunDoesNotExecuteSqlScriptWhenDataIsNotEmpty() throws Exception {
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);

        dataLoader.run();

        verify(dataSource).getConnection();
        verify(statement).executeQuery("SELECT COUNT(*) FROM patient");
        verify(resultSet).getInt(1);
        verify(connection).close();
    }

    @Test
    public void testRunThrowsRuntimeExceptionOnError() throws Exception {

        when(dataSource.getConnection()).thenThrow(new RuntimeException("Connection error"));

        try {
            dataLoader.run();
        } catch (RuntimeException e) {
            assertEquals("Failed to execute data.sql script", e.getMessage());
            assertEquals("Connection error", e.getCause().getMessage());
        }
    }
}

