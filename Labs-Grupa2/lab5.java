import java.util.*;
/*
Да се креира структура за опис на фреквентен магацин, каде со функцијата push во магацинот се додава нов елемент на врвот на магацинот, 
а со функцијата pop се отстранува првото појавување на елементот кој има најголем број појавувања во магацинот. Доколку во еден момент има 
два елементи со ист број појавувања (најголеми фреквенции), pop функцијата го отстранува елементот кој е најблиско до врвот на магацинот.
 */

class FreqStack {
    private ArrayList<Integer> stack;

    public FreqStack() {
        stack = new ArrayList<>();
    }

    public void push(int x) {
        stack.add(x);
        System.out.println("Pushed: " + x);
    }

    private int getFrequency(int element) {
        int count = 0;
        for (int num : stack) {
            if (num == element) {
                count++;
            }
        }
        return count;
    }

    public int pop() {
        if (stack.isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }

        int maxFreq = 0;
        for (int num : stack) {
            int freq = getFrequency(num);
            maxFreq = Math.max(maxFreq, freq);
        }

        // Closest to top
        int elementToRemove = -1;
        int indexToRemove = -1;

        // Search from top to bottom
        for (int i = stack.size() - 1; i >= 0; i--) { //
            int currentElement = stack.get(i);
            if (getFrequency(currentElement) == maxFreq) {
                elementToRemove = currentElement; // koj treba da go izvadime
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
            stack.remove(indexToRemove);
            System.out.println("Popped most frequent element: " + elementToRemove);
            return elementToRemove;
        }

        return -1;
    }

    public void display() {
        if (stack.isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.println("\nCurrent stack : " + stack);
    }
}

public class lab5 {
    public static void main(String[] args) {
        FreqStack fs = new FreqStack();

        fs.push(5);
        fs.push(7);
        fs.push(5);
        fs.push(7);
        fs.push(4);
        fs.push(5);

        fs.pop();
        fs.display();
    }
}
