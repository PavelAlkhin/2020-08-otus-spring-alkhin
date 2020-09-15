package ru.otus.spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class Student {

    private final String name;
    private final String surname;

    @Getter
    private int numberCorrectAnswers;

    @Getter  @Setter
    private int rating = 2;

    @Override
    public String toString() {
        return "Student " + getFio() + " was tested with an assessment " + getRating();
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

    public String getFio(){
        return name + " " + surname;
    }
}
