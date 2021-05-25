package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bt.BT;
import adt.bt.BTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		for (Integer num : array) {
			super.insert(num);
		}

		return floorRecursively(super.getRoot(), numero);
	}

	private Integer floorRecursively(BTNode<Integer> node, Double numero) {
		if (isNull(node)) return null;
		if (numberIsEqualNode(node, numero)) return node.getData();
		if (isLeaf(node) && elementIsLessThanNode(numero, node)) return null;
		if (isLeaf(node)) return node.getData();
		if (elementIsLessThanNode(numero, node)) return floorRecursively(node.getLeft(), numero);
		return max(node, floorRecursively(node.getRight(), numero));
	}


	@Override
	public Integer ceil(Integer[] array, double numero) {
		for (Integer num : array) {
			super.insert(num);
		}

		return ceilRecursively(super.getRoot(), numero);
	}


	private Integer ceilRecursively(BTNode<Integer> node, Double num) {
		if (isNull(node)) return null;
		if (numberIsEqualNode(node, num)) return node.getData();
		if (isLeaf(node) && elementIsGreaterThanNode(num, node)) return null;
		if (isLeaf(node)) return node.getData();
		if (elementIsGreaterThanNode(num, node)) return ceilRecursively(node.getRight(), num);
		return (min(node, ceilRecursively(node.getLeft(), num)));
	}

	private boolean numberIsEqualNode(BTNode<Integer> node, Double num) {
		return num.compareTo(Double.valueOf(node.getData())) == 0;
	}

	private boolean elementIsGreaterThanNode(Double num, BTNode<Integer> node) {
		return num.compareTo(Double.valueOf(node.getData())) > 0;
	}

	private boolean isNull(Object objeto) {
		return objeto == null;
	}

	private boolean isLeaf(BTNode<Integer> node) {
		return isNull(node.getLeft()) && isNull(node.getRight());
	}

	private Integer min(BTNode<Integer> node1, Integer num) {
		if (isNull(node1)) return num;
		if (isNull(num)) return node1.getData();
		if (node1.getData().compareTo(num) < 0) return node1.getData();
		return num;
	}

	private Integer max(BTNode<Integer> node1, Integer num) {
		if (isNull(node1)) return num;
		if (isNull(num)) return node1.getData();
		if (node1.getData().compareTo(num) > 0) return node1.getData();
		return num;
	}


	private boolean elementIsLessThanNode(Double numero, BTNode<Integer> node) {
		return numero.compareTo(Double.valueOf(node.getData())) < 0;
	}
}
