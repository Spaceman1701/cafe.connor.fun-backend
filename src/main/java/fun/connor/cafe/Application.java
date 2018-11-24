package fun.connor.cafe;

import com.google.inject.Guice;
import com.google.inject.Injector;
import fun.connor.cafe.domain.ProductionModule;
import fun.connor.lighter.Lighter;
import fun.connor.lighter.autoconfig.AutoConfigFactory;
import fun.connor.lighter.handler.Request;
import fun.connor.lighter.http.HttpHeaders;
import fun.connor.lighter.marshal.DelegatingAdaptorFactory;
import fun.connor.lighter.marshal.gson.GsonTypeAdapterFactory;
import fun.connor.lighter.marshal.java.JavaTypesAdaptorFactory;
import fun.connor.lighter.response.HeaderResponse;
import fun.connor.lighter.response.Response;
import fun.connor.lighter.undertow.LighterUndertow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        Application a = new Application();
        a.run();
    }

    private void run() {
        long start = System.currentTimeMillis();
        Injector injector = Guice.createInjector(new ProductionModule());
        long finishedGuice = System.currentTimeMillis();

        DelegatingAdaptorFactory adaptorFactory = DelegatingAdaptorFactory.builder()
                .addDelegateFactory(new JavaTypesAdaptorFactory())
                .addDelegateFactory(GsonTypeAdapterFactory.create())
                .build();

        Lighter lighter = LighterUndertow.builder()
                .adapterFactory(adaptorFactory)
                .injectionFactory(injector::getInstance)
                .addRouter(AutoConfigFactory.loadAutomaticConfiguration())
                .hostName("0.0.0.0")
                .port(8080)
                .addResponseTransformer((request, response) ->
                    response
                            .with(HeaderResponse.from(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*"))
                            .with(HeaderResponse.from(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, HttpHeaders.AUTHORIZATION))
                )
                .build();

        lighter.start();
        long end = System.currentTimeMillis();
        logStartupStats(start, finishedGuice, end);
    }

    private void logStartupStats(long start, long guiceTime, long end) {
        logger.info("Booted Container in a total of {} ms", end - start);
        logger.info("Of that time, {} ms was used to boot Guice", guiceTime - start);
    }
}
