import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;

public class XmlLoader
{
    // 数据库信息
    String USER = "";
    String PASS = "";
    String IPADDR = "";
    String DB = "";
    String DB_TABLE = "";

    public XmlLoader()
    {
        try
        {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            FileInputStream xmlInputStream = new FileInputStream("conf/main.conf");
            DocumentBuilder builder = null;
            builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(xmlInputStream);
            IPADDR = document.getChildNodes().item(0).getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
            DB = document.getChildNodes().item(0).getChildNodes().item(3).getChildNodes().item(0).getNodeValue();
            DB_TABLE = document.getChildNodes().item(0).getChildNodes().item(5).getChildNodes().item(0).getNodeValue();
            USER = document.getChildNodes().item(0).getChildNodes().item(7).getChildNodes().item(0).getNodeValue();
            PASS = document.getChildNodes().item(0).getChildNodes().item(9).getChildNodes().item(0).getNodeValue();
        } catch (ParserConfigurationException e) {
            //神奇的海螺：“什么都不做”
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }
}
