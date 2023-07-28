package ls.mestech.erp.dailysales.persistence;

import ls.mestech.erp.dailysales.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UnitOfWork implements IUnitOfWork {
    @Autowired
    private final DailySalesRepository DailySaleRepository;
    @Autowired
    private final LanguageRepository LanguageRepository;
    @Autowired
    private final TenderTypeRepository TenderTypeRepository;
    @Autowired
    private final UserRepository UserRepository;
    @Autowired
    private final UserGroupRepository UserGroupRepository;
    @Autowired
    private final MobileMoneyRepository MobileMoneyRepository;

    public UnitOfWork(DailySalesRepository dailySaleRepository, ls.mestech.erp.dailysales.persistence.LanguageRepository languageRepository, ls.mestech.erp.dailysales.persistence.TenderTypeRepository tenderTypeRepository, ls.mestech.erp.dailysales.persistence.UserRepository userRepository, ls.mestech.erp.dailysales.persistence.UserGroupRepository userGroupRepository, ls.mestech.erp.dailysales.persistence.MobileMoneyRepository mobileMoneyRepository) {
        DailySaleRepository = dailySaleRepository;
        LanguageRepository = languageRepository;
        TenderTypeRepository = tenderTypeRepository;
        UserRepository = userRepository;
        UserGroupRepository = userGroupRepository;
        MobileMoneyRepository = mobileMoneyRepository;
    }
    @Override
    public IDailySalesRepository DailySalesRepository() {

        return DailySaleRepository;
    }
    @Override
    public ILanguageRepository LanguageRepository() {

        return LanguageRepository;
    }
    @Override
    public ITenderTypeRepository TenderTypeRepository() {

        return TenderTypeRepository;
    }
    @Override
    public IMobileMoneyRepository MobileMoneyRepository() {
        return MobileMoneyRepository;
    }
    @Override
    public IUserRepository UserRepository() {

        return UserRepository;
    }
    @Override
    public IUserGroupRepository UserGroupRepository() {

        return UserGroupRepository;
    }
}
