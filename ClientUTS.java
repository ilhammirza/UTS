/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientuts;

import java.net.Socket;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ilham
 */
public class ClientUTS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
         
        try {
            
            // TODO code application logic here
            Socket socket=new Socket("10.151.34.155",6666);
            InputStream is = socket.getInputStream();
            OutputStream os=socket.getOutputStream();
            os.write("Username:5113100149\n".getBytes());
            String mod="mod";
            Pattern pattern = Pattern.compile("[0-9]+|[-+x]+|"+mod);
            int result;
            byte[] buf;
            int len = 0;
            String s,o = null,results = null;
            while(true) {
                buf = new byte[200];
                try {
                    len = is.read(buf);
                    ///String s;
                    s = new String(buf).trim();
                    Matcher matcher=pattern.matcher(s);
                   // System.out.println(new String(buf));
                    int n = 3;
                    int a = 0,b;
                    String op = null;
                    if(Character.isDigit(s.charAt(0))){
                    while(matcher.find()){
                    //if(s==matcher.group(0)){
                        if(s!="200"||s!="201"||s!="2015"){
                       
                           if(n==3&&!"-".equals(matcher.group(0))){
                                   
                               a=Integer.parseInt(matcher.group(0));
                               System.out.println(a);
                               n=n-1;
                           }
                           else if(n==2){
                           op=matcher.group(0);
                           n=n-1;
                           }
                           else if(n==1){
                               b=Integer.parseInt(matcher.group(0));
                               if(op=="x"){
                                   result=a*b;
                                   o=Integer.toString(result);
                               }else if(op=="mod"){
                                   result=a%b;
                                   o=Integer.toString(result);
                               }else if(op=="+"){
                                   result=a+b;
                                   o=Integer.toString(result);
                               }else if(op=="-"){
                                   result=a-b;
                                   o=Integer.toString(result);
                               }
                               n=n-1;
                           }
                       else
                               results="Result:"+o+"\n";
                               
                               //os.write(results.getBytes());
                               n=3;
                    }
                        //System.out.println(results);
                    }
                    }
                   // }
                } catch (IOException ex) {
                    Logger.getLogger(ClientUTS.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println(new String(buf).trim());
                if(len == -1) {
                    break;
                }
                    
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientUTS.class.getName()).log(Level.SEVERE, null, ex);
        }
                
            
            }
            
        
        }
        
    
    

