package com.example.CovidTracker.CovidDataService;


import com.example.CovidTracker.models.LocationStatus;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {
    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationStatus> allStats = new ArrayList<>();

    public List<LocationStatus> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStatus> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();

        HttpResponse<String> httpResponse = client.send(request,HttpResponse.BodyHandlers.ofString());
       // System.out.println(httpResponse.body());

        StringReader csvBodyReader = new StringReader(httpResponse.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            LocationStatus locationStatus = new LocationStatus();
            locationStatus.setState(record.get("Province/State"));
            locationStatus.setCountry(record.get("Country/Region"));
            int latestCases = Integer.parseInt(record.get(record.size() -1));
            int prevDay = Integer.parseInt(record.get(record.size() -2));
            locationStatus.setLatestTotalCases(latestCases);
            locationStatus.setLatestTotalCases(latestCases - prevDay);

          //  System.out.println(locationStatus);
            newStats.add(locationStatus);
           // System.out.println(state);
           // String customerNo = record.get("CustomerNo");
            //String name = record.get("Name");
        }
        this.allStats = newStats;

    }
}
