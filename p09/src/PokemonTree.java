//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Pokémon Catalog
// Course:   CS 300 Fall 2020
//
// Author:   Xianfu Luo
// Email:    xluo96@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;

/**
 * This class implements a binary search tree (BST) which stores a set of
 * Pokemons. The left subtree contains the Pokemons who are less powerful than
 * the Pokemon stored at a parent node. The right subtree contains the Pokemons
 * who are more powerful than the Pokemon stored at a parent node.
 * @author Xianfu Luo
 * @version 1.0
 */
public class PokemonTree {
	private PokemonNode root; // root of this binary search tree
	private int size; // total number of Pokemons stored in this tree.

	/**
	 * Checks whether this binary search tree (BST) is empty
	 * 
	 * @return true if this PokemonTree is empty, false otherwise
	 */
	public boolean isEmpty() {
		// TODO Complete the implementation of this method.
		return size==0; // return false when size = 0

	}

	/**
	 * Returns the number of Pokemons stored in this BST.
	 * 
	 * @return the size of this PokemonTree
	 */
	public int size() {
		// TODO Complete the implementation of this method.
		return size;

	}

	/**
	 * Recursive helper method to add a new Pokemon to a PokemonTree rooted at
	 * current.
	 * 
	 * @param current    The "root" of the subtree we are inserting new pokemon
	 *                   into.
	 * @param newPokemon The Pokemon to be added to a BST rooted at current.
	 * @return true if the newPokemon was successfully added to this PokemonTree,
	 *         false otherwise
	 */
	public static boolean addPokemonHelper(Pokemon newPokemon, PokemonNode current) {
		// TODO Complete the implementation of this method.
		if (newPokemon.equals(current.getPokemon())){
			return false;
		}else if(newPokemon.compareTo(current.getPokemon())>0){
			if (current.getRightChild()!=null){
				return addPokemonHelper(newPokemon,current.getRightChild());
			}else {
				PokemonNode newPokemonNode = new PokemonNode(newPokemon);
				current.setRightChild(newPokemonNode);
				return true;
			}
		}else {
			if (current.getLeftChild()!=null){
				return addPokemonHelper(newPokemon,current.getLeftChild());
			}else {
				PokemonNode newPokemonNode = new PokemonNode(newPokemon);
				current.setLeftChild(newPokemonNode);
				return true;
			}
		}
	}

