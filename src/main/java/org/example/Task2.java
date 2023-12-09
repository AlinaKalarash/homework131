package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.example.dto.CurrencyDto;

public class Task2 {
    public static void main(String[] args) throws IOException {
        String doc = Jsoup.connect("https://jsonplaceholder.typicode.com/users/1/posts")
                .ignoreContentType(true)
                .get()
                .body()
                .text();

        Type listType = new TypeToken<List<CurrencyDto>>(){}.getType();
        List<CurrencyDto> postsList = new Gson().fromJson(doc, listType);

        List<Integer> list2 = postsList.stream().map(p -> p.getId()).collect(Collectors.toList());
        int y = Collections.max(list2);

        List<Integer> list3 = new ArrayList<>();
                postsList.stream().filter(p -> p.getId() == y)
                .peek(p -> list3.add(p.getUserId()))
                .collect(Collectors.toList());
        int x = list3.get(0);

        File file = new File("user-"+x+"-post-"+y+"-comments.json.txt");
        FileWriter fileWriter = new FileWriter(file);

        String doc2 = Jsoup.connect("https://jsonplaceholder.typicode.com/posts/10/comments")
                .ignoreContentType(true)
                .get()
                .body()
                .text();

        Type comments = new TypeToken<List<CurrencyDto>>(){}.getType();
        List<CurrencyDto> commentsList = new Gson().fromJson(doc2, comments);

        commentsList.stream()
                .forEach(com -> {
                    try {
                        fileWriter.write(com.getBody());
                        System.out.println(com.getBody());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        fileWriter.close();

    }
}
