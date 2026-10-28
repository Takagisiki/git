package com.example.agentstudy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDemo {
    public static void main(String[] args) {
        //Path.of(...)	调用 Path 的静态方法 of，获得路径对象
        //order.txt：在当前工作目录下
        //..表示上一级目录，所以这里指的是：程序工作目录的上一级目录中的 order.txt
        Path path = Path.of("order.txt");

        try{
            //写入文本；文件不存在时创建，存在时覆盖
            Files.writeString(
                    path,
                    "订单号:A001,商品:耳机,数量:2",
                    StandardCharsets.UTF_8
            );
            // 读取文本
            String content = Files.readString(
                    path,
                    StandardCharsets.UTF_8
            );
            System.out.println(content);
            System.out.println("文件位置：" + path.toAbsolutePath());
        }catch(IOException e){
            System.out.println("文件操作失败！");
        }finally {
            System.out.println("对该文件操作完成！");
        }

        try (var reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String firstLine = reader.readLine();
            System.out.println(firstLine);
        } catch (IOException e) {
            System.err.println("读取第一行失败：" + e.getMessage());
        }
    }



}
