package ru.otus.spring.domain;

public class Student {
    private String name;
    private String surname;
    private int numberCorrectAnswers;
    private int rating = 2;

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public int getNumberCorrectAnswers() {
        return numberCorrectAnswers;
    }

    public void setNumberCorrectAnswers(int numberCorrectAnswers) {
        this.numberCorrectAnswers = numberCorrectAnswers;
        if (numberCorrectAnswers > 2){
            setRating(3);
        }

        if (numberCorrectAnswers > 3){
            setRating(4);
        }

        if (numberCorrectAnswers > 4){
            setRating(5);
        }
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFio(){
        return name + " " + surname;
    }
}
