package ee.spring.factoryDemo.view;

import ee.spring.factoryDemo.factory.BeanFactory;
import ee.spring.factoryDemo.service.AccountService;

public class Client {
    public static void main(String[] args) {
        for(int i = 0 ; i<3 ; i++){
            AccountService service = (AccountService)BeanFactory.getBean("accountService");
            System.out.println(service);
            service.saveAccount();
        }
    }
}
