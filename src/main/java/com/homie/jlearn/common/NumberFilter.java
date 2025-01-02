package com.homie.jlearn.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NumberFilter<FIELD_TYPE extends Comparable<? super FIELD_TYPE>> extends Filter<FIELD_TYPE> {

    private static final long serialVersionUID = 1L;
    private FIELD_TYPE gt;
    private FIELD_TYPE lt;
    private FIELD_TYPE gte;
    private FIELD_TYPE lte;

    @Override
    public boolean hasValue() {
        return super.hasValue() || gt != null || lt != null || gte != null || lte != null;
    }
}
