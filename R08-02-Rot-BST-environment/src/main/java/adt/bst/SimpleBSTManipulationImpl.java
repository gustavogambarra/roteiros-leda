package adt.bst;

import adt.bt.BTNode;

/**
 * - Esta eh a unica classe que pode ser modificada
 *
 * @param <T>
 * @author adalbertocajueiro
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(final BST<T> tree1, final BST<T> tree2) {
		return equals(tree1.getRoot(), tree2.getRoot());
	}

	private boolean equals(final BTNode<T> root1, final BTNode<T> root2) {
		if (isNull(root1) && isNull(root2)) {
			return true;
		}
		if (isNull(root1)) {
			return false;
		}
		if (isNull(root2)) {
			return false;
		}
		if (!root1.equals(root2)) {
			return false;
		}
		return equals(root1.getLeft(), root2.getLeft()) && equals(root1.getRight(), root2.getRight());
	}

	@Override
	public boolean isSimilar(final BST<T> tree1, final BST<T> tree2) {
		return isSimilar(tree1.getRoot(), tree2.getRoot());
	}

	private boolean isSimilar(final BTNode<T> node1, final BTNode<T> node2) {
		if (isNull(node1) && isNull(node2)) {
			return true;
		}
		if (isNull(node1)) {
			return false;
		}
		if (isNull(node2)) {
			return false;
		}
		return isSimilar(node1.getLeft(), node2.getLeft()) && isSimilar(node1.getRight(), node2.getRight());
	}

	@Override
	public T orderStatistic(final BST<T> tree, int k) {
		BSTNode<T> number = tree.minimum();
		while (k > 1) {
			if (isNull(number)) {
				return null;
			}
			number = tree.sucessor(number.getData());
			--k;
		}

		return number.getData();
	}

	private boolean isNull(final Object obj) {
		return obj == null;
	}

}
