package util.yamlModels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Config {

    public UrlData urlData;
    public TestData testData;
}
