package com.imall.commons.tools;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ygw on 2017/4/19.
 */
public class IdeaArtifactsTools {

    private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    //component/artifact/root/element
    private static void update(File artifactsFile){
        Element component=null;
        try {
            DocumentBuilder db=factory.newDocumentBuilder();
            Document xmldoc=db.parse(artifactsFile);
            component=xmldoc.getDocumentElement(); //component
            component.getElementsByTagName("element");
            //添加
            Element artifact = (Element)getChildByNodeName(component, "artifact", null, null);
            Node outputPath = getChildByNodeName(artifact, "output-path", null, null);
            Node root = getChildByNodeName(artifact, "root", null, null);
            Node webInf = getChildByNodeName(root, "element", null, null);
            Node classes = getChildByNodeName(webInf, "element", "name", "classes");
            Node lib = getChildByNodeName(webInf, "element", "name", "lib");

            if(artifact==null || outputPath==null || root==null || webInf==null || classes==null || lib==null){
                return;
            }

            //
            artifact.setAttribute("build-on-make", "true");
            Node nodeName = getAttributeNode(artifact,"name");
            artifact.setAttribute("name", nodeName.getNodeValue() + " new");

            String outputPathTextContent = outputPath.getTextContent();
            String warPath = outputPathTextContent.substring(0, outputPathTextContent.indexOf("target")-1) + "/src/main/webapp";
            outputPath.setTextContent(warPath);

            //
            NodeList nodeList = lib.getChildNodes();
            List<Node> newOutputList = new ArrayList<Node>();
            for(int i=nodeList.getLength()-1; i>=0; i--){
                Node jar = nodeList.item(i);
                if(jar.hasChildNodes()){
                    newOutputList.add(getChildByNodeName(jar, "element", null, null));
                    lib.removeChild(jar);
                }
            }

            for(int i=newOutputList.size()-1; i>=0; i--){
                //倒序回来
                classes.appendChild(newOutputList.get(i));
            }

            //保存
            TransformerFactory transFactory=TransformerFactory.newInstance();
            try {
                Transformer transformer = transFactory.newTransformer();
                transformer.setOutputProperty("indent", "yes");
                DOMSource source=new DOMSource();
                source.setNode(xmldoc);
                StreamResult result=new StreamResult();
                result.setOutputStream(new FileOutputStream(artifactsFile.getPath().replace(".xml", "") + ".new.xml"));
                transformer.transform(source, result);
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static Node getChildByNodeName(Node node, String nodeName, String attrKey, String attrValue){
        if(node.hasChildNodes()){
            NodeList nodeList = node.getChildNodes();
            for(int i=0; i<nodeList.getLength(); i++){
                Node child = nodeList.item(i);
                if(child.getNodeName().equals(nodeName)){
                    if(attrKey==null || attrValue==null){
                        return child;
                    }
                    //
                    Node nameNode = getAttributeNode(child, attrKey);
                    if(nameNode!=null && nameNode.getNodeValue().equals(attrValue)){
                        return child;
                    }
                }
            }
        }
        return null;
    }

    private static Node getAttributeNode(Node node, String attributeName){
        NamedNodeMap nodeMap = node.getAttributes();
        for(int j=0; j<nodeMap.getLength(); j++){
            Node nameNode = nodeMap.item(j);
            if(nameNode.getNodeName().equals(attributeName)){
                return nameNode;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        factory.setIgnoringElementContentWhitespace(true);
        File artifactsDir = new File("D:\\imall-cloudc-1.0-web\\.idea\\artifacts");
        File[] artifactsFiles = artifactsDir.listFiles();
        if(artifactsFiles!=null){
            for(File artifactsFile: artifactsFiles){
                if(artifactsFile.getName().endsWith("_exploded.xml")){
                    System.out.println("= = =" + artifactsFile.getPath() + "= = =");
                    update(artifactsFile);
                }
            }
        }
    }
}
