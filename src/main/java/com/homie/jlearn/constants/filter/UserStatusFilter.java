package com.homie.jlearn.constants.filter;

import com.homie.jlearn.common.Filter;
import com.homie.jlearn.constants.UserStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserStatusFilter extends Filter<UserStatus> {}
