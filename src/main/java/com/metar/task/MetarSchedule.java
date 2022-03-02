package com.metar.task;

import com.metar.model.Metar;
import com.metar.model.Subscription;
import com.metar.repo.MetarRepository;
import com.metar.repo.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
public class MetarSchedule {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    MetarRepository metarRepository;

    public static String baseUrl = "https://tgftp.nws.noaa.gov/data/observations/metar/stations/";

    @Scheduled(initialDelayString = "${scheduling.fixedDelay}",fixedDelayString = "${scheduling.fixedDelay}")
    void retrieveMetarData(){
        List<Subscription> subscriptionList = subscriptionRepository.findAll();
        if(subscriptionList.isEmpty())return;

        for (Subscription subscription: subscriptionList){
            String icaoCode = subscription.getIcaoCode();
            String urlString = baseUrl + icaoCode+".TXT";

            Runnable task = ()->{
                System.out.println(urlString);
                String metarData = null;
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");

                    int status = con.getResponseCode();
                    if(status==200) {
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(con.getInputStream()));
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            if(inputLine.startsWith(icaoCode)){
                                metarData = inputLine.substring(4);
                                System.out.println(metarData.trim());
                                break;
                            }
                        }
                        System.out.println("-------------------");
                        in.close();
                    }

                    con.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(metarData!=null){
                    Metar metar = new Metar(metarData, Instant.now(),subscription);
                    metarRepository.save(metar);
                }
            };

            new Thread(task).start();
        }
    }
}
