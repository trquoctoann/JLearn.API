package com.homie.jlearn.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StringFilter extends Filter<String> {

    private static final long serialVersionUID = 1L;
    private String contains;
    private String notContains;
}
