package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.TenderType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITenderTypeRepository extends JpaRepository <TenderType,Long> {
}
