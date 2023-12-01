package ru.ulstu.accounting.logics.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NonWorkingReason {
    VACATION("Отпуск"),
    MEDICAL("Больничный");

    private final String text;
}
