import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Task3 {

    // Классы для представления структуры JSON
     static class Test {
        int id;
        String title;
        String value;
        Test[] values; // вложенные тесты

     //конструктор class Test
        Test(int id, String title, String value, Test[] values) {
            this.id = id;
            this.title = title;
            this.value = value;
            this.values = values;
        }
    }

    static class TestsJson {
        Test[] tests;

        TestsJson(Test[] tests) {
            this.tests = tests;
        }
    }

    static class Value {
        int id;
        String value;

        Value(int id, String value) {
            this.id = id;
            this.value = value;
        }
    }

    static class ValuesJson {
        Value[] values;

        ValuesJson(Value[] values) {
            this.values = values;
        }
    }

    public static void main(String[] args) {

        // Пути к файлам
        String valuesFilePath = args[0];
        String testsFilePath = args[1];
        String reportFilePath = args[2];
//        System.out.println(valuesFilePath + "файл значения");

        // Создаем объект Gson с включенным форматированием,
        // метод setPrettyPrinting() позволяет форматировать вывод JSON с отступами и переносами строк.
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            // Считываем values.json и записываем в создаваемый экземпляр класса ValuesJson
            // Метод gson.fromJson() - конвертирует Json в объект
            FileReader valuesReader = new FileReader(valuesFilePath);
            ValuesJson valuesJson = gson.fromJson(valuesReader, ValuesJson.class);
            valuesReader.close();


            // Преобразуем values в Map для быстрого поиска по id
            Map<Integer, String> valuesMap = new HashMap<>();
            for (Value value : valuesJson.values) {
                valuesMap.put(value.id, value.value);
            }

            // Считываем tests.json
            FileReader testsReader = new FileReader(testsFilePath);
            TestsJson testsJson = gson.fromJson(testsReader, TestsJson.class);
            testsReader.close();

            // Заполняем значения в tests.json
            fillValues(testsJson.tests, valuesMap);

            // Записываем результат в report.json
            FileWriter writer = new FileWriter(reportFilePath);
            gson.toJson(testsJson, writer);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Рекурсивная функция для заполнения значений
    private static void fillValues(Test[] tests, Map<Integer, String> valuesMap) {
        for (Test test : tests) {
            if (valuesMap.containsKey(test.id)) {
                test.value = valuesMap.get(test.id);
            }
            if (test.values != null) {
                fillValues(test.values, valuesMap);
            }
        }
    }
}