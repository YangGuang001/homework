package demo.ws.soap_jaxws;

import javax.jws.WebService;

/**
 * Created by yz on 2017/7/22.
 */
@WebService(serviceName = "HelloService",
portName = "HelloServicePort",
endpointInterface = "demo.ws.soap_jaxws.HelloService")
public class HelloServiceImpl implements HelloService {
    public String say(String name) {
        return "hello " + name;
    }
}
