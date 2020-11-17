package ru.otus.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FormBookForSaveNewBookDto {
    private String title;
    private String description;
    private List<String> authornames;
    private List<String> genrernames;
    private String newcomment;
}