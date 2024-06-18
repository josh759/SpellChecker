package MINIPROJECT;

public class TernarySearchTrie {
    private Node root;

    private class Node {
        char character;
        Node left, middle, right;
        Integer value;
    }

    public void put(String key, Integer value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, Integer value, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.character = c;
        }
        if (c < x.character) {
            x.left = put(x.left, key, value, d);
        } else if (c > x.character) {
            x.right = put(x.right, key, value, d);
        } else if (d < key.length() - 1) {
            x.middle = put(x.middle, key, value, d + 1);
        } else {
            // x.value = value;
            if (x.value == null) {
                x.value = value;
            } else {
                x.value += value; // assuming you want to increment the value
            }
        }
        return x;
    }

    public boolean contains(String key) {
        return get(root, key, 0) != null;
    }

    private Node get(Node x, String key, int d) {
        if (x == null)
            return null;
        char c = key.charAt(d);
        if (c < x.character) {
            return get(x.left, key, d);
        } else if (c > x.character) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.middle, key, d + 1);
        } else {
            return x;
        }
    }
}
