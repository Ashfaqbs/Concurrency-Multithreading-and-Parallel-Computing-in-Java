package org.ashfaq.dev.concepts;

public class LivelockExample {
    static class Spoon {
        private Diner owner;

        public Spoon(Diner d) {
            owner = d;
        }

        public synchronized void use() {
            System.out.println(owner.name + " is using the spoon.");
        }

        public synchronized void pass(Diner newOwner) {
            System.out.println(owner.name + " passes the spoon to " + newOwner.name);
            owner = newOwner;
        }
    }

    static class Diner {
        private String name;
        private boolean isHungry;

        public Diner(String n) {
            name = n;
            isHungry = true;
        }

        public void eatWith(Spoon spoon, Diner spouse) {
            while (isHungry) {
                // Check if spouse is not hungry and pass the spoon
                if (!spouse.isHungry) {
                    try {
                        Thread.sleep(100); // Simulating some work before passing the spoon
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    spoon.pass(spouse);
                    continue;
                }
                // Simulate eating
                spoon.use();
                isHungry = false;
                System.out.println(name + " is no longer hungry.");
            }
        }
    }

    public static void main(String[] args) {
        final Diner husband = new Diner("Husband");
        final Diner wife = new Diner("Wife");

        final Spoon sharedSpoon = new Spoon(husband);

        Thread husbandThread = new Thread(() -> husband.eatWith(sharedSpoon, wife));
        Thread wifeThread = new Thread(() -> wife.eatWith(sharedSpoon, husband));

        husbandThread.start();
        wifeThread.start();
    }
}
//
//In this example, we have two diners (husband and wife) who are sharing a spoon (Spoon object). They alternate in 
//using the spoon, but there's a condition that they pass the spoon to each other only when the other diner is not 
//hungry. However, both diners are always hungry, so they keep passing the spoon back and forth without making progress
//in eating. This situation demonstrates a livelock.
//
//When you run this program, you will see that the diners are passing the spoon continuously without making any 
//progress in eating, indicating a livelock scenario
