
        public class Task1 {
            public static void main(String[] args) {
                if (args.length != 2){
                    System.out.println("Введите два целых числовых значений, больше нуля, в формате n m через пробел");
                    System.exit(0);
                }

                int n = 0;
                int m = 0;

                try {
                    n = Integer.parseInt(args[0]);
                    m = Integer.parseInt(args[1]);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Введите числовые значения!");
                    System.exit(0);
                }
                if (n < 1 || m < 1){
                    System.out.println("Введите значения больше нуля");
                    System.exit(0);
                }

                String ans = "1";

                for (int i = 1; i < n; i++) {

                    int ind = (m - 1) * i;
                    int value = (ind + 1) % n;
                    // 8 % 3 = 2 -> 6/3 остаток 8-6 = 2
                    if (value == 0) {value = n;}

                    if (value == 1) break;

                    ans = ans + value;
                }
                System.out.print( "Полученный путь: " + ans);
            }
        }

