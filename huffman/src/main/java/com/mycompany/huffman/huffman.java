/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.huffman;

/**
 *
 * @author pvv
 */
import java.io.File;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Node {

    int freq;
    char c;

    Node left;
    Node right;
}

class MyComparator implements Comparator<Node> {

    public int compare(Node x, Node y) {
        return x.freq - y.freq;
    }
}

public class huffman {

    private HashMap<Character, Integer> freq = new HashMap<>();
    public static Map<Character, String> dic = new HashMap<>();
    public static Map<String, Character> cid = new HashMap<>();
    public static String compressed = "", input = "", decompression = "";

    huffman(String x) {
        this.input = x;
    }

    public void frequency(String s) {
        for (int i = 0; i < s.length(); ++i) {
            if (freq.containsKey(s.charAt(i))) {
                freq.put(s.charAt(i), freq.get(s.charAt(i)) + 1);
            } else {
                freq.put(s.charAt(i), 1);
            }
        }

        for (HashMap.Entry<Character, Integer> entry : freq.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

    public static void buildtree(Node root, String s) {
        if (root.left
                == null
                && root.right
                == null
                && Character.isLetter(root.c)) {

            dic.put((char) (root.c), s);
            cid.put(s, (char) (root.c));

            System.out.println(root.c + ":" + s);

            return;
        }

        buildtree(root.left, s + "0");
        buildtree(root.right, s + "1");
    }

    public void compress() {
        for (int i = 0; i < input.length(); ++i) {
            compressed += dic.get(input.charAt(i));
        }
    }

    public void decompression() {
        String temp = "";

        for (int i = 0; i < compressed.length(); ++i) {
            temp += compressed.charAt(i);
            if (dic.containsValue(temp)) {
                decompression += cid.get(temp);
                temp = "";
            }
        }

        System.out.println(decompression);
    }

    public static void main(String[] args) throws IOException {

        File file = new File("C:\\Users\\pvv\\Desktop\\huffman\\files\\text.txt");
        FileControl xyz = new FileControl();
        xyz.readfile(file);

        String s = "";
        s = xyz.text;
        huffman h = new huffman(s);
        System.out.println("<-----------------------The frequency----------------------------->");

        h.frequency(s);

        PriorityQueue<Node> q
                = new PriorityQueue<Node>(h.freq.size(), new MyComparator());

        for (Map.Entry<Character, Integer> set : h.freq.entrySet()) {

            Node hn = new Node();

            hn.c = set.getKey();
            hn.freq = set.getValue();

            hn.left = null;
            hn.right = null;

            q.add(hn);
        }

        Node root = null;

        while (q.size() > 1) {

            Node x = q.peek();
            q.poll();

            Node y = q.peek();
            q.poll();

            Node f = new Node();

            f.freq = x.freq + y.freq;
            f.c = '-';

            f.left = x;

            f.right = y;

            root = f;

            q.add(f);
        }
        System.out.println("<-----------------------The dictionary----------------------------->");
        buildtree(root, "");
        System.out.print("Compression :  ");
        h.compress();
        System.out.println(h.compressed);
        xyz.text = compressed;  // save compression string to file 
        File file2 = new File("C:\\Users\\pvv\\Desktop\\huffman\\files\\compressed.txt");
        xyz.write(file2);
   System.out.print("Decompression :  ");
        h.decompression();

        xyz.text = decompression; // save decompression string to file 
        File file3 = new File("C:\\Users\\pvv\\Desktop\\huffman\\files\\decompression.txt");
        xyz.write(file3);
    }

}
