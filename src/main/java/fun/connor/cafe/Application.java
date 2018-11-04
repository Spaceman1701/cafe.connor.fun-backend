package fun.connor.cafe;

import com.google.inject.Guice;
import com.google.inject.Injector;
import fun.connor.cafe.domain.ProductionModule;
import fun.connor.lighter.Lighter;
import fun.connor.lighter.autoconfig.AutomaticRouteConfigurationLoader;
import fun.connor.lighter.marshal.DelegatingAdaptorFactory;
import fun.connor.lighter.marshal.gson.GsonTypeAdapterFactory;
import fun.connor.lighter.marshal.java.JavaTypesAdaptorFactory;
import fun.connor.lighter.undertow.LighterUndertow;

public class Application {

    public static void main(String[] args) {
        Application a = new Application();
        a.run();
    }

    private void run() {
        Injector injector = Guice.createInjector(new ProductionModule());

        DelegatingAdaptorFactory adaptorFactory = DelegatingAdaptorFactory.builder()
                .addDelegateFactory(new JavaTypesAdaptorFactory())
                .addDelegateFactory(GsonTypeAdapterFactory.create())
                .build();

        Lighter lighter = LighterUndertow.builder()
                .adapterFactory(adaptorFactory)
                .injectionFactory(injector::getInstance)
                .addRouter(AutomaticRouteConfigurationLoader.loadAutomaticConfiguration())
                .hostName("0.0.0.0")
                .port(8000)
                .build();


        lighter.start();
    }
}
