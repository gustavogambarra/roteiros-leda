package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
													HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if(this.isFull())
			throw new HashtableOverflowException();

		if(element != null) {
			if(this.search(element) != null)
				return;

			int probe = 0;
			int hashIndex = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);

			while (probe < table.length) {
				if (table[hashIndex] == null || deletedElement.equals(table[hashIndex])) {
					table[hashIndex] = element;
					super.elements++;
					return;
				} else {
					probe++;
					hashIndex = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
					super.COLLISIONS++;
				}
			}

		}
	}

	@Override
	public void remove(T element) {
		if(this.isEmpty())
			throw new HashtableOverflowException();

		int hashIndex = indexOf(element);

		if (hashIndex >= 0) {
			table[hashIndex] = new DELETED();
			elements--;
		}
	}

	@Override
	public T search(T element) {
		if (element != null) {
			int probe = 0;
			int hashIndex = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);

			while (probe < table.length) {
				if (table[hashIndex] != null && table[hashIndex].equals(element)) {
					return element;
				} else {
					probe++;
					hashIndex = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
				}
			}
		}

		return null;
	}

	@Override
	public int indexOf(T element) {
		if (this.search(element) != null) {
			int probe = 0;
			int hashIndex = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);

			while (probe < table.length) {
				if (table[hashIndex].equals(element)) {
					return hashIndex;
				} else {
					probe++;
					hashIndex = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
				}
			}
		}
		return -1;
	}
}
