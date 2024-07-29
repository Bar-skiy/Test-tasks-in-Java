package org.barm;

import java.util.List;

public class Test {

    private int id;
    private String title;
    private String value;
    private List<Test> values;

    @Override
    public String toString() {
        return "id: " + id + ", title: " + title + ", value: " + value + ", values: " + values;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Test> getValues() {
        return values;
    }

    public void setValues(List<Test> values) {
        this.values = values;
    }
}
