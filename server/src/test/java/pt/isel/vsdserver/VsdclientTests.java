package pt.isel.vsdserver;

import net.nuagenetworks.bambou.RestException;
import net.nuagenetworks.vspk.v5_0.*;
import net.nuagenetworks.vspk.v5_0.fetchers.EnterprisesFetcher;
import net.nuagenetworks.vspk.v5_0.fetchers.GatewaysFetcher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "/test.properties")
public class VsdclientTests {

    @Value("${test.vsdaddress}")
    private String myip;

    @Value("${test.vsdusername}")
    private String username;

    @Value("${test.password}")
    private String password;

    @Value("${test.organization}")
    private String organization;


    @Test
    public void makeRequestToMe() throws RestException {
        VSDSession session = new VSDSession(username, password, organization, myip);
        Assert.assertNotNull(session);
        session.start();

        Me me = session.getMe();
        Assert.assertNotNull(me);

        String enterprise = me.getEnterpriseName();
        Assert.assertEquals("CSP", enterprise);
    }

    @Test
    public void getStuff() throws RestException {
        VSDSession session = new VSDSession(username, password, organization, myip);
        Assert.assertNotNull(session);
        session.start();

        Me me = session.getMe();
        Assert.assertNotNull(me);

        EnterprisesFetcher ef = me.getEnterprises();
        Assert.assertNotNull(ef);
        List<Enterprise> list = ef.get();
        Assert.assertFalse(list.isEmpty());

        GatewaysFetcher gf = me.getGateways();
        List<Gateway> gateways = gf.get();
        Assert.assertFalse(gateways.isEmpty());
        for (Gateway gw: gateways) {
            Assert.assertNotNull(gw);
            Boolean isConnected = gw.getGatewayConnected();
            System.out.println("Gateway " + gw.getName() + "(" + gw.getId() + "):");
            //System.out.println("\tConnected: " + isConnected);
            System.out.println("\tPorts:");
            List<Port> ports = gw.getPorts().get();
            for(Port port : ports){
                System.out.println("\t\t" + port.getName() + " (" + port.getPortType().name() + ") - " + port.getStatus().name());
            }

        }
    }

}
