/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rpcclient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.xmlrpc.XmlRpcException;

/**
 *
 * @author dombesz
 */
public class main {
 /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws XmlRpcException, IOException {
        
            rpcClient.loadSettings();
            
            
            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                    

                        mainFrame frame = new mainFrame();
                        frame.setLocation(400, 200);
                        frame.setVisible(true);
                        
                    
                }
            });
        
    }
}
