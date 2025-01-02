package com.homie.jlearn.common;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Query {

    private Set<String> fetchAttributes = new HashSet<>();

    public void addFetchAttributes(String attribute) {
        fetchAttributes.add(attribute);
    }
}
