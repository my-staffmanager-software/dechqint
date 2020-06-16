package socklaundering;

import java.util.*;

/**
 * The {@code SockLaundry} class is meant to provide a 
 * an algorithm that compute the maximum number of pairs of socks Anna can take on her trip.
 *<br/><p>HOW THE ALGORITHM WORKS</p>
 *<br/>
 *<br/><li>Step 0 : Count the possible valid pairs of clean socks, and store the left in HashTable</li>
 *<br/><li>Step 1 : If the washing machine can't wash anything, return the total number of existing clean pair</li>
 *<br/><li>Step 2 : Count the possible pairs of dirty socks that can be wash, and store the left in HashTable</li>
 *<br/><li>Step 3 : If the washing machine can still wash the left dirty socks,count the possible valid pairs of clean socks</li>
 * 
 *<br/><br/>
 *@author  Amuda Adeolu
 * @email   badmusamuda@gmail.com
 */

public class SockLaundry {

   /** The value is used to count the maximum pairs of socks*/
   private int pairCounter = 0;

   /** The value denote the capacity of what the washing machine can wash*/
   private int washingIndex = 0;

    //Do not delete or edit this method, you can only modify the block
    /*
     * Returns the {@code int} possible pair of socks
     *
     * @param      noOfWashes   maximum number of socks the washing machine can wash.
     * @param      cleanPile    array of pairs of clean sock.
     * @param      dirtyPile    array of pairs of dirty sock.
     *<br/>
     *<br/>
     * <p>
     *     <br/> <b>Algorithm analysis<b><br/>
     *     <ul>Time complexity :  O(n+m) (where n is the total size of the dirty pile, m = total size of clean pile)</ul>
     *     <ul>Space complexity : O(n+m) (Two different memory space(s) is required)</ul>
     * </p>
    */
    public int getMaximumPairOfSocks(int noOfWashes, int[] cleanPile, int[] dirtyPile) {
        return possiblePairs(cleanPile, noOfWashes, dirtyPile);
    }
     

   

   /*Count and return all possible maximum clean pairs
	@param cleanPile       =   Array of clean socks
	@param noOfWashes      =   The maximum no. of socks the washing maching can wash
	@param dirtyPile       =   Array of dirty socks
		
	<p>HOW TO IDENTIFY VALID CLEAN SOCKS </p>
	<li>It must be at least two pairs of the same color</li>	
	<b>Algorithm analysis<b>
	<ul>Time complexity :  O(m) (where m is the total size of the clean pile)</ul>
	<ul>Space complexity : O(1) (Only a single memory or space[@param pairCounter] is required)
   */
   private int possiblePairs(int[]cleanPile, int noOfWashes, int[] dirtyPile){
	HashSet<Integer> cleanSocksTable = new HashSet<>();

        //Count the possible valid pairs of clean socks, and store the left in HashTable
	for(int i = 0; i < cleanPile.length; i++){
		if( cleanSocksTable.contains(cleanPile[i]) ){
			pairCounter++;
			cleanSocksTable.remove(cleanPile[i]);
		}else{
			cleanSocksTable.add(cleanPile[i]);
		}
	}	
        //If the washing machine can't wash anything, return the total number of existing clean pair
	if(noOfWashes == 0)  
	    return pairCounter;

        //Count the possible pairs of dirty socks that can be wash, and store the left in HashTable
	HashSet<Integer> dirtySocksTable = leftDirtyPile(dirtyPile, noOfWashes);

	if(!cleanSocksTable.isEmpty()){
		for(Integer j : cleanSocksTable){
			if(washingIndex == noOfWashes) break;
			else{
			    if(dirtySocksTable.contains(j)){
				pairCounter++;
				dirtySocksTable.remove(j);
			    }
			}
			washingIndex++;
		}
	}
        //If the washing machine can still wash the left dirty socks, count the possible valid pairs of clean socks
	if( washingIndex < noOfWashes ){
	    for(Integer j : cleanSocksTable){
		if(washingIndex == noOfWashes) break;
		   else{
		       if(dirtySocksTable.contains(j)){
			 pairCounter++;
			 dirtySocksTable.remove(j);
		         washingIndex++;			
		       }
		   }
	     }
         }

	return pairCounter;
   }
   /*Count and return the valid(pairable) maximum number of socks the washing machine can wash

	@param noOfWashes     =   The maximum no. of socks the washing maching can wash
	@param dirtyPile      =   Array of dirty socks
	
	<p>HOW TO IDENTIFY VALID DIRTY SOCKS THE WASHING MACHINE CAN WASH.</p>
	<li>It must be at least two pairs of the same color</li>
	<br/>
	<b>Algorithm analysis<b>
	<ul>Time complexity :  O(n) (where n is the total size of the dirty pile)</ul>
	<ul>Space complexity : O(n) (Only n(size) memory or space[@param dirtyPile] is required)
   */
   private HashSet<Integer> leftDirtyPile(int[]dirtyPile, int noOfWashes){
	HashSet<Integer> washingTable = new HashSet<>();

	for(int i = 0; i < dirtyPile.length; i++){
		if(washingIndex == noOfWashes) break;
		if( washingTable.contains(dirtyPile[i]) ){
			pairCounter++;
			washingIndex++;
			washingTable.remove(dirtyPile[i]);
		}else{
			washingTable.add(dirtyPile[i]);
		}
	}		
	return washingTable;
   }
}
