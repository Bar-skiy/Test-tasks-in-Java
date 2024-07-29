package org.barm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {

    private static final String tests = "tests";
    private static final String values = "values";
    private static List<Value> valueList;
    private static List<Test> testList;

    private static void getValues(String file) {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode;

        try {
            rootNode = mapper.readTree(new File(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        valueList = new ArrayList<>();
        for (JsonNode valueNode : rootNode.path(values)) {

            Value value = new Value();
            value.setId(valueNode.path("id").intValue());
            value.setValue(valueNode.path("value").textValue());
            valueList.add(value);
        }
    }

    private static void getTests(String file) {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode;

        try {
            rootNode = mapper.readTree(new File(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        testList = new ArrayList<>();
        for (JsonNode testNode : rootNode.path(tests)) {
            testList.add(parseNode(testNode));
        }
    }

    private static Test parseNode(JsonNode testNode) {
        Test test = new Test();
        test.setId(testNode.path("id").intValue());
        test.setTitle(testNode.path("title").textValue());
        test.setValue(testNode.path("value").textValue());
        if (testNode.has("values")) {
            List<Test> curTest = new ArrayList<>();
            for (JsonNode node : testNode.path("values")) {
                curTest.add(parseNode(node));
            }
            test.setValues(curTest);
        }
        return test;
    }

    private static void setValues(Test test) {
        for (Value curValue : valueList) {
            if (test.getId() == curValue.getId()) {
                test.setValue(curValue.getValue());
                valueList.remove(curValue);
                break;
            }
        }
        if (test.getValues() != null) {
            for (Test nestedTest : test.getValues()) {
                setValues(nestedTest);
            }
        }
    }

    private static void writeReport(String file) {

        Map<String, Object> map = new HashMap<>();
        map.put(tests, testList);

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(new File(file), map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        getValues(args[0]);
        getTests(args[1]);

        for (Test curTest : testList) setValues(curTest);

        writeReport(args[2]);
    }
}
