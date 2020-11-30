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
 * This class checks the correctness of the implementation of the methods
 * defined in the class PokemonTree.
 * @author XianfuLuo
 * @version 1.0
 */

public class PokemonTreeTester {

	/**
	 * Checks the correctness of the implementation of both addPokemon() and
	 * toString() methods implemented in the PokemonTree class. This unit test
	 * considers at least the following scenarios. (1) Create a new empty
	 * PokemonTree, and check that its size is 0, it is empty, and that its string
	 * representation is an empty string "". (2) try adding one Pokemon and then
	 * check that the addPokemon() method call returns true, the tree is not empty,
	 * its size is 1, and the .toString() called on the tree returns the expected
	 * output. (3) Try adding another Pokemon which is more powerful than the one at
	 * the root, (4) Try adding a third Pokemon which is less powerful than the one
	 * at the root, (5) Try adding at least two further Pokemons such that one must
	 * be added at the left subtree, and the other at the right subtree. For all the
	 * above scenarios, and more, double check each time that size() method returns
	 * the expected value, the add method call returns true, and that the
	 * .toString() method returns the expected string representation of the contents
	 * of the binary search tree in an ascendant order from the most powerful
	 * Pokemon to the least powerful one. (6) Try adding a Pokemon whose CP value
	 * was used as a key for a Pokemon already stored in the tree. Make sure that
	 * the addPokemon() method call returned false, and that the size of the tree
	 * did not change.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddPokemonToStringSize() {
		try{
			PokemonTree test = new PokemonTree();
			if (test.size()!=0){
				System.out.println("empty tree's size should be 0");
				return false;
			}
			if (!test.isEmpty()){
				System.out.println("empty tree's isEmpty() should be true");
				return false;
			}
			if (!test.toString().equals("")){
				System.out.println("empty tree's toString() should be \"\"");
				return false;
			}
			if(!test.addPokemon(new Pokemon("Pikachu","1,2,3"))){
				System.out.println("add pokemons to an empty tree should return true");
				return false;
			}
			if (test.size()!=1){
				System.out.println("tree's size should be 1");
				return false;
			}
			if (test.isEmpty()){
				System.out.println("tree's isEmpty() should be false");
				return false;
			}
			if (!test.toString().equals("[Pikachu CP:123 (A:1 S:2 D:3)]\n")){
				System.out.println("empty tree's toString() should be [Pikachu CP:123 (A:1 S:2 D:3)]\n");
				return false;
			}
			if(!test.addPokemon(new Pokemon("Eevee", "2,2,4"))){
				System.out.println("add a powerful pokemon to this tree should return true");
				return false;
			}
			if (test.size()!=2){
				System.out.println("tree's size should be 2");
				return false;
			}
			if (!test.toString().equals("[Pikachu CP:123 (A:1 S:2 D:3)]\n[Eevee CP:224 (A:2 S:2 D:4)]\n")){
				System.out.println("2 size tree's toString() return wrong");
				return false;
			}
			if(!test.addPokemon(new Pokemon("Mewtwo", "1,2,1"))){
				System.out.println("add a weak pokemon to this tree should return true");
				return false;
			}
			if (test.size()!=3){
				System.out.println("tree's size should be 3");
				return false;
			}
			if (!test.toString().equals("[Mewtwo CP:121 (A:1 S:2 D:1)]\n[Pikachu CP:123 (A:1 S:2 D:3)]\n"
				+ "[Eevee CP:224 (A:2 S:2 D:4)]\n")){
				System.out.println("3 size tree's toString() return wrong");
				return false;
			}
			if(!test.addPokemon(new Pokemon("Snorlax", "4,4,8"))){
				System.out.println("add a powerful pokemon to this right subtree should return true");
				return false;
			}
			if (test.size()!=4){
				System.out.println("tree's size should be 4");
				return false;
			}
			if (!test.toString().equals("[Mewtwo CP:121 (A:1 S:2 D:1)]\n[Pikachu CP:123 (A:1 S:2 D:3)]\n"
				+ "[Eevee CP:224 (A:2 S:2 D:4)]\n[Snorlax CP:448 (A:4 S:4 D:8)]\n")){
				System.out.println("4 size tree's toString() return wrong");
				return false;
			}
			if(!test.addPokemon(new Pokemon("Charmander", "1,2,2"))){
				System.out.println("add a pokemon to this left subtree should return true");
				return false;
			}
			if (test.size()!=5){
				System.out.println("tree's size should be 5");
				return false;
			}
			if (!test.toString().equals("[Mewtwo CP:121 (A:1 S:2 D:1)]\n[Charmander CP:122 (A:1 S:2 D:2)]"
				+ "\n[Pikachu CP:123 (A:1 S:2 D:3)]\n[Eevee CP:224 (A:2 S:2 D:4)]\n"
				+ "[Snorlax CP:448 (A:4 S:4 D:8)]\n")){
				System.out.println("5 size tree's toString() return wrong");
				return false;
			}
			if(test.addPokemon(new Pokemon("Lapras", "1,2,3"))){
				System.out.println("add a pokemon with same cp in the tree to this left should return false");
				return false;
			}
			if (test.size()!=5){
				System.out.println("tree's size should remain still");
				return false;
			}

		}catch (Exception e){
			System.out.println("Problem detected: Your testAddPokemonToStringSize() has thrown"
				+ " a non expected exception.");
			e.printStackTrace();
			return false;
		}
		return true;

	}

	/**
	 * This method checks mainly for the correctness of the PokemonTree.lookup()
	 * method. It must consider at least the following test scenarios. (1) Create a
	 * new PokemonTree. Then, check that calling the lookup() method with any valid
	 * CP value must throw a NoSuchElementException. (2) Consider a PokemonTree of
	 * height 3 which consists of at least 5 PokemonNodes. Then, try to call
	 * lookup() method to search for the Pokemon at the root of the tree, then a
	 * Pokemon at the right and left subtrees at different levels. Make sure that
	 * the lookup() method returns the expected output for every method call. (3)
	 * Consider calling .lookup() method on a non-empty PokemonTree with a CP value 
	 * not stored in the tree, and ensure that the method call throws a
	 * NoSuchElementException.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddPokemonAndLookup() {
		try{
			PokemonTree test = new PokemonTree();
			try {
				test.lookup(122);
				System.out.println("lookup() method in empty tree should throw NoSuchElementException");
				return false;
			}catch (NoSuchElementException e){
				// catch exception
			}
			test.addPokemon(new Pokemon("A","1,2,3"));
			test.addPokemon(new Pokemon("B","1,2,2"));
			test.addPokemon(new Pokemon("C","1,2,1"));
			test.addPokemon(new Pokemon("D","1,2,4"));
			test.addPokemon(new Pokemon("E","1,2,5"));
			if (!test.lookup(123).equals(new Pokemon("A","1,2,3"))){
				System.out.println("lookup() method can't find the root");
				return false;
			}
			if (!test.lookup(122).equals(new Pokemon("B","1,2,2"))){
				System.out.println("lookup() method can't find B");
				return false;
			}
			if (!test.lookup(124).equals(new Pokemon("D","1,2,4"))){
				System.out.println("lookup() method can't find D");
				return false;
			}
			if (!test.lookup(121).equals(new Pokemon("C","1,2,1"))){
				System.out.println("lookup() method can't find A");
				return false;
			}
			if (!test.lookup(125).equals(new Pokemon("E","1,2,5"))){
				System.out.println("lookup() method can't find E");
				return false;
			}
			try {
				test.lookup(188);
				System.out.println("lookup() method find no key should throw NoSuchElementException");
				return false;
			}catch (NoSuchElementException e){
				// catch exception
			}
		}catch (Exception e){
			System.out.println("Problem detected: Your testAddPokemonAndLookup() has thrown"
				+ " a non expected exception.");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Checks for the correctness of PokemonTree.height() method. This test must
	 * consider several scenarios such as, (1) ensures that the height of an empty
	 * Pokemon tree is zero. (2) ensures that the height of a tree which consists of
	 * only one node is 1. (3) ensures that the height of a PokemonTree with the
	 * following structure for instance, is 4. 
	 *      (*) 
	 *     /   \ 
	 *   (*)   (*) 
	 *     \   / \ 
	 *    (*)(*) (*) 
	 *           /
	 *         (*)
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testHeight() {
		try{
			PokemonTree test = new PokemonTree();
			if (test.height()!=0){
				System.out.println("empty tree's height should return 0");
			}
			test.addPokemon(new Pokemon("A","1,2,3"));
			if (test.height()!=1){
				System.out.println("tree's height should return 1");
			}
			test.addPokemon(new Pokemon("B","1,2,1"));
			test.addPokemon(new Pokemon("C","1,2,2"));
			test.addPokemon(new Pokemon("D","1,3,4"));
			test.addPokemon(new Pokemon("E","1,3,3"));
			test.addPokemon(new Pokemon("F","1,4,5"));
			test.addPokemon(new Pokemon("G","1,3,5"));
			if (test.height()!=4){
				System.out.println("tree's height should return 4");
			}
		}catch (Exception e){
			System.out.println("Problem detected: Your testHeight() has thrown"
				+ " a non expected exception.");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Checks for the correctness of PokemonTree.getLeastPowerfulPokemon() method.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testGetLeastPowerfulPokemon() {
		try {
			PokemonTree test = new PokemonTree();
			test.addPokemon(new Pokemon("A", "1,2,3"));
			test.addPokemon(new Pokemon("B", "1,2,1"));
			test.addPokemon(new Pokemon("C", "1,2,2"));
			test.addPokemon(new Pokemon("D", "1,3,4"));
			test.addPokemon(new Pokemon("E", "1,3,3"));
			test.addPokemon(new Pokemon("F", "1,4,5"));
			test.addPokemon(new Pokemon("G", "1,3,5"));
			if (!test.getLeastPowerfulPokemon().equals(new Pokemon("B", "1,2,1"))){
				System.out.println("wrong Least Powerful Pokemo");
			}
		}catch (Exception e){
			System.out.println("Problem detected: Your testGetLeastPowerfulPokemon() has thrown"
				+ " a non expected exception.");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Checks for the correctness of PokemonTree.getMostPowerfulPokemon() method.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testGetMostPowerfulPokemon() {
		try {
			PokemonTree test = new PokemonTree();
			test.addPokemon(new Pokemon("A", "1,2,3"));
			test.addPokemon(new Pokemon("B", "1,2,1"));
			test.addPokemon(new Pokemon("C", "1,2,2"));
			test.addPokemon(new Pokemon("D", "1,3,4"));
			test.addPokemon(new Pokemon("E", "1,3,3"));
			test.addPokemon(new Pokemon("F", "1,4,5"));
			test.addPokemon(new Pokemon("G", "1,3,5"));
			if (!test.getMostPowerfulPokemon().equals(new Pokemon("F", "1,4,5"))){
				System.out.println("wrong Most Powerful Pokemo");
			}
		}catch (Exception e){
			System.out.println("Problem detected: Your testGetMostPowerfulPokemon() has thrown"
				+ " a non expected exception.");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Calls the test methods
	 * 
	 * @param args input arguments if any
	 */
	public static void main(String[] args) {
		System.out.println(testAddPokemonToStringSize());
		System.out.println(testAddPokemonAndLookup());
		System.out.println(testHeight());
		System.out.println(testGetLeastPowerfulPokemon());
		System.out.println(testGetMostPowerfulPokemon());
	}

}
