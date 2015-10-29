/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.shad.autocomplete;

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
public class PrefixMatchesTest {

    public void assertListEquals(LinkedList<String> expResult,
            LinkedList<String> actualResult) {
        if (expResult.size() != actualResult.size()) {
            throw new AssertionError("the lenghts of expList:"
                    + Integer.toString(expResult.size())
                    + " and actualList, are different");
        }
        for (String expResult1 : expResult) {
            if (!(actualResult.contains(expResult1))) {
                throw new AssertionError("actualResult list doesn`t contain "
                        + "this string: " + expResult1);
            }
        }
    }

    @Test
    public void loadTest() {
        System.out.println("load");// i must know what i wanna test
        PrefixMatches memory = new PrefixMatches();
        int expResult = 8;
        int actualResult = memory.load("what do you want", "just do it",
                "We love java", "hello world");
        assertEquals(expResult, actualResult);
    }

    @Test
    public void containsTest() {
        System.out.println("contains");
        PrefixMatches memory = new PrefixMatches();
        memory.load("what do you want", "just do it",
                "We love java", "hello world");
        boolean expResult = true;
        boolean actualResult = memory.contains("world");
        assertEquals(expResult, actualResult);
    }

    @Test
    public void containsTestWithNoWords() {
        System.out.println("contains");
        PrefixMatches memory = new PrefixMatches();
        memory.load("asdasd");
        boolean expResult = false;
        boolean actualResult = memory.contains("world");
        assertEquals(expResult, actualResult);
    }

    @Test
    public void deleteTest() {
        System.out.println("delete");
        PrefixMatches memory = new PrefixMatches();
        memory.load("what do you want", "just do it",
                "We love java", "hello world");
        boolean expResult = true;
        boolean actualResult = memory.delete("world");
        assertEquals(expResult, actualResult);
    }

    @Test
    public void deleteTestWithNoWords() {
        System.out.println("delete");
        PrefixMatches memory = new PrefixMatches();
        memory.load("what do you want", "just do it",
                "We love java", "hello world");
        boolean expResult = false;
        boolean actualResult = memory.delete("alala");
        assertEquals(expResult, actualResult);
    }

    @Test
    public void testWordsWithPrefixString() {
        System.out.println("wordsWithPrefix");
        String pref = "java";
        PrefixMatches memory = new PrefixMatches();
        memory.load("hello world with java", "javascript", "javac", "javah",
                "abcd", "abcde", "abcdefgh", "abce");
        Iterable<String> list = new LinkedList<String>(Arrays.asList("java",
                "javac", "javah", "javascript"));
        Iterable<String> answer = memory.wordsWithPrefix(pref);
        LinkedList<String> expResult = (LinkedList) list;
        LinkedList<String> actualResult = (LinkedList) answer;
        assertListEquals(expResult, actualResult);
    }

    @Test
    public void testWordsWithPrefixStringIntBigCount() {
        System.out.println("wordsWithPrefixInt");
        String pref = "java";
        PrefixMatches memory = new PrefixMatches();
        memory.load("hello world with java", "javascript", "javac", "javah",
                "abcd", "abcde", "abcdefgh", "abce");
        Iterable<String> list = new LinkedList<String>(Arrays.asList("java",
                "javac", "javah", "javascript"));
        Iterable<String> answer = memory.wordsWithPrefix(pref, 4);
        LinkedList<String> expResult = (LinkedList) list;
        LinkedList<String> actualResult = (LinkedList) answer;
        assertListEquals(expResult, actualResult);
    }

    @Test
    public void testWordsWithPrefixStringInt() {
        System.out.println("wordsWithPrefixInt");
        String pref = "java";
        PrefixMatches memory = new PrefixMatches();
        memory.load("hello world with java", "javascript", "javac", "javah",
                "abcd", "abcde", "abcdefgh", "abce");
        Iterable<String> list = new LinkedList<String>(Arrays.asList("java",
                "javac", "javah"));
        Iterable<String> answer = memory.wordsWithPrefix(pref, 2);
        LinkedList<String> expResult = (LinkedList) list;
        LinkedList<String> actualResult = (LinkedList) answer;
        assertListEquals(expResult, actualResult);
    }

    @Test
    public void testSize() {
        System.out.println("size");
        PrefixMatches memory = new PrefixMatches();
        memory.load("hello world with java", "javascript", "javac", "javah",
                "abcd", "abcde", "abcdefgh", "abce");
        int expResult = 11;
        int actualResult = memory.size();
        assertEquals(expResult, actualResult);
    }
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
