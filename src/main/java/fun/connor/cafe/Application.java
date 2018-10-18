package fun.connor.cafe;

import com.google.inject.Guice;
import com.google.inject.Injector;
import fun.connor.lighter.Lighter;
import fun.connor.lighter.autoconfig.AutomaticRouteConfigurationLoader;

public class Application {

    public static void main(String[] args) {
        Injector i = Guice.createInjector();


        Lighter lighter = new Lighter(i::getInstance);

        lighter.addRouter(AutomaticRouteConfigurationLoader.loadAutomaticConfiguration());

        lighter.start();
    }
}
