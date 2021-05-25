package orderStatistic;

import util.Util;

import java.util.Random;

/**
 * O quickselect eh um algoritmo baseado no quicksort para
 * descobrir/selectionar, em tempo linear, a k-esima estatistica de ordem
 * (k-esimo menor elemento) de um conjunto de dados.
 * 
 * O quiskselect escolhe um elemento para ser o pivot e particiona o array
 * inicial em dois subarrays da mesma forma que o quicksort (elementos menores
 * que o pivot a esquerda do pivot e elementos maiores que o pivot a direita
 * dele). Entretanto, ao inves de chamar o quicksort recursivo nas duas metades,
 * o quickselect eh executado (recursivamente) apenas na metade que contem o
 * elemento que ele procura (o k-esimo menor elemento). Isso reduz a
 * complexidade de O(n.log n) para O(n)
 * 
 * @author Adalberto
 *
 */
public class QuickSelect<T extends Comparable<T>> {

	/**
	 * O algoritmo quickselect usa a mesma abordagem do quicksort para calclar o
	 * k-esimo menor elemento (k-esima estatistica de ordem) de um determinado
	 * array de dados comparaveis. Primeiro ele escolhe um elemento como o pivot
	 * e particiona os daods em duas partes baseado no pivot (exatemente da
	 * mesma forma que o quicksort). Depois disso, ele chama recursivamente o
	 * mesmo algoritmo em apenas uma das metades (a que contem o k-esimo menor
	 * elemento). Isso redua a completixade de O(n.log n) para O(n).
	 * 
	 * Caso o array seja vazio ou a ordem (posicao) do elemento desejado esteja
	 * fora do tamanho do array, o metodo deve retornar null.
	 * 
	 * 
	 * @param array
	 *            o array de dados a procurar o k-esimo menor elemento.
	 * @param k
	 *            a ordem do elemento desejado. 1 significa primeiro menor
	 *            elemento, 2 significa segundo menor elemento e assim por
	 *            diante
	 * @return
	 */
	public T quickSelect(T[] array, int k) {
		return quickHelper(array, 0, array.length - 1, k);
	}

	private T quickHelper (T[]array,int inicio, int fim, int k){
		int pivot = partition(array, inicio, fim);

		T retorno;

		if (pivot + 1 == k) {
			retorno =  array[pivot];
		} else if (pivot + 1 < k) {
			retorno =  quickHelper(array, pivot + 1, fim, k);
		} else {
			retorno =  quickHelper(array, inicio, pivot - 1, k);
		}

		return retorno;
	}

	private int partition (T[]array,int inicio, int fim){
		int randNum = new Random().nextInt(fim - inicio + 1) + inicio;

		Util.swap(array, randNum, fim);

		int i = inicio;
		int j = fim - 1;

		while (i < j) {
			while (i < j && array[i].compareTo(array[fim]) <= 0) {
				i++;
			}
			while (i < j && array[j].compareTo(array[fim]) >= 0) {
				j--;
			}
			if (i < j) {
				Util.swap(array, i, j);
			}
		}
		if (array[i].compareTo(array[fim]) > 0) {
			Util.swap(array, i, fim);
		}

		return i;
	}
}
