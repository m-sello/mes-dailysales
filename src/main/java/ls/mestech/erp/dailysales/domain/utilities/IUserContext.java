package ls.mestech.erp.dailysales.domain.utilities;

import javax.management.relation.Role;

public interface IUserContext {
    IUser CurrentUser = null;

    Boolean IsInRole(Role role);
}
