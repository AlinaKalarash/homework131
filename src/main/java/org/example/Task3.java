package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.dto.CurrencyDto;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class Task3 {
    public static void main(String[] args) throws IOException {

        String doc = Jsoup.connect("https://jsonplaceholder.typicode.com/users/1/todos")
                .ignoreContentType(true)
                .get()
                .body()
                .text();

        Type listType = new TypeToken<List<CurrencyDto>>(){}.getType();
        List<CurrencyDto> userTODOList = new Gson().fromJson(doc, listType);
        int X = 1;

        new Task3().getTODO(X, userTODOList);
    }

    public void getTODO(int X, List<CurrencyDto> userTODOS) {
        System.out.println("TODO for user " + X + ": ");
        userTODOS.stream()
                .filter(c -> c.getUserId() == X)
                .filter(c -> !c.isCompleted())
                .peek(c -> System.out.println(c.getTitle()))
                .collect(Collectors.toList());

    }
}