package com.homie.jlearn.modules.user.port;

import com.homie.jlearn.common.StringFilter;
import com.homie.jlearn.constants.filter.RoleTypeFilter;
import com.homie.jlearn.constants.filter.UserStatusFilter;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCriteria {

    private StringFilter id;

    private StringFilter username;

    private StringFilter firstName;

    private StringFilter lastName;

    private StringFilter email;

    private UserStatusFilter status;

    private RoleTypeFilter role;

    private StringFilter activationKey;
}
