public class Runner {
    public static void main(String[] args) {
        int a = 5;
        int b = 8;

        System.out.println(" До обмена : \na = " + a + "\nb = " + b);
        int[] result = swapWithoutTemp(a, b);
        a = result[0];
        b = result[1];
        System.out.println(" После обмена : \na = " + a + "\nb = " + b);
    }

    public static int[] swapWithoutTemp(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;
        return new int[]{a, b};
    }
}
