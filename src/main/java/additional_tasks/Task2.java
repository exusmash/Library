package additional_tasks;

public class Task2 {
    public static void main(String[] args) {
        System.out.println(isPrime(4));
    }

    public static boolean isPrime(int number) {
        for (int i = 2; i <= Math.sqrt(number) + 1; i++) {
            if (number % i == 0){
                return false;
            }
        }
        return true;
    }
}
