import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * DictionaryChainedHashTableTest
 *
 * JUnit 4 Tester for Chained Hash Dictionary COMSC 211 - Hashing PSA
 *
 * Created by Peter Klemperer on March 22, 2017.
 */
public class DictionaryChainedHashTableTest {

	DictionaryEntry<String, String> appleEntry;
	DictionaryEntry<String, String> catEntry;
	DictionaryEntry<String, String> catEatingDogEntry;
	DictionaryEntry<String, String> dogEntry;

	DictionaryChainedHashTable<String, DictionaryEntry<String, String>> biggerDCST;
	DictionaryChainedHashTable<String, DictionaryEntry<String, String>> tinyDCST;

	@Before
	public void setUp() {
		dogEntry = new DictionaryEntry<>("dog", "barky land mammal");
		catEntry = new DictionaryEntry<>("cat", "purry land mammal");
		appleEntry = new DictionaryEntry<>("apple", "red fruit plant");
		catEatingDogEntry = new DictionaryEntry<>("cat", "bad, bad dog.");

		biggerDCST = new DictionaryChainedHashTable<String, DictionaryEntry<String, String>>(
				27);
		tinyDCST = new DictionaryChainedHashTable<String, DictionaryEntry<String, String>>(
				1);
	}

	@Test
	public void constructor() {
		assertNotNull(appleEntry);
		assertNotNull(catEntry);
		assertNotNull(dogEntry);
		assertNotNull(tinyDCST);
		assertNotNull(tinyDCST.table[0]);
		assertNotNull(biggerDCST);
	}

	@Test
	public void getTableSize() {
		assertEquals(1, tinyDCST.getTableSize());
		assertEquals(27, biggerDCST.getTableSize());
	}

	@Test
	public void hashFunction() {
		assertEquals(0, tinyDCST.hashFunction("apple"));
		assertEquals(0, tinyDCST.hashFunction("cat"));
		assertEquals(0, tinyDCST.hashFunction("dog"));

		assertEquals(1, biggerDCST.hashFunction("apple"));
		assertEquals(3, biggerDCST.hashFunction("cat"));
		assertEquals(4, biggerDCST.hashFunction("dog"));
	}

	@Test
	public void searchTableTinyTable() {
		tinyDCST.table[0].insertFirst(dogEntry);
		tinyDCST.table[0].insertFirst(catEntry);

		int i = tinyDCST.hashFunction("dog");
		assertEquals(dogEntry, tinyDCST.searchTable(i, "dog").getData());
		i = tinyDCST.hashFunction("apple");
		assertNull(tinyDCST.searchTable(i, "apple"));
	}

	@Test
	public void searchTableBiggerTable() {
		// yes, the hash("dog") != 0,
		// but we are testing here!
		biggerDCST.table[0].insertFirst(dogEntry);
		biggerDCST.table[1].insertFirst(catEntry);

		assertEquals(dogEntry, biggerDCST.searchTable(0, "dog").getData());
		assertNull(biggerDCST.searchTable(0, "apple"));

		assertEquals(catEntry, biggerDCST.searchTable(1, "cat").getData());
		assertNull(biggerDCST.searchTable(1, "dog"));
	}

	@Test
	public void insertTinyTable() {
		tinyDCST.insert("cat", catEntry);
		assertEquals(catEntry, tinyDCST.table[0].getFirst());
		tinyDCST.insert("apple", appleEntry);
		assertEquals(appleEntry, tinyDCST.table[0].getFirst());
		// overwrite cat entry with catEatingDogEntry
		// (cat eats dog!)
		tinyDCST.insert("cat", catEatingDogEntry);
		assertEquals(catEatingDogEntry, tinyDCST.table[0].getLast());
	}

	@Test
	public void insertBiggerTable() {
		biggerDCST.insert("cat", catEntry);

		assertEquals(catEntry,
				biggerDCST.table[biggerDCST.hashFunction("cat")].getFirst());
		biggerDCST.insert("apple", appleEntry);
		assertEquals(appleEntry,
				biggerDCST.table[biggerDCST.hashFunction("apple")].getFirst());

		biggerDCST.insert("dog", dogEntry);
		assertEquals(dogEntry,
				biggerDCST.table[biggerDCST.hashFunction("dog")].getLast());
	}

	@Test
	public void searchTinyTable() {
		tinyDCST.insert("cat", catEntry);
		tinyDCST.insert("apple", appleEntry);
		tinyDCST.insert("dog", dogEntry);
		// System.out.println("Before tinyDCST= " + tinyDCST);
		assertEquals(dogEntry, tinyDCST.search("dog"));
		// System.out.println("After tinyDCST= " + tinyDCST.toString());
		assertEquals(catEntry, tinyDCST.search("cat"));
		assertEquals(appleEntry, tinyDCST.search("apple"));

		tinyDCST.insert("cat", catEatingDogEntry);
		assertEquals(catEatingDogEntry, tinyDCST.search("cat"));
	}

	@Test
	public void searchBiggerTable() {
		biggerDCST.insert("cat", catEntry);
		biggerDCST.insert("apple", appleEntry);
		biggerDCST.insert("dog", dogEntry);
		// System.out.println("Before tinyDCST= " + tinyDCST);
		assertEquals(dogEntry, biggerDCST.search("dog"));
		// System.out.println("After tinyDCST= " + tinyDCST.toString());
		assertEquals(catEntry, biggerDCST.search("cat"));
		assertEquals(appleEntry, biggerDCST.search("apple"));

		biggerDCST.insert("cat", catEatingDogEntry);
		assertEquals(catEatingDogEntry, biggerDCST.search("cat"));
	}

	@Test
	public void deleteTinyTable() {
		tinyDCST.insert("cat", catEntry);
		tinyDCST.insert("apple", appleEntry);
		tinyDCST.insert("dog", dogEntry);

		tinyDCST.delete("dog");

		assertNull(tinyDCST.search("dog"));
		assertEquals(catEntry, tinyDCST.search("cat"));
		assertEquals(appleEntry, tinyDCST.search("apple"));
	}

	@Test
	public void deleteBiggerTable() {
		biggerDCST.insert("cat", catEntry);
		biggerDCST.insert("dog", dogEntry);

		biggerDCST.delete("cat");

		assertNull(biggerDCST.search("cat"));
		assertEquals(dogEntry, biggerDCST.search("dog"));

	}
	@Test
	public void getListLengthTest(){
		tinyDCST.insert("dog", dogEntry);
		tinyDCST.insert("apple", appleEntry);
		assertEquals(2,
				tinyDCST.getListLength(0));
		
		biggerDCST.insert("dog", dogEntry);
		biggerDCST.insert("apple", appleEntry);
		assertEquals(1,
				biggerDCST.getListLength(1));
	}
	@Test
	public void getEntryCountTest(){
		tinyDCST.insert("cat", catEntry);
		tinyDCST.insert("apple", appleEntry);
		tinyDCST.insert("dog", dogEntry);
		assertEquals(3,
				tinyDCST.getEntryCount());
	}

}