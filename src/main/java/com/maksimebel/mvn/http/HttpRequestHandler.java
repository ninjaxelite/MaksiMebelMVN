/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maksimebel.mvn.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Magamet
 */
public class HttpRequestHandler {

    public HttpRequestHandler() {

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet post = new HttpGet("http://partners.api.skyscanner.net/apiservices/browsequotes/v1.0");
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("apiKey", "ma313279152991767978284338338282"));
            nameValuePairs.add(new BasicNameValuePair("country", "AUT"));
            nameValuePairs.add(new BasicNameValuePair("currency", "EUR"));
            nameValuePairs.add(new BasicNameValuePair("locale", "de-DE"));
            nameValuePairs.add(new BasicNameValuePair("originplace", "Vienna"));
            nameValuePairs.add(new BasicNameValuePair("destinationplace", "Moscow"));
            nameValuePairs.add(new BasicNameValuePair("outbounddate", "2017-03-14"));
            nameValuePairs.add(new BasicNameValuePair("inbounddate", "2017-03-30"));
//            nameValuePairs.add(new BasicNameValuePair("adults", "1"));
            //post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            post.setHeader("Accept", "application/json");

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
//            post.setHeader("X-Forwarded-For", ipAddress);

            CloseableHttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));
            System.out.println("GET Status: " + response.getStatusLine().getStatusCode());
            System.out.println("GET Message: " + response.getStatusLine().getReasonPhrase());
            StringBuilder content = new StringBuilder();
            while (rd.ready()) {
                String l = rd.readLine();
                content.append(l);
                System.out.println(l);
            }

        } catch (IOException e) {
            Logger.getLogger(HttpRequestHandler.class.getName()).log(Level.SEVERE, null, e);
        }

//        URL url;
//        try {
//            url = new URL("https://booking.pobeda.aero/ScheduleSelect.aspx");
//            String params = "fromStation=Wien (Hauptbahnhof)&toStation=Moskau (Vnukovo)&beginDate=15.03.2017&endDate=18.03.2017";
//            params = URLEncoder.encode(params, "UTF-8");
//            
//            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
//            httpCon.setDoOutput(true);
//            httpCon.setRequestMethod("POST");
//            httpCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            httpCon.setRequestProperty("charset", "utf-8");
//            httpCon.setRequestProperty("Content-Length", Integer.toString(params.getBytes().length));
//            try (OutputStreamWriter out = new OutputStreamWriter(
//                    httpCon.getOutputStream())) {
//                out.write(params);
//		out.flush();
//		out.close();
//                System.out.println(httpCon.getResponseCode());
//                System.out.println(httpCon.getResponseMessage());
//                BufferedReader br = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
//                String h = "";
//                while((h = br.readLine()) != null){
//                    System.out.println(""+h);
//                }
//            } catch (Exception e) {
//                Logger.getLogger(HttpRequestHandler.class.getName()).log(Level.SEVERE, null, e);
//            }
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(HttpRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ProtocolException ex) {
//            Logger.getLogger(HttpRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(HttpRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(HttpRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
