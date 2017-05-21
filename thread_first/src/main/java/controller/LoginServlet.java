package controller;

/**
 * Created by yz on 2017/5/16.
 */
public class LoginServlet {
  private  String nameRef;
  private  String passwdRef;
  public void doPost(String name, String passwd){
      try {
          nameRef = name;
          if (nameRef.equals("a")){
              Thread.sleep(5000);
          }
          passwdRef = passwd;
          System.out.println("usernameRef:" + nameRef + " passwdRef:" + passwdRef);
      }catch (InterruptedException e){
          e.printStackTrace();
      }
  }
}
