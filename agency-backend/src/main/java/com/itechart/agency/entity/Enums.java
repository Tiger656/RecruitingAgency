package com.itechart.agency.entity;

public class Enums {
    public enum Experience {
        UP_TO_YEAR("Up to 1 year"),
        ONE_TO_THREE("1 - 3 years"),
        THREE_TO_FIVE("3 - 5 years"),
        FIVE_TO_EIGHT("5 - 8 years"),
        OVER_EIGHT("Over 8 years");
        private String str;

        Experience(String s) {
            this.str = s;
        }
    }
    public enum AgeRestriction{
        ANY("-"),
        OVER_THIRTY("Over 30 years old"),
        OVER_FORTY("Over 40 years old");
        private String str;

        AgeRestriction(String s) {
            this.str = s;
        }
    }
}
