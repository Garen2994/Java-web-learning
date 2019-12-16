package ee.demo.Proxy;

public class Lenovo implements SaleComputer {
    @Override
    public String sale(double money) {
        System.out.println("花了" + money + "元买了一台电脑");
        return "Lenovo PC";
    }

    @Override
    public void show() {
        System.out.println("show....");
    }
}

