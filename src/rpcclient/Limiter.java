/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rpcclient;

/**
 *
 * @author dombesz
 */
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

class Limiter extends PlainDocument
    {
        int maxChar = -1;
        public Limiter(int len){maxChar = len;}
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
        {
            if (str != null && maxChar > 0 && this.getLength() + str.length() > maxChar)
            {
                java.awt.Toolkit.getDefaultToolkit().beep();
                return;
            }
            super.insertString(offs, str, a);
        }
    }
