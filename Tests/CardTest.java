import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void testGetValue() {
        // Check get value matches integer passed into card class
        Card card = new Card(1);
        assertEquals(card.getValue(), 1);
    }

    @Test
    void testToString() {
    }
}