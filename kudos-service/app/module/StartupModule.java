package module;

import com.google.inject.AbstractModule;

public class StartupModule extends AbstractModule {
    @Override
    protected void configure() {
//        this.bind(ElasticSearchIndexCreator.class).asEagerSingleton();
    }
}