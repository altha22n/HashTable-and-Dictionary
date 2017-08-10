import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * DictionaryEntryTest
 *
 * JUnit 4 Tester for DictionaryEntry
 * COMSC 211 - Hashing PSA
 *
 * Created by Peter Klemperer on March 22, 2017.
 */
public class DictionaryEntryTest {
    DictionaryEntry<String,String> dogEntry;
    DictionaryEntry<String,String> catEntry;
    DictionaryEntry<String,String> appleEntry;

    @Before
    public void setUp() {
        dogEntry = new DictionaryEntry<>("dog", "barky land mammal");
        catEntry = new DictionaryEntry<>("cat", "purry land mammal");
        appleEntry = new DictionaryEntry<>("apple", "red fruit plant");
    }

    @Test
    public void getKey() {
        assertEquals("dog", dogEntry.getKey());
        assertEquals("cat", catEntry.getKey());
    }

    @Test
    public void getValue() {
        assertEquals("barky land mammal", dogEntry.getValue());
        assertEquals("purry land mammal", catEntry.getValue());
    }

    @Test
    public void hasKey() {
        assertTrue(dogEntry.hasKey("dog"));
        assertTrue(catEntry.hasKey("cat"));

        assertFalse(dogEntry.hasKey("cat"));
        assertFalse(catEntry.hasKey("dog"));
    }

}