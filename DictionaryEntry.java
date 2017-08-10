/**
 * Created by peter on 3/22/17.
 */
public class DictionaryEntry<K extends Comparable<K>, V> implements
		Comparable<K> {
	K key;
	V value;

	/**
	 * constructor
	 * 
	 * @param key
	 * @param value
	 */
	public DictionaryEntry(K key, V value) {
		// key and value as this
		this.key = key;
		this.value = value;
	}

	/**
	 * gets Key
	 * 
	 * @return key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * gets value
	 * 
	 * @return value
	 */
	public V getValue() {
		return value;
	}

	/**
	 * checks if hasKey is true or false
	 * 
	 * @param k
	 * @return true or false
	 */
	public boolean hasKey(K k) {
		if (k.equals(key)) {
			return true;
		}
		return false;
	}

	/**
	 * compares key to k
	 */
	public int compareTo(K k) {
		return this.key.compareTo(k);

	}
}
