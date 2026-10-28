package com.example.agentstudy.entity;
import com.example.agentstudy.service.OrderRepository;
import com.example.agentstudy.service.InMemoryOrderRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Order {
    private String orderId;
    private String product;
    private int quantity;

    //构造方法
    public Order(String orderId, String product, int quantity) {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("订单号不能为空");
        }
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("商品名不能为空");
        }
        if (quantity <= 0) {
            //抛出异常
            throw new IllegalArgumentException("商品数量必须大于0");
        }
        //throw	在代码中抛出一个异常
        //throws	在方法声明中说明可能向调用方抛出异常

        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
    }

    //方法--对象提供的行为
    public void printInfo(){
        System.out.println("Order ID: " + orderId);
        System.out.println("Product: " + product);
        System.out.println("Quantity: " + quantity);
    }

    public String getInfo() {
        return "Order ID: " + orderId + System.lineSeparator()
                + "Product: " + product + System.lineSeparator()
                + "Quantity: " + quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public static void main(String[] args) {
        Order product1 = new Order("P001", "ipone 18PROMAX", 1);
        Order product2 = new Order("P002", "ipone DUO", 2);
        Order product3 = new Order("P003", "HUAWEI MATE90PROMAX", 3);

        product1.printInfo();

        //List	有顺序，可以重复	ArrayList	订单列表、聊天消息
            List<Order> orders = new ArrayList<>();
            orders.add(new Order("A001", "耳机", 2));
            orders.add(new Order("A002", "键盘", 1));
            orders.add(new Order("A003", "电脑", 1));

            System.out.println(orders.size()); // 3
            orders.get(0).printInfo();         // 索引从 0 开始,获取第一个订单的信息

            //相当于py的for order in orders,遍历订单列表的信息
            for(Order order : orders){
                order.printInfo();
            }
        //Set	元素不重复	HashSet	去重后的用户编号
            Set<String> userIds = new HashSet<>();

            userIds.add("U001");
            userIds.add("U001");
            userIds.add("U002");

            System.out.println(userIds.size()); // 2

        //Map	保存键与值的对应关系，键唯一	HashMap	根据订单号查订单
            Map<String,Order> orderMap = new HashMap<>();

            Order order = new Order("A001", "耳机", 2);
            //对同一个键再次 put()，会替换原来的值
            orderMap.put(order.getOrderId(), order);

            //寻找订单号为A001的订单
            Order found = orderMap.get("A001");

            if(found != null){
                found.printInfo();
            }

        //泛型：明确“这里存放什么类型”
        // orders.add(new Order("A001", "耳机", 2)); // 类型正确，但再次添加会产生重复订单
        //orders.add("这不是订单");                // 编译错误

        //还可以定义自己的泛型类：
        /*
        public class Box<T> {
            private T value;
            public void set(T value) {this.value = value;}
            public T get() {return value;}
        }
        T 是类型占位符，使用时确定
        Box<String> textBox = new Box<>();
        textBox.set("你好");
        String text = textBox.get();

        Box<Integer> numberBox = new Box<>();
        注意，泛型参数不能直接使用 int 等基本类型，要使用包装类型
        numberBox.set(100);
        Integer number = numberBox.get();

        两者使用同一个 Box 类，但保存的类型不同
         */

        try {
            Order order1 = new Order("A001", "耳机", 0);
            order1.printInfo(); // 创建失败，这行不会执行
        } catch (IllegalArgumentException e) {
            System.out.println("创建订单失败：" + e.getMessage());
        }finally {
            System.out.println("操作完成");
        }

        //通过接口使用内存仓库：Map 由仓库内部管理
        //左边接口名，右边类名
        OrderRepository repository = new InMemoryOrderRepository();
        List<String> orderIds = new ArrayList<>();
        for(Order item : orders){
            repository.save(item);
            orderIds.add(item.getOrderId());
        }
        System.out.println("已保存的订单号：" + orderIds);

        Order savedOrder = repository.findById("A002");
        if (savedOrder != null) {
            savedOrder.printInfo();
        }
        if (repository.findById("NOT_FOUND") == null) {
            System.out.println("订单不存在");
        }

        Path path = Path.of("order.txt");

        //StringBuilder 用于逐步拼接字符串。这里仍然是覆盖写入，但每次写进去的是完整订单列表
        StringBuilder content = new StringBuilder();
        for(Order item : orders){
            content.append(item.getInfo())
                    .append(System.lineSeparator())
                    .append(System.lineSeparator());
        }

        //一次写入完整列表，避免在循环中反复覆盖；再次运行时更新整个文件
        try {
            Files.writeString(path, content.toString(), StandardCharsets.UTF_8);
            System.out.println("已保存到：" + path.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("保存订单失败：" + e.getMessage());
        }



    }


}

