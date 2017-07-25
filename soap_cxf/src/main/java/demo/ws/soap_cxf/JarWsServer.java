package demo.ws.soap_cxf;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * Created by yz on 2017/7/22.
 */
public class JarWsServer {
    public static void main(String[] args) {
        JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
        factoryBean.setAddress("http://localhost:8080/ws/soap/hello");
        factoryBean.setServiceClass(HelloService.class);
        factoryBean.setServiceBean(new HelloServiceImpl());
        factoryBean.create();
        System.out.println("soap ws is published");
    }
}
