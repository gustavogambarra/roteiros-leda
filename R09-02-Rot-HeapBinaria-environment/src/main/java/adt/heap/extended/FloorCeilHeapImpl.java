package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double number) {
		Integer resp = null;
		if (array.length > 0){
			insertAuxiliar(array);
			if(comparator.compare(1, 2) > 0){
				resp = floorMinHeap(number, null);
			} else {
				resp = floorMaxHeap(number, null);
			}
		}
		return resp;
	}

	private void insertAuxiliar(Integer[] array){
		this.heap = new Integer[array.length];
		this.index = -1;
		for (Integer num : array){
			insert(num);
		}
	}

	private Integer floorMaxHeap(double number, Integer floor) {
		Integer floorMax = floor;
		if (!isEmpty()) {
			if (rootElement() <= number) {
				floorMax = rootElement();
			} else {
				extractRootElement();
				floorMax = floorMaxHeap(number, floor);
			}
		}
		return floorMax;
	}

	private Integer floorMinHeap(double number, Integer floor) {
		Integer floorMin = floor;
		if (!isEmpty()) {
			if (rootElement() == number) {
				floorMin = rootElement();
			} else if (rootElement() < number) {
				Integer root = extractRootElement();
				floorMin = floorMinHeap(number, root);
			}
		}
		return floorMin;
	}

	@Override
	public Integer ceil(Integer[] array, double number) {
		Integer ceil = null;
		this.index = -1;
		for (Integer i : array) {
			this.insert(i);
		}
		if(!isEmpty()) {
			for (int i = 0; i < array.length; i++) {
				if(this.rootElement() == number) ceil = this.rootElement();
				else if(this.rootElement() > number) {
					if(ceil == null || ceil > this.rootElement()) ceil = this.rootElement();
				}
				else {
					this.extractRootElement();
				}
			}
		}
		return ceil;
	}
}
