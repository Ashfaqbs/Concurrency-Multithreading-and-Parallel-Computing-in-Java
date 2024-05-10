package org.ashfaq.dev.concepts;

public class LivelockExampleDemo {
    static class Person {
        private String name;
        private boolean isMovingRight = true;  // Initial direction

        public Person(String name) {
            this.name = name;
        }

        // Method to pass the other person
        public void pass(Person other) {
            while (this.isMovingRight == other.isMovingRight) {
                System.out.println(name + ": Oh, I should change my direction to let " + other.name + " pass!");
                // Change direction
                isMovingRight = !isMovingRight;
                try {
                    Thread.sleep(100); // Simulating decision-making time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        final Person alice = new Person("Alice");
        final Person bob = new Person("Bob");

        // Thread for Alice
        Thread thread1 = new Thread(() -> {
            while (true) {
                alice.pass(bob);
            }
        });

        // Thread for Bob
        Thread thread2 = new Thread(() -> {
            while (true) {
                bob.pass(alice);
            }
        });

        thread1.start();
        thread2.start();
    }
}

