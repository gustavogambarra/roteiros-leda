package problems;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		Integer floor = null;

		if (array != null && array.length > 0) {
			int leftIndex = 0;
			int rightIndex = array.length - 1;

			while (leftIndex <= rightIndex) {
				int middle = (leftIndex + rightIndex) / 2;

				if (array[middle].compareTo(x) == 0) {
					return array[middle];
				} else if (x.compareTo(array[middle]) < 0) {
					rightIndex = middle - 1;
				} else {
					floor = array[middle];
					leftIndex = middle + 1;
				}
			}
		}

		return floor;
	}

}
