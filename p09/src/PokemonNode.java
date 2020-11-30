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
/**
 * A class to implements a LinkedNode data type for Pokémon
 * @author Xianfu Luo
 * @version 1.0
 */
public class PokemonNode {
    private Pokemon data; // data field of this PokemonNode
    private PokemonNode leftChild; // reference to the left child
    private PokemonNode rightChild; // reference to the right child

    public PokemonNode(Pokemon data) throws IllegalArgumentException{
        if (data==null){
            throw new IllegalArgumentException("data is null");
        }
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }
    public PokemonNode getLeftChild(){
        return this.leftChild;
    }
    public PokemonNode getRightChild(){
        return this.rightChild;
    }
    public Pokemon getPokemon(){
        return this.data;
    }
    public void setLeftChild(PokemonNode left){
        this.leftChild = left;
    }
    public void setRightChild(PokemonNode right){
        this.rightChild = right;
    }
}
