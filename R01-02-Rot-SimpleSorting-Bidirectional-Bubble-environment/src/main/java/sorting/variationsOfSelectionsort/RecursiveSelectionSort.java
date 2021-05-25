package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

public class RecursiveSelectionSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * Implementação recursiva do selection sort. Você deve implementar apenas
	 * esse método sem usar nenhum outro método auxiliar (exceto
	 * Util.swap(array,int,int)). Para isso, tente definir o caso base do
	 * algoritmo e depois o caso indutivo, que reduz o problema para uma entrada
	 * menor em uma chamada recursiva. Seu algoritmo deve ter complexidade
	 * quadrática O(n^2).
	 */
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex >= 0 && rightIndex <= array.length - 1
				&& array.length > 1) {
			int minor_index = leftIndex;

			if (leftIndex < rightIndex) {
				for (int i = leftIndex; i < rightIndex + 1; i++) {
					if (array[i].compareTo(array[minor_index]) < 0) {
						minor_index = i;
					}
				}

				Util.swap(array, minor_index, leftIndex);

				sort(array, leftIndex + 1, rightIndex);
			}
		}
	}

}
