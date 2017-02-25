package com.heqing.samplesBase.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class XMLUtil {

	 /** 
     * 把document对象写入新的文件 
     * @param document 
     * @throws Exception 
     */  
    public static void writer(Document document, String filePath) throws Exception {  
        // 紧凑的格式  
        // OutputFormat format = OutputFormat.createCompactFormat();  
        // 排版缩进的格式  
        OutputFormat format = OutputFormat.createPrettyPrint();  
        // 设置编码  
        format.setEncoding("UTF-8");  
        // 创建XMLWriter对象,指定了写出文件及编码格式  
        // XMLWriter writer = new XMLWriter(new FileWriter(new  
        // File("src//a.xml")),format);  
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
                new FileOutputStream(new File(filePath)), "UTF-8"), format);  
        // 写入  
        writer.write(document);  
        // 立即写入  
        writer.flush();  
        // 关闭操作  
        writer.close();  
    }  
    
    /** 
     * 遍历当前节点元素下面的所有(元素的)子节点 
     * @param node 
     */  
    public static void listNodes(Element node) {  
        System.out.println("当前节点的名称：：" + node.getName());  
        // 获取当前节点的所有属性节点  
        List<Attribute> list = node.attributes();  
        // 遍历属性节点  
        for (Attribute attr : list) {  
            System.out.println(attr.getText() + "-----" + attr.getName() + "---" + attr.getValue());  
        }  
        if (!(node.getTextTrim().equals(""))) {  
            System.out.println("文本内容：" + node.getText());  
        } 
        // 当前节点下面子节点迭代器  
        Iterator<Element> it = node.elementIterator();  
        // 遍历  
        while (it.hasNext()) {  
            // 获取某个子节点对象  
            Element e = it.next();  
            // 对子节点进行遍历  
            listNodes(e);  
        }  
    }  
    
    /** 
     * 将对象直接转换成String类型的 XML输出 
     * @param obj 
     * @return 
     */  
    public static String convertToXml(Object obj) {  
        // 创建输出流  
        StringWriter sw = new StringWriter();  
        try {  
            // 利用jdk中自带的转换类实现  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
  
            Marshaller marshaller = context.createMarshaller();  
            // 格式化xml输出的格式  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);  
            // 将对象转换成输出流形式的xml  
            marshaller.marshal(obj, sw);  
        } catch (JAXBException e) {  
            e.printStackTrace();  
        }  
        return sw.toString();  
    }  
    
    /** 
     * 将对象根据路径转换成xml文件 
     * @param obj 
     * @param path 
     * @return 
     */  
    public static void convertToXml(Object obj, String path) {  
        try {  
            // 利用jdk中自带的转换类实现  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
  
            Marshaller marshaller = context.createMarshaller();  
            // 格式化xml输出的格式  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);  
            // 将对象转换成输出流形式的xml  
            // 创建输出流  
            FileWriter fw = null;  
            try {  
                fw = new FileWriter(path);  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            marshaller.marshal(obj, fw);  
        } catch (JAXBException e) {  
            e.printStackTrace();  
        }  
    }  
  
    @SuppressWarnings("unchecked")  
    /** 
     * 将String类型的xml转换成对象 
     */  
    public static Object convertXmlStrToObject(Class clazz, String xmlStr) {  
        Object xmlObject = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(clazz);  
            // 进行将Xml转成对象的核心接口  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            StringReader sr = new StringReader(xmlStr);  
            xmlObject = unmarshaller.unmarshal(sr);  
        } catch (JAXBException e) {  
            e.printStackTrace();  
        }  
        return xmlObject;  
    }  
  
    @SuppressWarnings("unchecked")  
    /** 
     * 将file类型的xml转换成对象 
     */  
    public static Object convertXmlFileToObject(Class clazz, String xmlPath) {  
        Object xmlObject = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(clazz);  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            FileReader fr = null;  
            try {  
                fr = new FileReader(xmlPath);  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            }  
            xmlObject = unmarshaller.unmarshal(fr);  
        } catch (JAXBException e) {  
            e.printStackTrace();  
        }  
        return xmlObject;  
    } 
    
}
