package com.example.agentstudy.service;

public class MockPaymentService implements PaymentService {

    /*
    *   implements PaymentService：普通具体类必须实现接口要求的抽象方法，编译器会检查是否遗漏。
        @Override：检查下面这个方法是否确实在实现接口方法或重写父类方法，防止方法名、参数类型等写错
    *
    * */


    @Override
    public void pay(String OrderID){
        System.out.println("购买商品的ID为" + OrderID);
    }

    public static void main(String[] args) {
        PaymentService paymentService = new MockPaymentService();
        paymentService.pay("A001");
    }
}
