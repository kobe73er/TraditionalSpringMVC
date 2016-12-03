package com.dengpf.Lab.ThinkingInJava.ProxyTest1;

/**
 * Created by kobe73er on 16/12/3.
 */
public class ProxyTest {

    interface DoSomething {
        void doFirstThing();

        void doSecondThing();
    }


    static class ConcreteClass implements DoSomething {

        @Override
        public void doFirstThing() {
            System.out.println("concreate class do First Thing!");
        }

        @Override
        public void doSecondThing() {
            System.out.println("concreate class do Second Thing!");
        }
    }

    static class ProxyOne implements DoSomething {

        private DoSomething instance;

        public ProxyOne(DoSomething instance) {
            this.instance = instance;
        }

        @Override
        public void doFirstThing() {
            System.out.println("proxy do first thing!");
            instance.doFirstThing();
        }

        @Override
        public void doSecondThing() {
            System.out.println("proxy do second thing!");
            instance.doSecondThing();

        }
    }

    public static void main(String args[]) {
        new ProxyOne(new ConcreteClass()).doFirstThing();
        new ProxyOne(new ConcreteClass()).doSecondThing();

    }
}
