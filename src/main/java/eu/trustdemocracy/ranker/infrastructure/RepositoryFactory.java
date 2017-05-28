package eu.trustdemocracy.ranker.infrastructure;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import eu.trustdemocracy.ranker.gateways.repositories.MongoRankRepository;
import eu.trustdemocracy.ranker.gateways.repositories.RankRepository;
import java.util.Properties;
import lombok.val;

public class RepositoryFactory {

  private static final String DATABASE_PROPERTIES_FILE = "database.properties";
  private static final String PROPERTIES_KEY_SERVER = "server";
  private static final String PROPERTIES_KEY_PORT = "port";
  private static final String PROPERTIES_KEY_DATABASE = "database";
  private static RankRepository rankRepository;


  public static RankRepository getRankRepository() {
    if (rankRepository == null) {
      rankRepository = new MongoRankRepository(getConnection());
    }
    return rankRepository;
  }


  public static MongoDatabase getConnection() {
    Properties properties;
    try {
      properties = getProperties();
    } catch (Exception e) {
      throw new RuntimeException("Failed to read database properties file ["
          + DATABASE_PROPERTIES_FILE + "]", e);
    }

    throwIfMissingKey(properties, PROPERTIES_KEY_SERVER);
    throwIfMissingKey(properties, PROPERTIES_KEY_PORT);
    throwIfMissingKey(properties, PROPERTIES_KEY_DATABASE);

    val server = properties.getProperty(PROPERTIES_KEY_SERVER);
    val port = Integer.valueOf(properties.getProperty(PROPERTIES_KEY_PORT));
    val db = properties.getProperty(PROPERTIES_KEY_DATABASE);

    val mongoClient = new MongoClient(server, port);
    return mongoClient.getDatabase(db);
  }


  private static void throwIfMissingKey(Properties properties, String key) {
    if (!properties.containsKey(key)) {
      throw new RuntimeException(
          "Unable to find key " + key + "in " + DATABASE_PROPERTIES_FILE);
    }
  }

  private static Properties getProperties() throws Exception {
    val properties = new Properties();
    val inputStream = RepositoryFactory.class.getClassLoader()
        .getResourceAsStream(DATABASE_PROPERTIES_FILE);
    properties.load(inputStream);
    inputStream.close();

    loadSystemProperties(properties);

    return properties;
  }

  private static void loadSystemProperties(Properties properties) {
    val dbHost = System.getenv("db_host");
    if (dbHost != null) {
      properties.put(PROPERTIES_KEY_SERVER, dbHost);
    }
  }
}
