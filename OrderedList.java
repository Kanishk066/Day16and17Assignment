package com.bl.algorithmproblems;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class OrderedList<T extends Comparable<T>> {
    private LinkedList<T> list;

    public OrderedList() {
        list = new LinkedList<T>();
    }

    public void readFile(String fileName) throws IOException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            T num = (T) scanner.nextLine();
            insert(num);
        }
        scanner.close();
    }

    public void insert(T num) {
        if (list.isEmpty()) {
            list.add(num);
            return;
        }
        int i = 0;
        while (i < list.size() && num.compareTo(list.get(i)) > 0) {
            i++;
        }
        if (i == list.size()) {
            list.add(num);
        } else {
            list.add(i, num);
        }
    }

    public boolean search(T num) {
        return list.contains(num);
    }

    public void delete(T num) {
        list.remove(num);
    }

    public void writeFile(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for (T num : list) {
            writer.write(num.toString() + "\n");
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        OrderedList<Integer> list = new OrderedList<Integer>();
        list.readFile("numbers.txt");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = scanner.nextInt();
        scanner.close();
        if (list.search(num)) {
            list.delete(num);
        } else {
            list.insert(num);
        }
        list.writeFile("sorted_numbers.txt");
    }
}
