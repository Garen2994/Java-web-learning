package ee.spring.factoryDemo.service.Impl;

import ee.spring.factoryDemo.dao.AccountDao;
import ee.spring.factoryDemo.factory.BeanFactory;
import ee.spring.factoryDemo.service.AccountService;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");

    public void saveAccount() {
        int i = 1;
        accountDao.saveAccount();
        System.out.println(i);
        i++;
    }
}
