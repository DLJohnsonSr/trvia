package com.d2j2.trvia.apiEntities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Questions {

    private String response_code;
    private ArrayList<Results>results;

    public Questions() {
    }

    public String getResponse_code() {
        return response_code;
    }

    public void setResponse_code(String response_code) {
        this.response_code = response_code;
    }

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "response_code='" + response_code + '\'' +
                ", results=" + results +
                '}';
    }
}
