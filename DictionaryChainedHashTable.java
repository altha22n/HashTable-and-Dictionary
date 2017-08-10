/**
 * Created by peter on 3/22/17.
 * Edited by Nada Al-Thawr 3/28/17
 */
public class DictionaryChainedHashTable<K extends String, V extends Comparable<K>> {
	LinkedList<V> table[];

	public DictionaryChainedHashTable(int n) {
		// n is table size
		table = new LinkedList[n];
		for (int i = 0; i < n; i++) {
			table[i] = new LinkedList<V>();
		}

	}

	/**
	 * searches the table when we are given the index and key
	 * 
	 * @param index
	 * @param key
	 * @return the node containing entry key in the list table[index]
	 */
	public LinkedListNode<V> searchTable(int index, K key) {
		// get the first node from the table
		LinkedListNode<V> current = table[index].getFirstNode();
		// while the current ( first ) node isn't empty
		while (current != null) {
			// if the data is same as the key
			if (current.getData().compareTo(key) == 0) {
				// return current
				return current;
			}
			// keep getting the next node
			current = current.getNext();
		}
		return null;
	}

	/**
	 * inserts in the table a specific key with its value
	 * 
	 * @param key
	 * @param value
	 */
	public void insert(K key, V value) {
		// call hashFunction on the key to get the index
		int index = hashFunction(key);
		// create a new LinkedList and make it the table at the index
		LinkedList<V> list = table[index];
		// get the current node by calling searchTable on index and key
		LinkedListNode<V> current = searchTable(index, key);
		// if the current node is null
		if (current == null) {
			// insert the value at the begining of the list
			list.insertFirst(value);

		}
		// else if the current node is not null
		if (current != null) {
			// set the data of the current to value
			current.setData(value);
		}
	}

	/**
	 * search method when we are only given a key
	 * 
	 * @param key
	 * @return
	 */
	public V search(K key) {
		// call hashFunction on the key to get the index
		int index = hashFunction(key);
		// get the current node by calling searchTable on index and key
		LinkedListNode<V> current = searchTable(index, key);
		// if the null created is null
		if (current == null) {
			// return null
			return null;

		} else {
			// else return the data of the current node
			return current.getData();
		}
	}

	/**
	 * delete method to delete the key given
	 * 
	 * @param key
	 */
	public void delete(K key) {
		// call hashFunction on the key to get the index
		int index = hashFunction(key);
		// get the current node by calling searchTable on index and key
		LinkedListNode<V> current = searchTable(index, key);
		// if the current is not null
		if (current != null) {
			// call delete first on the table at the given index
			table[index].deleteFirst();
			// else return itself
		} else {
			return;
		}

	}

	public int hashFunction(K key) {
		int hash = 0;
		int power = 1;
		char[] array = key.toCharArray();
		for (int i = 0; i < array.length; i++) {
			int ascii_code = (int) array[i];
			if (ascii_code >= 65 && ascii_code <= 90)
				ascii_code += -64;
			else if (ascii_code >= 97 && ascii_code <= 122)
				ascii_code += -96;
			else 
				ascii_code = 0;
			hash += ascii_code * power;
			power = power * 27;
		}
		return hash % this.getTableSize();
	}

	public int getTableSize() {
		return this.table.length;
	}

	@Override
	public String toString() {
		return super.toString() + " table size " + getTableSize();
	}

	/**
	 * getListLength
	 * 
	 * @param i
	 * @return an int returns the number of entries stored in list[i]
	 */
	public int getListLength(int i) {
		// initialize the listLength as 0
		int listLength = 0;
		// create a new LinkedList and make it the table at the given index
		LinkedList<V> list = table[i];
		// create a current node as the first node in the list
		LinkedListNode<V> current = list.getFirstNode();
		// while the current node is not null
		while (current != null) {
			// increment the listLength
			listLength++;
			// then keep getting the next node
			current = current.getNext();

		}
		// return the listLength
		return listLength;
	}

	/**
	 * getEntryCount method to find the number of entries stored in all the list
	 * 
	 * @return the number of entries
	 */
	public int getEntryCount() {
		// initialize entryCount as 0
		int entryCount = 0;
		// loop through the table
		for (int i = 0; i < table.length; i++) {
			// create a new LinkedList and make it the table at the given index
			LinkedList<V> list = table[i];
			// create a current node as the first node in the list
			LinkedListNode<V> current = list.getFirstNode();
			// while the current node is not null
			while (current != null) {
				// increment entryCount
				entryCount++;
				// keep getting the next node
				current = current.getNext();

			}

		}
		// return the entryCount
		return entryCount;
	}

	public double getTheoreticalLoadFactor() {
		return (double) getEntryCount() / (double) getTableSize();
	}

	public String getObservedLoadFactor() {
		String loadFactor = "";
		for (int i = 0; i < getTableSize(); i++) {
			loadFactor += "list " + i + " length " + getListLength(i) + "\n";
		}
		return loadFactor;
	}
}
