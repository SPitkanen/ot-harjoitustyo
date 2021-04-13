
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import wbapp.dao.acDataDao;
import java.sql.*;

/**
 *
 * @author santeripitkanen
 */
public class DBTest {
    
    public DBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void dbCountCorrect() throws SQLException {
        acDataDao db = new acDataDao();
        int result = db.getCount(2);
        assertEquals(5, result);
    }
    
}
