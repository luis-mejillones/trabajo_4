package services;

import model.Kudos;
import util.Logger;

import javax.inject.Inject;

public class KudosService {
//    String baseHref = BASE_HREF;
//    private KudosElsConfig kudosElsConfig;
//    private final Checked.IOCommand<Kudos, Kudos> create;

    @Inject
    public KudosService(
//            final KudosElsConfig kudosElsConfig
//            final Checked.IOCommand<Kudos, Kudos> create
    ) {
//        this.kudosElsConfig = kudosElsConfig;
//        this.create = create;
    }

    public Kudos create(Kudos in) throws Exception {
//        Kudos out = ElasticSearchDefaultDataAccess.save(kudosElsConfig, baseHref, in);
        Logger.log.info("Kudos created with id: " + in);

        return new Kudos();
    }
}
