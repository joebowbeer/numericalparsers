package com.joebowbeer;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericalParsers {

  private NumericalParsers() { }

  public static int parseRomanNumerals(CharSequence s) {
    Map<Character, Integer> map = Collections.unmodifiableMap(Stream.of(
        new SimpleEntry<>('I', 1),
        new SimpleEntry<>('V', 5),
        new SimpleEntry<>('X', 10),
        new SimpleEntry<>('L', 50),
        new SimpleEntry<>('C', 100),
        new SimpleEntry<>('D', 500),
        new SimpleEntry<>('M', 1000))
        .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));
    int total = 0;
    int minuend = 0;
    for (int index = s.length(); --index >= 0; ) {
      int value = map.get(s.charAt(index));
      if (value < minuend) {
        total -= value;
        minuend = 0;
      } else {
        total += value;
        minuend = value;
      }
    }
    return total;
  }

  public static int parseEnglishNumerals(String s) {
    Map<String, Integer> dict = Collections.unmodifiableMap(Stream.of(
        new SimpleEntry<>("one", 1),
        new SimpleEntry<>("two", 2),
        new SimpleEntry<>("three", 3),
        new SimpleEntry<>("four", 4),
        new SimpleEntry<>("five", 5),
        new SimpleEntry<>("six", 6),
        new SimpleEntry<>("seven", 7),
        new SimpleEntry<>("eight", 8),
        new SimpleEntry<>("nine", 9),
        new SimpleEntry<>("ten", 10),
        new SimpleEntry<>("eleven", 11),
        new SimpleEntry<>("twelve", 12),
        new SimpleEntry<>("thirteen", 13),
        new SimpleEntry<>("fourteen", 14),
        new SimpleEntry<>("fifteen", 15),
        new SimpleEntry<>("sixteen", 16),
        new SimpleEntry<>("seventeen", 17),
        new SimpleEntry<>("eighteen", 18),
        new SimpleEntry<>("nineteen", 19),
        new SimpleEntry<>("twenty", 20),
        new SimpleEntry<>("thirty", 30),
        new SimpleEntry<>("forty", 40),
        new SimpleEntry<>("fifty", 50),
        new SimpleEntry<>("sixty", 60),
        new SimpleEntry<>("seventy", 70),
        new SimpleEntry<>("eighty", 80),
        new SimpleEntry<>("ninety", 90),
        new SimpleEntry<>("hundred", 100),
        new SimpleEntry<>("thousand", 1000),
        new SimpleEntry<>("million", 1000000))
        .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

    IntStream values = Arrays.stream(s.replaceAll(" and ", " ").split(" ")).mapToInt(dict::get);

    // This expression correctly evaluates a surprising number of cases:
    // return values.reduce(0, (acc, e) -> (acc != 0 && acc < e) ? acc * e : acc + e);

    Eval tree = new Eval();
    values.forEachOrdered(tree::append);
    return tree.eval();
  }

  static class Eval {

    /**
     * Represents a numerical expression as a heap. The value in each node is greater or equal
     * to all values in its left and right subtrees. The values in each node's left subtree were
     * appended before the node's value was appended, and values in the right subtree were inserted
     * after this node was inserted.
     */
    static class Node {

      Node left;
      int value;
      Node right;
 
      Node(int value) {
        this.value = value;
      }

      Node(Node left, int value) {
        this.left = left;
        this.value = value;
      }

      /**
       * Returns right subtree's value summed with the product of this node's value and the
       * left subtree's value.
       */
      int eval() {
        return ((left != null) ? left.eval() : 1) * value + ((right != null) ? right.eval() : 0);
      }
    }

    Node root;

    /**
     * Inserts the given value in the tree. The given value will be inserted as a new root
     * if it is greater than the current root's value. Otherwise, the given value will be
     * inserted as the rightmost descendent of the existing root.
     * 
     * @param value 
     */
    void append(int value) {
      if (root == null || value > root.value) {
        root = new Node(root, value);
      } else if (value < root.value) {
        Node node;
        for (node = root; node.right != null && node.right.value > value; node = node.right) { }
        node.right = new Node(node.right, value);
      } else {
        throw new IllegalArgumentException();
      }
    }

    int eval() {
      return (root != null) ? root.eval() : 0;
    }
  }
}
