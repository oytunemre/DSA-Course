package comp2112_summer_project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author oytunemreozmel
 */
public class Problem2 {

    static DoubleLinkedlist allNamesOfTheStudents = new DoubleLinkedlist();
    static DoubleLinkedlist visitedStudents = new DoubleLinkedlist();

    static int k = (int) (Math.random() * 5) + 1;

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        int matched = 0;
        int matchedCount = 0;
        System.out.println("Reading Proble2names.txt");
        File();
        int total = allNamesOfTheStudents.getSize();
        while (visitedStudents.getSize() < total) {
            int randomNode = (int) (Math.random() * allNamesOfTheStudents.getSize());
            System.out.print("Enter the number of Hubs : ");
            int numberOfHubs = inputScanner.nextInt();
            System.out.println("Creating a linkedlist with "
                    + allNamesOfTheStudents.getSize() + " students," + numberOfHubs + " are Hubs.");
            System.out.print("Enter a message: ");
            String message = inputScanner.next();

            int randomDirection = randomDirection();
            if (randomDirection == 1) {
                System.out.println("Randomly generating direction :Right " + randomDirection);
            } else {
                System.out.println("Randomly generating direction :Left " + randomDirection);
            }
            DoubleNode SourceNode = allNamesOfTheStudents.getNodeFromTheList(randomNode);
            visitedStudents.insert(SourceNode.data);
            System.out.println("Randomly choose student: " + SourceNode.data);
            System.out.println("Randomly generating value of k:" + k);
            System.out.println("---Displaying the student message train. Hub students have a * on top of their name:");
            allNamesOfTheStudents.addHubMarks(allNamesOfTheStudents, k, numberOfHubs);
            allNamesOfTheStudents.printList();
            allNamesOfTheStudents.moveMessage(SourceNode, message, numberOfHubs);
            matchedCount = allNamesOfTheStudents.matchLetters(SourceNode, message);
            if (matchedCount > 0) {
                matched++;
            }
            SourceNode.count += matchedCount;
            System.out.println("matched letters: " + matchedCount);

        }
        System.out.println("===========================================");
        allNamesOfTheStudents.printListWithCommonLetterNumbers(matched);

    }

    public static void File() {
        Scanner scn = new Scanner(System.in);
        File file = new File("/Users/oytunemreozmel/NetBeansProjects/"
                + "Comp2112_Summer_Project1/src/comp2112_summer_project1/Problem2names.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                allNamesOfTheStudents.insert(line);
                // System.out.println(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public static int randomDirection() {
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            return 1;
        } else {
            return 0;
        }
    }

}

class DoubleLinkedlist {

    DoubleNode first;
    DoubleNode last;
    int size;

    public DoubleLinkedlist() {
        first = null;
        last = null;
        size = 0;

    }

    public DoubleNode DestinationNode(DoubleNode startNode, int k, int direction) {

        DoubleNode current = startNode;

        if (startNode == null) {
            throw new java.lang.NullPointerException();

        }

        if (direction == 0) {
            for (int i = 1; i < k; i++) {
                if (current == null || current.previous == null) {
                    throw new java.lang.NullPointerException();
                }
                current = current.previous;
            }
        } else if (direction == 1) {
            for (int i = 1; i <= k; i++) {
                if (current == null || current.next == null) {
                    throw new java.lang.NullPointerException();
                }
                current = current.next;
            }
        }

        return current;
    }

    public void moveMessage(DoubleNode sourceNode, String message, int hubs) {

        System.out.println("Source Node : " + sourceNode.data);
        sourceNode.message = message;

        DoubleNode currDoubleNode = this.first;

        while (currDoubleNode != null) {
            if (currDoubleNode.data.contains("*")) {
                System.out.println("Message reached to hub, changing direction");
                return;
            }
            if (currDoubleNode.previous == first || currDoubleNode.next == last) {
                System.out.println("you reach the end of the list direction will change");
                return;
            }

            currDoubleNode = currDoubleNode.next;
        }

    }

    public void addHubMarks(DoubleLinkedlist doubleLinkedlist, int step, int hubNumber) {

        Random random1 = new Random();
        DoubleNode current = doubleLinkedlist.getNodeFromTheList(random1.nextInt(doubleLinkedlist.getSize()));

        int count = 0;
        int countHub = 0;
        while (current != null) {
            if (count % step == 0) {

                if (!current.data.contains("*")) {
                    current.data += "*";
                    countHub++;
                    count++;
                }

            }

            if (countHub == hubNumber) {
                break;
            }
            if (hubNumber > doubleLinkedlist.getSize()) {
                System.out.println("Hub size is bigger than list");
                break;
            }

            if (count == doubleLinkedlist.getSize()) {
                break;
            }
            if (count == doubleLinkedlist.getSize() - 1) {
                break;
            }

            current = current.next;
            count++;
        }

    }

    public void insert(String data) {
        DoubleNode newNode = new DoubleNode(data);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
            size++;
        } else {
            newNode.next = first;
            first.previous = newNode;
            first = newNode;
            size++;
        }

    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printList() {

        DoubleNode tmp = this.first;
        while (tmp != null) {
            System.out.print(tmp.data + " <---> ");

            tmp = tmp.next;

        }
        System.out.println("");
    }

    public void printListWithCommonLetterNumbers(int number) {

        DoubleNode tmp = this.first;
        while (tmp != null) {
            System.out.print(tmp.data + " " + number + " <---> ");

            tmp = tmp.next;

        }
        System.out.println("");
    }

    public DoubleNode getNodeFromTheList(int random) {
        DoubleNode temp = first;
        for (int i = 0; i < random; i++) {
            if (temp == null) {
                throw new java.lang.NullPointerException("Index is out of bounds.");
            }
            temp = temp.next;
        }
        return temp;
    }

    public int matchLetters(DoubleNode node, String messageString) {
        String studentName = node.data;
        String visitedMessage = messageString;

        int count = 0;

        for (int i = 0; i < studentName.length(); i++) {
            for (int j = 0; j < visitedMessage.length(); j++) {
                if (studentName.charAt(i) == visitedMessage.charAt(j)) {
                    count++;
                }
            }
        }

        node.count = count;
        System.out.println(node.count);
        return count;
    }

    @Override
    public String toString() {
        return first.data;
    }
}

class DoubleNode {

    int count;
    String data;
    String message;
    DoubleNode next;
    DoubleNode previous;

    DoubleNode(String data) {
        this.data = data;
        next = null;
        previous = null;

    }

    public DoubleNode(String message, String data) {
        this.data = data;
        next = null;
        previous = null;
        count = 0;
        this.message = message;

    }

    public int count(int count) {
        return count;
    }

    public String toString() {
        if (next != null) {
            return data + " <--> " + next.toString();
        } else {
            return data;
        }
    }

}
