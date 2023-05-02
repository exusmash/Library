package additional_tasks;

public class Task1 {
    public static void main(String[] args) {
        System.out.println(isArmstrong(154));
    }

    public static boolean isArmstrong(int number) {
        String strNumber = String.valueOf(number);
        int res = 0;
        for (int i = 0; i < strNumber.length(); i++) {
            res += Math.pow(Character.getNumericValue(strNumber.charAt(i)), strNumber.length());
        }
        return number == res;
    }
}
