package demo.ws.soap_cxf;

import javax.jws.WebService;

/**
 * Created by yz on 2017/7/22.
 */
@WebService
public class HelloServiceImpl implements HelloService {
    public String say(String name) {
        return "hello " + name;
    }
}
