package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for (int k = leftIndex; k <= rightIndex; k++) {
			T aux = array[k];
			int j = k - 1;

			while (j >= leftIndex && array[j].compareTo(aux) > 0) {
				array[j + 1] = array[j];
				j--;
			}

			array[j + 1] = aux;
		}
	}
}