import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File(args[0]);
        Scanner scanner = new Scanner(file) ;

        List<Integer> listNums = new ArrayList<>();

        try {
            while (scanner.hasNextInt()) {
                listNums.add(Integer.parseInt(scanner.nextLine()));
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод данных в файле");
        }
        scanner.close();

        Collections.sort(listNums);

        int center = listNums.size() / 2;
        int ans = 0;

        for (Integer n : listNums) {
            ans += Math.abs(n - listNums.get(center));
        }
        System.out.print(ans);
    }
}
