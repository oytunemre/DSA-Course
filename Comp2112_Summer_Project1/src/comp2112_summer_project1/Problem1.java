/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package comp2112_summer_project1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author oytunemreozmel
 */
public class Problem1 {

    static CircularLinkedlist PlayerListCircularLinkedlist = new CircularLinkedlist();

    public static void main(String[] args) throws FileNotFoundException, IOException {

        Problem1();

    }

    public static void Problem1() throws FileNotFoundException, IOException {

        File f = new File("/Users/oytunemreozmel/NetBeansProjects/Comp2112_Summer_Project1/src/comp2112_summer_project1/Problem1names.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        String st;
        System.out.println("Begining of the file ");
        while ((st = br.readLine()) != null) {
            PlayerListCircularLinkedlist.insert(st);
            System.out.println(st);
        }
        System.out.print("");
        System.out.println("End of the file ");
        Node node = null;
        System.out.println("============================================");
        System.out.println("Problem 1");
        System.out.println("Reading problem 1 list");
        Node winner = null;
        while (PlayerListCircularLinkedlist.getSize() > 1) {
            int kthNode = (int) (Math.random() * PlayerListCircularLinkedlist.getSize());
            System.out.println("Kth Value is : " + kthNode);

            node = PlayerListCircularLinkedlist.getRandomNodeFromTheList(kthNode);

            if (node == null) {
                System.out.println("No players at kthNode");

            }
            PlayerListCircularLinkedlist.display(PlayerListCircularLinkedlist);
            System.out.println("Selected student is : " + node.tempValue);

            String removeLetter = PlayerListCircularLinkedlist.replaceLastLetter(PlayerListCircularLinkedlist, kthNode);
            System.out.println("Last letter removed from name " + node.tempValue);

            if (removeLetter == null || removeLetter.equals("_")) {
                System.out.println(node.tempValue + " has no letters left, they will be removed from the list :(");
                PlayerListCircularLinkedlist.removeNodesWithNoLetters(node);
            }
            winner = PlayerListCircularLinkedlist.getFirst();
        }

        System.out.println("===========================================");
        if (winner != null) {
            System.out.println("The winner is " + winner.tempValue);
        } else {
            System.out.println("No winner");
        }
    }

}

class CircularLinkedlist {

    Node last, first;
    int size;

    CircularLinkedlist() {
        first = null;
        size = 0;

    }

    public Node getFirst() {
        return first;
    }

    public Node returnLastNodeInTheList(CircularLinkedlist circularLinkedlist) {
        Node node = circularLinkedlist.first;
        if (isEmpty()) {
            return null;
        } else {
            return node;
        }
    }

    public Node getRandomNodeFromTheList(int random) {
        Node temp = first;
        for (int i = 1; i <= random; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public void insert(String value) {

        Node newNode = new Node(value);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
            size++;
        } else {
            last.next = newNode;
            last = newNode;
            newNode.next = first;
            size++;

        }
    }

    public void removeNodesWithNoLetters(Node nodeToRemove) {
        if (isEmpty()) {
            System.out.println("list is empty");
            return;
        } else if (first == nodeToRemove) {
            first = first.next;
            size--;
            return;
        }
        Node current = first;
        while (current.next != first) {
            if (current.next == nodeToRemove) {
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int getSize() {
        return size;
    }

    void display(CircularLinkedlist circularLinkedlist) {
        Node temp = first;
        System.out.println("--------- Beginning of the list ---------");
        for (int i = 1; i <= circularLinkedlist.size; i++) {
            System.out.println(temp.value);
            temp = temp.next;
        }
        System.out.print("");
        System.out.println("------------ End of the list -----------");
    }

    
    public String replaceLastLetter(CircularLinkedlist circularLinkedlist, int random) {

        Node node = circularLinkedlist.getRandomNodeFromTheList(random);
        String value = node.value;
        if (value.isEmpty()) {
            return null;
        }
        value = value.substring(0, value.length() - 1);

        if (value.isEmpty()) {
            value = "_";
        } else if (value.endsWith("_")) {
            value = "_";
        }

        node.value = value;
        return value;
    }

    public String toString() {
        if (isEmpty()) {
            return "List is empty!";
        }
        Node tmp = first;
        String str = "List with " + size + " elements: ";
        while (tmp != null) {
            str += tmp.value + "->";
            tmp = tmp.next;
        }
        str += "\n First: " + first + "\t ";
        return str;
    }

}

class Node {

    String value, tempValue;
    int index;
    Node next;

    public Node(String data) {

        value = data;
        tempValue = data;
        next = null;
        index = 1;

    }

    public String toString() {
        return value + " ";
    }

    public int getIndex() {
        return index;
    }
}
