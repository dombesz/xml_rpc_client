/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rpcclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.Vector;
import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

/**
 *
 * @author dombesz
 */
public class rpcClient  {
 
    public static XmlRpcClient client=null;

    public static String host;
    public static Integer port;
    public static String schemaPath;
    public static Boolean isValidating;
    public static void initRpcClient(String host,int port) throws MalformedURLException{
    
    client=new XmlRpcClient("http://"+host+":"+port);
    
    }
    public static String makeQuery(String query) throws XmlRpcException, IOException{
    
    Vector params = new Vector(  );
    String ip = InetAddress.getLocalHost().toString();
    params.addElement(ip);
    params.addElement(query);
    
   
    Object result =client.execute("request.dbQuery",params);
    return result.toString();
    }
    public static String getTableList() throws XmlRpcException, IOException{
              
            Vector params = new Vector(  );
            String ip = InetAddress.getLocalHost().toString();
            params.addElement(ip);
            
            Object result =client.execute("request.getTableNames",params);
    return result.toString();
    }
    public static String makeUpdate(String query) throws XmlRpcException, IOException{
            
            Vector params=new Vector();
            String ip = InetAddress.getLocalHost().toString();
            params.addElement(ip);
            params.addElement(query);
   
    Object result =client.execute("request.dbExec",params);
    return result.toString();
            
 
    }
    public static void saveSettings(){
    try {  
            Properties properties = new Properties();
            properties.setProperty("port", port.toString());
            properties.setProperty("host", host);
            properties.setProperty("XSdSchema",schemaPath );
            properties.setProperty("isValidating",isValidating.toString());
            File fs = new File("rpcclient-configuration.xml");
            fs.createNewFile();
            FileOutputStream fos = new FileOutputStream(fs);
            properties.storeToXML(fos, "RPC Client Configuration", "UTF-8");
        } catch (IOException ex) {
            System.out.print("File save exception");
        }
    }
    public static void loadSettings(){
    try {

            Properties properties = new Properties();
            FileInputStream fis = null;
           
            fis = new FileInputStream("rpcclient-configuration.xml");
            try{
            properties.loadFromXML(fis);
            port = Integer.parseInt(properties.getProperty("port"));
            host = properties.getProperty("host");
            schemaPath=properties.getProperty("XSdSchema");
            isValidating=Boolean.parseBoolean(properties.getProperty("isValidating"));
            
            fis.close();
            }catch(IOException e){
            host="localhost";
            port = 8899;
            schemaPath="tabla.xsd";
            isValidating=false;
            }

        } catch (FileNotFoundException ex) {
            host="localhost";
            port = 8899;
            schemaPath="tabla.xsd";
            isValidating=false;
        }
    
    }
    public static void disconnect(){
    client=null;
    }
}
