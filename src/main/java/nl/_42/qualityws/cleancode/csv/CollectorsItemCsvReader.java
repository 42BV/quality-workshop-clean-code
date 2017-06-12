package nl._42.qualityws.cleancode.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.csveed.api.CsvClient;
import org.csveed.api.CsvClientImpl;
import org.springframework.stereotype.Component;

@Component
public class CollectorsItemCsvReader {

    public <T extends CollectorsItemCsvRecord> List<T> read(InputStream items, Class<T> csvClass) {
        try(Reader reader = new InputStreamReader(items)) {
            CsvClient<T> client = new CsvClientImpl<T>(reader, csvClass);
            return client.readBeans();
        } catch (IOException err) {
            throw new RuntimeException(err);
        }
    }

}
