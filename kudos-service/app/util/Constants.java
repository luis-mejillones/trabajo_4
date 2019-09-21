package util;

public class Constants {
    public static final String SERVICE_NAME = "kudos-service";
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final String MONGODB_HOST = "localhost";
    public static final int MONGODB_PORT = 27017;
    public static final String MONGODB_DATABASE = "omega";
    public static final String MONGODB_COLLECTION = "kudos";

    public static final String USERS_QUEUE = "users_queue";
    public static final String KUDOS_QUEUE = "kudos_queue";

    public static final String QUEUE_HOST = "localhost";
    public static final int QUEUE_PORT = 5672;
    public static final String QUEUE_USER_NAME = "admin";
    public static final String QUEUE_PASSWORD = "12345";
}
