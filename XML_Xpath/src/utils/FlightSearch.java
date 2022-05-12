package utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FlightSearch {
    private static Document doc;
    private static XPath xpath;

    public static void main(String[] args) throws Exception {
        init();
//        searchByLeave();
        searchByTime();
        //System.out.println(doc.getDocumentElement().getChildNodes().getLength());
//        NodeList nodeList = doc.getDocumentElement().getChildNodes();
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
//                System.out.print(nodeList.item(i).getNodeName() + " ");
//            }
//        }
    }

    // 初始化Document、XPath对象
    public static void init() throws Exception {
        // 创建Document对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(new FileInputStream(new File("FlightInfo.xml")));

        // 创建XPath对象
        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
    }

//    到港时间早于10:00的所有航班
    public static String searchByTime() throws Exception {
        init();
        String result = "";
        NodeList nodeList = (NodeList) xpath.evaluate("//Airline", doc, XPathConstants.NODESET);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");

        Date time = simpleDateFormat.parse("10:00");
        Date arriveTime = null;

        for (int i = 0 ; i < nodeList.getLength() ; i++) {

            Element element = (Element) nodeList.item(i);
            String date = element.getElementsByTagName("ArriveTime").item(0).getFirstChild().getNodeValue();

            arriveTime = simpleDateFormat.parse(date);
            if (arriveTime.before(time)) {
                result += (nodeList.item(i).getTextContent());
            }

        }
        return result;
    }

//    从北京机场出发的所有航班
    public static String searchByLeave(String startP) throws Exception {
        init();
        String result = "";
        NodeList nodeList = (NodeList) xpath.evaluate("//Airline[LeaveAirport = startP]", doc, XPathConstants.NODESET);
        for (int i = 0 ; i < nodeList.getLength() ; i++) {
            result += (nodeList.item(i).getTextContent());
        }
        return result;
    }

    // 获取根元素
    // 表达式可以更换为/*,/rss
    public static void getRootEle() throws XPathExpressionException {
        Node node = (Node) xpath.evaluate("/Flights", doc, XPathConstants.NODE);
        System.out.println(node.getNodeName() + "--------"
                + node.getNodeValue());
    }

    // 获取子元素并打印
    public static void getChildEles() throws XPathExpressionException {
        NodeList nodeList = (NodeList) xpath.evaluate("/Flights/Airline/*", doc,
                XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + " ");
        }
        System.out.println();
    }

    // 获取部分元素
    // 只获取元素名称为title的元素
    public static void getPartEles() throws XPathExpressionException {
        NodeList nodeList = (NodeList) xpath.evaluate("//*[name() = 'Company']",
                doc, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + "-->"
                    + nodeList.item(i).getTextContent());
        }
        System.out.println();
    }

    // 获取包含子节点的元素
    public static void haveChildsEles() throws XPathExpressionException {
        NodeList nodeList = (NodeList) xpath.evaluate("//*[*]", doc,
                XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + " ");
        }
        System.out.println();
    }

    // 获取指定层级的元素
    public static void getLevelEles() throws XPathExpressionException {
        NodeList nodeList = (NodeList) xpath.evaluate("/*/*/*/*", doc,
                XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + "-->"
                    + nodeList.item(i).getTextContent() + " ");
        }
        System.out.println("-----------------------------");
    }

    // 获取指定属性的元素
    // 获取所有大于指定价格的书箱
    public static void getAttrEles() throws XPathExpressionException {
        System.out.println("获取所有大于指定价格的书箱");
        NodeList nodeList = (NodeList) xpath.evaluate("//bookstore/book[price>35.00]/title", doc,
                XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + "-->"
                    + nodeList.item(i).getTextContent() + " ");
        }
        System.out.println();
    }
}
