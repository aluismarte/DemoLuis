package edu.alsjava.courses.demoluis.model.network.response;

import java.util.List;

import edu.alsjava.courses.demoluis.model.Example;

/**
 * Created by aluis on 11/26/19.
 */
public class DemoResponse {

    private int total;
    private List<Example> examples;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Example> getExamples() {
        return examples;
    }

    public void setExamples(List<Example> examples) {
        this.examples = examples;
    }
}
