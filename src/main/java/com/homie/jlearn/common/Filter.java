package com.homie.jlearn.common;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filter<FIELD_TYPE> implements Serializable {

    private static final long serialVersionUID = 1L;
    private FIELD_TYPE eq;
    private FIELD_TYPE ne;
    private List<FIELD_TYPE> in;
    private List<FIELD_TYPE> nin;

    public boolean hasValue() {
        return eq != null || ne != null || (in != null && !in.isEmpty()) || (nin != null && !nin.isEmpty());
    }
}
