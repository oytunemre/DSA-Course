
import java.util.NoSuchElementException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author oytunemreozmel
 */
public class BST {

    int size;
    private Node root;
    int index;

    public BST() {
        root = null;
        size = 0;

    }

    void insert(int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {
        Song song = new Song();

        if (root == null) {
            root = new Node(song, key);
            return root;
        }

        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }
        size++;
        return root;
    }

    public void postOrder(Node root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            //Visit the node by Printing the node data  
            System.out.print(root.key + " ");
        }
    }

    public Node getRootNode() {
        return root;
    }

// search song 
    public Node searchSong(String songName) {

        Node temp = root;

        int key = songName.hashCode() + 17 * 31;

        while (temp != null) {

            if (temp.SongKey(songName) == key) {
                return temp;
            } else if (temp.SongKey(songName) > key) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }

        }
        return null;
    }

    // search artist
    public Node searchArtist(String artist) {

        Node temp = root;

        int key = artist.hashCode() + 17 * 31;

        while (temp != null) {

            if (temp.artistKey(artist) == key) {
                return temp;
            } else if (temp.artistKey(artist) > key) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }

        }
        return null;

    }

    // list all user between id interval
    public Node searchID(int lowerBound, int upperBound) {

        return null;

    }

    // display all song in given genre...
    public Node searchGenre(String genre) {

        Node temp = root;

        int key = genre.hashCode() + 17 * 31;

        while (temp != null) {

            if (temp.GenreKey(genre) == key) {
                return temp;
            } else if (temp.GenreKey(genre) > key) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }

        }
        return null;

    }

    public String toStringPostOrder(Node root) {
        String result = "";
        if (root == null) {
            return "";
        }

        result += toStringPostOrder(root.left);
        result += toStringPostOrder(root.right);
        result += root.toString();
        return result;
    }
void deleteKey(int key) { root = deleteRec(root, key); }

	/* A recursive function to
	delete an existing key in BST
	*/
	Node deleteRec(Node root, int key)
	{
		/* Base Case: If the tree is empty */
		if (root == null)
			return root;

		/* Otherwise, recur down the tree */
		if (key < root.key)
			root.left = deleteRec(root.left, key);
		else if (key > root.key)
			root.right = deleteRec(root.right, key);

		// if key is same as root's
		// key, then This is the
		// node to be deleted
		else {
			// node with only one child or no child
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;

			// node with two children: Get the inorder
			// successor (smallest in the right subtree)
			root.key = minValue(root.right);

			// Delete the inorder successor
			root.right = deleteRec(root.right, root.key);
		}

		return root;
	}

	int minValue(Node root)
	{
		int minv = root.key;
		while (root.left != null)
		{
			minv = root.left.key;
			root = root.left;
		}
		return minv;
	}
}

class Node extends Song {

    int key;
    Node left, right;

    public Node(Song song, int key) {
        key = song.songObjKey();
        left = right = null;
    }

    @Override
    public String toString() {

        String str = String.valueOf(key);

        return str;

    }

}
