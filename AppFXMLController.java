/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather.and.news.app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AppFXMLController implements Initializable {

    @FXML
    private Button sports;
    @FXML
    private Button tech;
    @FXML
    private Button business;
    @FXML
    private Button ent;
    @FXML
    private TextArea news;
    @FXML
    private TextField loc;
    @FXML
    private Button search;
    @FXML
    private TextArea currWeather;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void getNews( String url) throws IOException, InterruptedException{
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        news.setText(response.body());
    }
    
    @FXML
    private void dispTech(ActionEvent event) throws IOException, InterruptedException {
        var url = "https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=bacd166abc7f461492173d9880e12566";
        getNews(url);
    }
    
    @FXML
    private void dispSports(ActionEvent event) throws IOException, InterruptedException {
        var url = "https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=bacd166abc7f461492173d9880e12566";
        getNews(url);
    }

    @FXML
    private void dispBuz(ActionEvent event) throws IOException, InterruptedException {
        var url = "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=bacd166abc7f461492173d9880e12566";
        getNews(url);
    }

    @FXML
    private void dispEnt(ActionEvent event) throws IOException, InterruptedException {
        var url = "https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=bacd166abc7f461492173d9880e12566";
        getNews(url);
    }

    @FXML
    private void dispWeather(ActionEvent event) throws IOException, InterruptedException {
        String location = loc.getText().toLowerCase();
        var url = "https://weatherdbi.herokuapp.com/data/weather/"+location;
        
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        String res = response.body();

        int t = res.indexOf("temp");
        int c = res.indexOf("comment");
        //int h = res.indexOf("humidity");
        
        String tmp = res.substring(t+11, t+13);
        String com = res.substring(c+10,c+14);
        //String hum = res.substring(h+11, t+14);
        
        String weatherNow;
        weatherNow = "Condition:\n"+com+"\nCurrent temperature:\n"+tmp+" degree Celcius";//"\nHumidity:\n"+hum;
        currWeather.setText(weatherNow);
    }
    
}
