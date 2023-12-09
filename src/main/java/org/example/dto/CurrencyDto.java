package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class CurrencyDto {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;

    @Getter
    @Setter
    @AllArgsConstructor
    public class Address {
        public String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;

        @Getter
        @Setter
        @AllArgsConstructor
        public class Geo {
            private double lat;
            private double lng;
        }

    }

    private String phone;
    private String website;
    private Company company;

    @Getter
    @Setter
    @AllArgsConstructor
    public class Company {
        private String name;
        private String catchPhrase;
        private String bs;
    }

    private int postId;
    private String body;

    private int userId;
    private String title;
    private boolean completed;


}
