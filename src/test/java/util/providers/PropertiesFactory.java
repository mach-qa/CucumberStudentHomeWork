package util.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.reader.YamlReader;
import util.yamlModels.TestData;
import util.yamlModels.UrlData;

import java.util.Map;

public class PropertiesFactory {

    private static final Logger logger = LoggerFactory.getLogger("PropertiesFactory.class");
    YamlReader yamlReader = new YamlReader();
    protected UrlData urlData;
    protected TestData testData;

    private PropertiesFactory() {
        setUrlProperties();
        setTestDataProperties();
    }

    public static PropertiesFactory getInstance() {
        return PropertiesFactory.PropertiesFactorySingleton.INSTANCE;
    }

    private static class PropertiesFactorySingleton {
        private static final PropertiesFactory INSTANCE = new PropertiesFactory();
    }

    private void setUrlProperties() {
        logger.info("<------------------Start setting properties for URL data------------------>");
        urlData = yamlReader.getConfig().getUrlData();
        Map<String, Object> urlDataProperties = urlData.getUrlProperties();
        for (Map.Entry entry : urlDataProperties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
        }
    }

    private void setTestDataProperties() {
        logger.info("<------------------Start setting properties for Test data------------------>");
        testData = yamlReader.getConfig().getTestData();
        Map<String, Object> testDataProperties = testData.getDataProperties();
        for (Map.Entry entry : testDataProperties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
        }
    }
}
