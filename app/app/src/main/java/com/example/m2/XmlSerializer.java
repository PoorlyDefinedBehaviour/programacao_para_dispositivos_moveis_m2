package com.example.m2;

import android.graphics.BitmapFactory;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlSerializer {
    public static List<MenuItem> toMenuItems(String xml) {
        List<MenuItem> menu = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(new InputSource(new StringReader(xml)));

            NodeList nodes = doc.getElementsByTagName("item");

            for (int temp = 0; temp < nodes.getLength(); temp++) {
                Node node = nodes.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    menu.add(new MenuItem(
                            Integer.parseInt(element
                                    .getElementsByTagName("id")
                                    .item(0)
                                    .getTextContent()),
                            element
                                    .getElementsByTagName("name")
                                    .item(0)
                                    .getTextContent(),
                            element
                                    .getElementsByTagName("description")
                                    .item(0)
                                    .getTextContent(),
                            element
                                    .getElementsByTagName("price")
                                    .item(0)
                                    .getTextContent(),
                            element
                                    .getElementsByTagName("has_gluten")
                                    .item(0)
                                    .getTextContent() == "1",
                            Integer.parseInt(element
                                    .getElementsByTagName("calories")
                                    .item(0)
                                    .getTextContent()),
                            BitmapFactory.decodeStream((InputStream) new URL(element
                                    .getElementsByTagName("image")
                                    .item(0)
                                    .getTextContent()).getContent()))
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return menu;
    }
}
