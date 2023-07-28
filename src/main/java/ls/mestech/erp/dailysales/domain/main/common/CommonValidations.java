package ls.mestech.erp.dailysales.domain.main.common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class CommonValidations <T>{

    public List<T> listDuplicateUsingFilterAndSetAdd(List<T> list) {
        Set<T> elements = new HashSet<T>();
        return list.stream()
                .filter(n -> !elements.add(n))
                .collect(Collectors.toList());
    }
}
