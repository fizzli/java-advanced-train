package com.fizzli.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author fizzli
 * @version 1.0.0
 *
 * 自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，
 * 此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。
 */
public class OwnClassLoader extends ClassLoader{


    /**
     * 重写findClass方法，用于自己的逻辑
     * @param name 文件名
     * @return 类
     */
    @Override
    public Class<?> findClass(String name) {
        //获取带扩展名的文件名
        String fileName = getFileName(name);
        //获取文件转成输入流
        InputStream input = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (input == null){
            return null;
        }
        //ClassPathResource pathResource = new ClassPathResource(fileName);
        //InputStream is = pathResource.getInputStream();

        Class<?> clazz = null;
        try{
            int available = input.available();
            byte[] data = new byte[available];
            input.read(data);
            //获取解密后字节流
            byte[] decryptData =decrypt(data);
            //将数组转化为类
            clazz  = defineClass(name,decryptData,0,decryptData.length);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return clazz;
    }

    /**
     * 解密
     * @param data 密文数组
     * @return 解密后数组
     */
    private byte[] decrypt(byte[] data) {
        byte[] newData = new byte[data.length];

        for (int i=0;i<data.length;i++){
            newData[i] = (byte) (255 - data[i]);
        }

        return newData;
    }

    /**
     * 获取class文件名
     * @param name 不带扩展名的文件名
     * @return 带扩展名的文件名
     */
    private String getFileName(String name) {

        int index = name.lastIndexOf(".");
        if (index == -1){
            return name + ".xlass";
        }else{
            return name.substring(index + 1) + ".xlass";
        }
    }
}
