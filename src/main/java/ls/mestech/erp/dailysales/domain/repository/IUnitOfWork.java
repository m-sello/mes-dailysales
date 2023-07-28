package ls.mestech.erp.dailysales.domain.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface IUnitOfWork {
    IDailySalesRepository DailySalesRepository();
    ILanguageRepository LanguageRepository();
    ITenderTypeRepository TenderTypeRepository();
    IMobileMoneyRepository MobileMoneyRepository();
    IUserRepository UserRepository();
    IUserGroupRepository UserGroupRepository();
}
