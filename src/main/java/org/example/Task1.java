package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import org.example.dto.CurrencyDto;

public class Task1 {
    public static void main(String[] args) throws IOException {

        Task1 main = new Task1();

        main.getAllData();
        System.out.println("\n#############\n");
        main.getAllDataById(4);
        System.out.println("\n#############\n");
        main.getAllDataByUserName("Delphine");
    }

    public void getAllData() throws IOException {
        user().stream().forEach(us -> { getAllData(us);
                System.out.println("**************"); });


    }

    public void getAllDataByUserName(String userName) throws IOException {
        user().stream().filter(us -> us.getUsername().equals(userName))
                .forEach(us -> getAllData(us));
    }

    public void getAllDataById(int id) throws IOException {
        user().stream().filter(us -> us.getId() == id)
                .forEach(us -> getAllData(us));
    }

    public List<CurrencyDto> user() throws IOException {
        String doc = Jsoup.connect("https://jsonplaceholder.typicode.com/users")
                .ignoreContentType(true)
                .get()
                .body()
                .text();


        Type listType = new TypeToken<List<CurrencyDto>>(){}.getType();
        return new Gson().fromJson(doc, listType);
    }

    public void getAllData(CurrencyDto us) {
        System.out.println("Id: " + us.getId());
        System.out.println("Name: " + us.getName());
        System.out.println("Username: " + us.getUsername());
        System.out.println("Email: " + us.getEmail());
        System.out.println("Street: " + us.getAddress().getStreet());
        System.out.println("Suite: " + us.getAddress().getSuite());
        System.out.println("City: " + us.getAddress().getCity());
        System.out.println("Zipcode: " + us.getAddress().getZipcode());
        System.out.println("Lat: " + us.getAddress().getGeo().getLat());
        System.out.println("Lng: " + us.getAddress().getGeo().getLng());
        System.out.println("Phone: " + us.getPhone());
        System.out.println("Website: " + us.getWebsite());
        System.out.println("CompanyName: " + us.getCompany().getName());
        System.out.println("CatchPhrase: " + us.getCompany().getCatchPhrase());
        System.out.println("Bs: " + us.getCompany().getBs());
    }

}
