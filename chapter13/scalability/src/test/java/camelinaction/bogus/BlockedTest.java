package camelinaction.bogus;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Ignore;
import org.junit.Test;

public class BlockedTest extends CamelTestSupport {

    @Override
    protected boolean useJmx() {
        return true;
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext context = super.createCamelContext();
        context.addComponent("bogus", new BogusComponent());
        return context;
    }

    @Test
    @Ignore
    public void testNotBlocked() throws Exception {
        String out = template.requestBody("direct:start", "Camel in Action", String.class);
        assertEquals("Camel in Action;516", out);
    }

    @Test
    public void testBlocked() throws Exception {
        log.info("This operation will cause Camel to block processing the message (2 minute).");
        log.info("You need to use a JMX console such as jconsole or hawtio to manually unblock this");

        try {
            String reply = template.requestBody("direct:start", "ActiveMQ in Action", String.class);

            log.info("The request was not manually unblocked, and allowed to complete, but that took 2 minutes.");
            log.info("The reply message is {}", reply);

        } catch (Exception e) {
            // should happen if unblocked
            log.info("The request was manually unblocked. And the message failed with exception due " + e.getMessage());
        }
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                    .log("Calling bogus with ${threadName}")
                    .to("bogus:foo").id("to-bogus")
                    .log("Response from bogus ${body} from ${threadName}");
            }
        };
    }
}
