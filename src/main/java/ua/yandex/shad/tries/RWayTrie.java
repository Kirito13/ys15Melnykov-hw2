package ua.yandex.shad.tries;

import java.util.LinkedList;

public class RWayTrie implements Trie {

    private static final int ALPHABETSIZE = 26;
    private static final char FIRSTELEMENT = 'a';
    private Node root;

    public RWayTrie() {
        root = new Node();
    }

    public static class Node {

        private int weight;
        private Node[] next;

        public Node() {
            next = new Node[ALPHABETSIZE];
        }
    }

    //have problem with testing method add(private or public)....
    /*
     public Node getroot() {
     Node x = new Node();
     x.weight = root.weight;
     x.next = root.next;
     return x;
     }*/
    @Override
    public void add(Tuple t) {
        //throw new UnsupportedOperationException("Not supported yet.");
        root = add(root, t, 0);
    }

    private Node add(Node x, Tuple t, int d) {
        String term = t.getTerm();
        int weight = t.getWeight();
        if (d == weight) {
            x.weight = weight;
            return x;
        }
        int c = (term.charAt(d) - FIRSTELEMENT);
        if (x.next[c] == null) {
            x.next[c] = new Node();
        }
        x.next[c] = add(x.next[c], t, d + 1);
        return x;
    }

    @Override
    public boolean contains(String word) {
        //throw new UnsupportedOperationException("Not supported yet.");
        Node x = contains(root, word, 0);
        if (x == null) {
            return false;
        }
        return true;
    }

    private Node contains(Node x, String word, int d) {
        if (x == null) {
            return null;
        }
        if (d == word.length()) {
            return x;
        }
        int c = word.charAt(d) - FIRSTELEMENT;
        return contains(x.next[c], word, d + 1);
    }

    @Override
    public boolean delete(String word) {
        //throw new UnsupportedOperationException("Not supported yet.");
        if (!contains(word)) {
            return false;
        }
        root = delete(root, word, 0);
        return true;
    }

    private Node delete(Node x, String word, int d) {
        /*if (x == null) {
         return null;
         }*/
        if (d == word.length()) {
            x.weight = 0;
        } else {
            int c = word.charAt(d) - FIRSTELEMENT;
            x.next[c] = delete(x.next[c], word, d + 1);
        }
        /*
        if (x.weight != 0) {
            return x;
        }*/

        for (int i = 0; i < ALPHABETSIZE; i++) {
            if (x.next[i] != null) {
                return x;
            }
        }
        return null;
    }

    @Override
    public Iterable<String> words() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return takeWords(root);
    }

    private Iterable<String> takeWords(Node x) {
        LinkedList<String> answer = new LinkedList<>();
        LinkedList<Node> temp = new LinkedList<Node>();
        LinkedList<String> tempString = new LinkedList<String>();
        temp.add(x);
        tempString.add("");
        while (!temp.isEmpty()) {
            String s = tempString.pollFirst();
            Node y = temp.pollFirst();
            if (y.weight != 0) {
                answer.add(s);
            }
            for (int i = 0; i < ALPHABETSIZE; i++) {
                Node next = y.next[i];
                if (next != null) {
                    temp.addLast(next);
                    tempString.addLast(s + (char) (i + FIRSTELEMENT));
                    //System.out.print(tempString.getLast());
                }
            }
        }
        return answer;
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        //throw new UnsupportedOperationException("Not supported yet.");
        LinkedList<String> noCheckAnswer = new LinkedList<String>();
        Node x = root;
        for (int i = 0; i < s.length(); i++) {
            x = x.next[s.charAt(i) - FIRSTELEMENT];
            if (x == null) {
                return noCheckAnswer;
            }
        }
        noCheckAnswer = (LinkedList<String>) takeWords(x);
        //System.out.println(noCheckAnswer.size());
        LinkedList<String> finalAnswer = new LinkedList<String>();
        String element = "";
        int count = noCheckAnswer.size();
        for (int i = 0; i < count; i++) {
            element = noCheckAnswer.pollFirst();
            finalAnswer.addLast(s + element);
        }
        //System.out.println(finalAnswer.size());
        return finalAnswer;
    }

    @Override
    public int size() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        int count = 0;
        if (x.weight != 0) {
            count++;
        }

        for (int i = 0; i < ALPHABETSIZE; i++) {
            count += size(x.next[i]);
        }
        return count;
    }

}
