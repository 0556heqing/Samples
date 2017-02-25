package com.heqing.samplesBase;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import com.heqing.samplesBase.bean.xml.XmlComputer;
import com.heqing.samplesBase.bean.xml.XmlUser;
import com.heqing.samplesBase.utils.XMLUtil;

public class TestXML {
	
//	@Test 
	public void testCreateXML() throws Exception {
		// 第一种方式：创建文档，并创建根元素
        // 创建文档:使用了一个Helper类
        Document document = DocumentHelper.createDocument();
        // 创建根节点并添加进文档
        Element root = DocumentHelper.createElement("student");
        document.setRootElement(root);

        // 第二种方式:创建文档并设置文档的根元素节点
        Element root2 = DocumentHelper.createElement("student");
        Document document2 = DocumentHelper.createDocument(root2);
        // 添加属性
        root2.addAttribute("id", "10001");
        // 添加子节点:add之后就返回这个元素
        Element nameElement = root2.addElement("name");
        Element ageElement = root2.addElement("age");
        Element sexElement = root2.addElement("sex");
        Element scoreElement = root2.addElement("score");
        Element englishElement = scoreElement.addElement("english");
        Element mathElement = scoreElement.addElement("math");
        Element chineseElement = scoreElement.addElement("chinese");

        nameElement.setText("heqing");
        ageElement.setText("27");
        sexElement.setText("男");
        englishElement.setText("70");
        mathElement.setText("80");
        chineseElement.setText("90");
        // 输出
        // 输出到控制台
        XMLWriter xmlWriter = new XMLWriter();
        xmlWriter.write(document);

        XMLUtil.writer(document2, "D:/test/student.xml");
	}
	
//	@Test  
    public void testParsingXML() throws Exception {  
    	//1.读取XML文件,获得document对象              
    	SAXReader reader = new SAXReader();             
		Document document = reader.read(new File("D:/test/student.xml"));  
        //获取根节点元素对象  
        Element node = document.getRootElement();  
        
        //添加CDATA区域  
	    node.addCDATA("该学生是一个好学生.");  
        //获取根节点
        Element sex = node.element("sex");  
        //删除元素节点 ,返回true代码删除成功，否则失败
        boolean flag = node.remove(sex);  
        // 获取element的id属性节点对象  
        Attribute attr = node.attribute("id");  
        attr.setText("10002");
        Element element = node.element("score"); 
        //添加新的属性  
        element.addAttribute("name", "分数");
        //添加支点
        Element newElement = element.addElement("name");  
        newElement.setText("贺庆");  
	    //删除支点
	    element.remove(newElement);  
        // 写入到一个新的文件中  
        XMLUtil.writer(document, "D:/test/student.xml");  
        
//		//2.解析XML形式的文本,得到document对象.  
//        String text = "<student><name>heqing</name><book>java</book><book>html5</book><book>android</book></student>";             
//        Document document = DocumentHelper.parseText(text);  
//        Element root = document.getRootElement();  
//        List nodes = root.elements("book");
//        for (Iterator it = nodes.iterator(); it.hasNext();) {
//        	Element elm = (Element) it.next();
//  	        System.out.println(elm.getText());
//  	    }  
	}
    
//    @Test
    public void testBeanToXmlString() {
    	// 创建需要转换的对象  
    	XmlUser user = new XmlUser(1, "heqing", "123456", new Date(), 1000.0);  
    	List<XmlComputer> list = new ArrayList<XmlComputer>();  
        list.add(new XmlComputer("xxxMMeedd", "asus", new Date(), 4455.5));  
        list.add(new XmlComputer("lenvoXx", "lenvo", new Date(), 4999));  
        user.setComputers(list);  
    	
        System.out.println("---将对象转换成string类型的xml ---");  
        // 将对象转换成string类型的xml  
        String str = XMLUtil.convertToXml(user);  
        // 输出  
        System.out.println(str);  
        System.out.println("===============================");  
        System.out.println("---将String类型的xml转换成对象 ");  
        XmlUser userTest = (XmlUser) XMLUtil.convertXmlStrToObject(XmlUser.class, str);  
        System.out.println(userTest);  
    }
    
//    @Test
    public void testBeanToXmlFile() {
    	// 创建需要转换的对象  
    	XmlUser user = new XmlUser(1, "heqing", "123456", new Date(), 1000.0);  
    	List<XmlComputer> list = new ArrayList<XmlComputer>();  
        list.add(new XmlComputer("xxxMMeedd", "asus", new Date(), 4455.5));  
        list.add(new XmlComputer("lenvoXx", "lenvo", new Date(), 4999));  
        user.setComputers(list);
        
        String path = "D:/test/user.xml";  
        System.out.println("---将对象转换成File类型的xml");  
        XMLUtil.convertToXml(user, path);  
        System.out.println("===============================");   
        System.out.println("---将File类型的xml转换成对象 ---");  
        XmlUser user2 = (XmlUser) XMLUtil.convertXmlFileToObject(XmlUser.class, path);  
        System.out.println(user2);  
    }

}
