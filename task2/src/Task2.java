import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Task2 {
    public static void main(String[] args) {

        Map<String, Double> mapCircle = dataCircle(args[0]);
        ArrayList<double[]> listDots =  dataPoints(args[1]);
        ArrayList<Integer> ansList = poseCalculate(mapCircle, listDots);

        for (Integer ans : ansList) {
            System.out.println(ans);
        }
    }

    private static Map<String, Double> dataCircle(String file) {

        Map<String, Double> mapCircle = new HashMap<>();

        try (BufferedReader circleFile = new BufferedReader(new FileReader(file))) {

            String[] line = circleFile.readLine().split(" ");
            mapCircle.put("xc", Double.parseDouble(line[0]));
            mapCircle.put("yc", Double.parseDouble(line[1]));
            mapCircle.put("rc2", Math.pow(Double.parseDouble(circleFile.readLine()), 2));

        } catch (NumberFormatException | IOException e) {
            System.out.println("Некорректный ввод данных в файле circle.txt");
            throw new RuntimeException(e);
        }

        return mapCircle;
    }

    private static ArrayList<double[]> dataPoints(String file) {

        ArrayList<double[]> listDots = new ArrayList<>();

        try (BufferedReader dotsFile = new BufferedReader(new FileReader(file))) {

            String line;
            int counter = 0;
            while ((line = dotsFile.readLine()) != null) {
                double[] dotCoordinates = new double[] {
                  Double.parseDouble(line.split(" ")[0]),
                  Double.parseDouble(line.split(" ")[1])
                };
                listDots.add(dotCoordinates);
                counter++;
                if (counter  == 100) {
                    System.out.println("Сработало ограничение количества в 100 точек, лишние точки не учитываются");
                    break;
                }
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Некорректный ввод данных в файле dorts.txt");
            throw new RuntimeException(e);
        }
        if (listDots.isEmpty()) System.out.println("Введите координаты точек в файле dorts.txt");

        return listDots;
    }

    private static ArrayList<Integer> poseCalculate(Map<String, Double> mapCircle, ArrayList<double[]> listDots) {

        ArrayList<Integer> ansList = new ArrayList<>();

        for (double[] dot : listDots) {
            double ans = Math.pow(dot[0] - mapCircle.get("xc"), 2) + Math.pow(dot[1] - mapCircle.get("yc"), 2) - mapCircle.get("rc2");

            if (ans > 0) ansList.add(2);
            else if (ans == 0) ansList.add(0);
            else ansList.add(1);
        }
        return ansList;
    }
}