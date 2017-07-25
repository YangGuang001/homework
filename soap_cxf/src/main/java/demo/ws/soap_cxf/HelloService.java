package demo.ws.soap_cxf;

import javax.jws.WebService;

/**
 * Created by yz on 2017/7/22.
 */
@WebService
public interface HelloService {
    String say(String name);
}
