package ls.mestech.erp.dailysales.domain.repository;

import ls.mestech.erp.dailysales.domain.model.DailySales;
import ls.mestech.erp.dailysales.dto.DailySale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;

public interface IDailySaleRepository extends JpaRepository <DailySales,Long> {
    DailySale dailySalesByCapturedDt(Instant capturedDt);
}
