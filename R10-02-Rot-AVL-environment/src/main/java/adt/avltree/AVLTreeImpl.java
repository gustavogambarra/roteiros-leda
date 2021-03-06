package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 *
 * Implementacao de uma arvore AVL
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	public void insert(T element) {
		if (element != null) {
			super.insert(element);
			BSTNode<T> node = search(element);
			rebalanceUp((BSTNode<T>) node.getParent());
		}
	}

	public void remove(T element){
		BSTNode<T> node = search(element);
		super.remove(element);
		rebalanceUp((BSTNode<T>) node.getParent());
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node.isEmpty()) {
			return 0;
		}
		return super.height((BSTNode<T>) node.getLeft()) - super.height((BSTNode<T>) node.getRight());
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);

		if (balance < -1) {
			if (calculateBalance((BSTNode<T>) node.getLeft()) > 0) {
				Util.leftRotation((BSTNode<T>) node.getLeft());
			}
			Util.rightRotation(node);
		} else if (balance > 1) {
			if (calculateBalance((BSTNode<T>) node.getRight()) < 0) {
				Util.rightRotation((BSTNode<T>) node.getRight());
			}
			Util.leftRotation(node);
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			if (calculateBalance(node) < -1 || calculateBalance(node) > 1) {
				rebalance(node);
			}
			rebalanceUp((BSTNode<T>) node.getParent());
		}
	}

	//AUXILIARY
	protected void leftRotation(BSTNode<T> node) {
		BSTNode<T> newNode = Util.leftRotation(node);
		if (newNode.getParent() == null) {
			this.root = newNode;
		}
	}

	// AUXILIARY
	protected void rightRotation(BSTNode<T> node) {
		BSTNode<T> newNode = Util.rightRotation(node);
		if (newNode.getParent() == null) {
			this.root = newNode;
		}
	}
}
