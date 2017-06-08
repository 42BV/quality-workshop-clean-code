package nl._42.qualityws.cleancode.collectors_item.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.csveed.api.CsvClient;
import org.csveed.api.CsvClientImpl;

public class CsvReader<T extends CollectionsItemCsvRecord> {

    private final Class<T> csvRecordType;

    public CsvReader(Class<T> csvRecordType) {
        this.csvRecordType = csvRecordType;
    }

    public List<T> read(InputStream items) {
        try(Reader reader = new InputStreamReader(items)) {
            CsvClient<T> client = new CsvClientImpl<>(reader, csvRecordType);
            return client.readBeans();
        } catch (IOException err) {
            throw new RuntimeException(err);
        }
    }

}
