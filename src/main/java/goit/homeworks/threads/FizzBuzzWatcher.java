package IK.threads;

public class FizzBuzzWatcher {

    public static void main(String[] args) {
        // TASK 2
        // створення об'єкту і викликання методу, який слідкує за числами, що діляться на 3 і 5 окремо і водночас до 30
        FizzBuzz fizzBuzz = new FizzBuzz(30);
        fizzBuzz.start();
    }

}
