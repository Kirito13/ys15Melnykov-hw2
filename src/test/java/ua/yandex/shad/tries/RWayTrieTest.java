/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.shad.tries;

import java.util.AbstractQueue;
import java.util.LinkedList;
import java.util.Queue;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ua.yandex.shad.tries.Tuple;


/**
 *
 * @author Мел
 */
@RunWith(MockitoJUnitRunner.class)
public class RWayTrieTest {
    
/*
    @Test
    public void testAdd() {
        RWayTrie tree = new RWayTrie();
        Tuple t = new Tuple("java", 4);
        tree.add(t);
        int expResult = 4;
        int actualResult = tree.getroot().next['j'-'a'].next['a'-'a'].next['v' - 'a'].next['a'-'a'].weight;
        assertEquals(expResult, actualResult);
    }*/
    public void assertListEquals(LinkedList<String> expResult, 
            LinkedList<String> actualResult) {
        if (expResult.size() != actualResult.size()) {
            throw new AssertionError("the lenghts of expList:" + 
                    Integer.toString(expResult.size()) + 
                    " and actualList, are different");
        }
        for (String expResult1 : expResult) {
            if (!(actualResult.contains(expResult1))) {
                throw new AssertionError("actualResult list doesn`t contain "
                        + "this string: " + expResult1);
            }
        }
    }
    
    @Test
    public void testContains() {
        RWayTrie tree = new RWayTrie();
        Tuple t = new Tuple("java", 4);
        tree.add(t);
        boolean expResult = true;
        boolean actualResult = tree.contains("java");
        assertEquals(expResult, actualResult);
    }
    
    @Test
    public void testContainsWithNoWord() {
        RWayTrie tree = new RWayTrie();
        Tuple t = new Tuple("java", 4);
        tree.add(t);
        boolean expResult = false;
        boolean actualResult = tree.contains("cplusplus");
        assertEquals(expResult, actualResult);
    }
    
    @Test
    public void testContainsWithManyWords() {
        RWayTrie tree = new RWayTrie();
        Tuple t = new Tuple("java", 4);
        Tuple y = new Tuple("pyton", 5);
        Tuple u = new Tuple("java", 4);
        tree.add(t);
        tree.add(y);
        tree.add(u);
        boolean expResult = true;
        boolean actualResult = tree.contains("java");
        assertEquals(expResult, actualResult);
    }
    
    @Test
    public void testDelete() {
        RWayTrie tree = new RWayTrie();
        Tuple t = new Tuple("java", 4);
        tree.add(t);
        boolean expResult = true;
        boolean actualResult = tree.delete("java");
        assertEquals(expResult, actualResult);
    }
    
    @Test
    public void testDeleteWithNoWord() {
        RWayTrie tree = new RWayTrie();
        Tuple t = new Tuple("java", 4);
        tree.add(t);
        boolean expResult = false;
        boolean actualResult = tree.delete("cplusplus");
        assertEquals(expResult, actualResult);
    }
    
    @Test
    public void testWords() {
        RWayTrie tree = new RWayTrie();
        Tuple t = new Tuple("java", 4);
        Tuple y = new Tuple("cplusplus", 9);
        Tuple u = new Tuple("pyton", 5);
        Tuple i = new Tuple("javascript", 10);
        Tuple o = new Tuple("php", 3);
        tree.add(t);
        tree.add(y);
        tree.add(u);
        tree.add(i);
        tree.add(o);
        LinkedList<String> expResult = new LinkedList<String>();
        expResult.add("php");
        expResult.add("java");
        expResult.add("pyton");
        expResult.add("cplusplus");
        expResult.add("javascript");
        Iterable<String> result = tree.words();
        LinkedList<String> actualResult = (LinkedList)result;
        assertListEquals(expResult, actualResult);
    }
    
    @Test
    public void testWordsWithPrefix() {
        RWayTrie tree = new RWayTrie();
        Tuple t = new Tuple("java", 4);
        Tuple y = new Tuple("jav", 3);
        Tuple u = new Tuple("pyton", 5);
        Tuple i = new Tuple("javascript", 10);
        Tuple o = new Tuple("php", 3);
        tree.add(t);
        tree.add(y);
        tree.add(u);
        tree.add(i);
        tree.add(o);
        LinkedList<String> expResult = new LinkedList<String>();
        expResult.add("jav");
        expResult.add("java");
        expResult.add("javascript");
        Iterable<String> result = tree.wordsWithPrefix("jav");
        LinkedList<String> actualResult = (LinkedList)result;
        assertListEquals(expResult, actualResult);
    }
    
    @Test
    public void testWordsWithPrefixWithNoWords() {
        RWayTrie tree = new RWayTrie();
        Tuple t = new Tuple("java", 4);
        Tuple y = new Tuple("jav", 3);
        Tuple u = new Tuple("pyton", 5);
        Tuple i = new Tuple("javascript", 10);
        Tuple o = new Tuple("php", 3);
        tree.add(t);
        tree.add(y);
        tree.add(u);
        tree.add(i);
        tree.add(o);
        LinkedList<String> expResult = new LinkedList<String>();
        Iterable<String> result = tree.wordsWithPrefix("javac");
        LinkedList<String> actualResult = (LinkedList)result;
        assertListEquals(expResult, actualResult);
    }
    
    @Test
    public void testSize() {
        RWayTrie tree = new RWayTrie();
        Tuple t = new Tuple("java", 4);
        Tuple y = new Tuple("jav", 3);
        Tuple u = new Tuple("pyton", 5);
        Tuple i = new Tuple("javascript", 10);
        Tuple o = new Tuple("php", 3);
        tree.add(t);
        tree.add(y);
        tree.add(u);
        tree.add(i);
        tree.add(o);
        int expResult = 5;
        int actualResult = tree.size();
        assertEquals(expResult, actualResult);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
