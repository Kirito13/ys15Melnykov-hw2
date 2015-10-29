/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.shad.autocomplete;

import ua.yandex.shad.tries.Tuple;
import ua.yandex.shad.tries.Trie;
import ua.yandex.shad.tries.RWayTrie;
import java.util.LinkedList;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private static final int DEFPREF = 3;
    private Trie trie;

    public PrefixMatches() {
        trie = new RWayTrie();
    }

    public int load(String... strings) {
        //throw new UnsupportedOperationException("Not supported yet.");
        int sizeM = 0;
        for (String element : strings) {
            String[] array = element.split(" ");
            for (String word : array) {
                if (word.length() > 2) {
                    trie.add(new Tuple(word, word.length()));
                    sizeM++;
                }
            }
        }
        return sizeM;
    }

    public boolean contains(String word) {
        //throw new UnsupportedOperationException("Not supported yet.");
        return trie.contains(word);
    }

    public boolean delete(String word) {
        //throw new UnsupportedOperationException("Not supported yet.");
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        //throw new UnsupportedOperationException("Not supported yet.");
        return wordsWithPrefix(pref, DEFPREF);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        //throw new UnsupportedOperationException("Not supported yet.");
        LinkedList<String> answer = new LinkedList<String>();
        LinkedList<String> preAnswer = (LinkedList) trie.wordsWithPrefix(pref);
        int count = k - 1;
        String s = preAnswer.peekFirst();
        for (String temp : preAnswer) {
            if (s.length() < temp.length()) {
                count--;
            }
            if (count < 0) {
                return answer;
            }
            answer.add(temp);
            s = temp;
        }
        return answer;
    }

    public int size() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return trie.size();
    }
}
