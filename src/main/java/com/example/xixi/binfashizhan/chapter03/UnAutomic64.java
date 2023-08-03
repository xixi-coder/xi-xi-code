package com.example.xixi.binfashizhan.chapter03;

/**
 * @author : xi-xi
 */
public class UnAutomic64 {


        private double value;

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public static void main(String[] args) throws InterruptedException {

            UnAutomic64 a = new UnAutomic64();
            System.out.println(a.getValue());
            new Thread(() -> System.out.println(a.getValue())).start();
            Thread.sleep(1);
            a.setValue(8);
            System.out.println(a.getValue());

        }



}