	/**
	 * Adds a new Pokemon to this PokemonTree
	 * 
	 * @param newPokemon a new Pokemon to add to this BST.
	 * @return true if the new was successfully added to this BST, and returns false
	 *         if there is a match with this Pokemon already already stored in this
	 *         BST.
	 */
	public boolean addPokemon(Pokemon newPokemon) {
		// TODO Complete the implementation of this method.
		if (isEmpty()) { // Add new to an empty PokemonTree
		  // TODO /* MISSING CODE */
			root = new PokemonNode(newPokemon);
			size++;
			return true;
		} else { // Add new to an non-empty PokemonTree
		  // TODO /* MISSING CODE */
		  // [Hint]: call addPokemonHelper to help implement this behavior.
			if (addPokemonHelper(newPokemon,root)){
				size++;
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Recursive helper method which returns a String representation of the BST
	 * rooted at current. An example of the String representation of the contents of
	 * a PokemonTree is provided in the description of the above toString() method.
	 * 
	 * @param current reference to the current PokemonNode within this BST.
	 * @return a String representation of all the Pokemons stored in the sub-tree
	 *         PokemonTree rooted at current in increasing order with respect to the
	 *         CP values. Returns an empty String "" if current is
	 *         null.
	 */
	public static String toStringHelper(PokemonNode current) {
		if (current!=null) {

			return toStringHelper(current.getLeftChild())+current.getPokemon().toString()+"\n"
				+toStringHelper(current.getRightChild());

		}else {
			return "";
		}
		// TODO Complete the implementation of this method.

	}

	/**
	 * Returns a String representation of all the Pokemons stored within this BST in
	 * the increasing order, separated by a newline "\n". For instance:
	 * "[Pikachu CP:123 (A:1 S:2 D:3)]" + "\n" + "[Eevee CP:224 (A:2 S:2 D:4)]" + "\n" + 
	 * [Lapras CP:735 (A:7 S:3 D:5)] + "\n" + "[Mewtwo CP:999 (A:9 S:9 D:9)]" + "\n"
	 * 
	 * @return a String representation of all the Pokemons stored within this BST
	 *         sorted in an increasing order with respect to the CP values.
	 *         Returns an empty string "" if this BST is empty.
	 */
	public String toString() {
		// TODO Complete the implementation of this method.

		// [Hint]: call toStringHelper() method to help implement this behavior.
		return toStringHelper(root); // remove this statement.
	}

	/**
	 * Search for a Pokemon (Pokemon) given the CP value as lookup key.
	 * 
	 * @param cp combat power of a Pokemon
	 * @return the Pokemon whose CP value equals our lookup key.
	 * @throws a NoSuchElementException with a descriptive error message if there is
	 *           no Pokemon found in this BST having the provided CP value
	 */
	public Pokemon lookup(int cp) {
		return lookupHelper(cp, root);
	}

	/**
	 * Recursive helper method to lookup a Pokemon given a reference Pokemon with
	 * the same CP in the subtree rooted at current
	 * 
	 * @param find    a reference to a Pokemon target we are lookup for a match in
	 *                the BST rooted at current.
	 * @param current "root" of the subtree we are looking for a match to find
	 *                within it.
	 * @return reference to the Pokemon stored stored in this BST which matches
	 *         find.
	 * @throws NoSuchElementException with a descriptive error message if there is
	 *                                no Pokemon whose CP value matches target value,
	 *                                stored in this BST.
	 */
	public static Pokemon lookupHelper(int cp, PokemonNode current) throws NoSuchElementException {
		// TODO Complete the implementation of this method
		if (current==null){
			throw new NoSuchElementException("No search results.");
		}else {
			if (cp<current.getPokemon().getCP()){
				return lookupHelper(cp,current.getLeftChild());
			}else if(cp==current.getPokemon().getCP()){
				return current.getPokemon();
			}else {
				return lookupHelper(cp,current.getRightChild());
			}
		}
	}

	/**
	 * Computes and returns the height of this BST, counting the number of nodes
	 * (PokemonNodes) from root to the deepest leaf.
	 * 
	 * @return the height of this Binary Search Tree
	 */
	public int height() {
		return heightHelper(root);
	}

	/**
	 * Recursive helper method that computes the height of the subtree rooted at
	 * current
	 * 
	 * @param current pointer to the current PokemonNode within a PokemonTree
	 * @return height of the subtree rooted at current, counting the number of
	 *         PokemonNodes
	 */
	public static int heightHelper(PokemonNode current) {
		if (current==null){
			return 0;
		}else {
			int right = heightHelper(current.getRightChild())+1;
			int left = heightHelper(current.getLeftChild())+1;
			return right>left ? right:left;
		}
	}

	/**
	 * Returns the Pokemon of the least powerful Pokemon in this BST.
	 * 
	 * @return the Pokemon of the least powerful Pokemon in this BST and null if this tree
	 *         is empty.
	 */
	public Pokemon getLeastPowerfulPokemon() {
		// TODO Complete the implementation of this method
		if (root==null){
			return null;
		}
		PokemonNode current = root;
	  while (current.getLeftChild()!=null){
	  	current=current.getLeftChild();
		}
		// Feel free to implement either the iterative OR the recursive version of this
		// method.
	  
		// If you choose recursion to implement this behavior, add a private helper
		// method and call it here.

		return current.getPokemon(); // added to let this incomplete code compile
	}

	/**
	 * Returns the Pokemon of the most powerful Pokemon in this BST.
	 * 
	 * @return the Pokemon of the most powerful Pokemon in this BST, and null if this tree
	 *         is empty.
	 */
	public Pokemon getMostPowerfulPokemon() {
		// TODO Complete the implementation of this method
		if (root==null){
			return null;
		}
		PokemonNode current = root;
		while (current.getRightChild()!=null){
			current=current.getRightChild();
		}
		// Feel free to implement either the iterative OR the recursive version of this
		// method.
	  
		// If you choose recursion to implement this behavior, add a private helper
		// method and call it here.

		return current.getPokemon(); // added to let this incomplete code compile
	}

}
