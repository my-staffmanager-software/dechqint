# SocksLaundering

 ---END GOAL---

 <b>*To provide an algorithm that compute the maximum number of pairs of socks Anna can take on her trip.</b>

----
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

---

```* <p>
     *     <br/> <b>Algorithm analysis<b><br/>
     *     <ul>Time complexity :  O(n+m) (where n is the total size of the dirty pile, m = total size of clean pile)</ul>
     *     <ul>Space complexity : O(n+m) (Two different memory space(s) is required)</ul>
     * </p>

```
<b>Issues</b>
<li>Expected or provided result for Test 4,17 and 18 parameters appears wrong</li>
---

