package apiEngine;

public class Endpoints {

    public static final String BASE_URL = System.getProperty("baseURL");
    public static final String API_PATH = System.getProperty("apiPath");
    public static final String STUDENT_DETAILS_ENDPOINT = System.getProperty("studentsDetailsEndpoint");

    public String getBaseUri() {
        return System.getProperty("baseURL");
    }
    public String getApiPath() {
        return System.getProperty("apiPath");
    }

    public String getStudentsDetailsEndpoint() {
        return System.getProperty("studentsDetailsEndpoint");
    }
}
