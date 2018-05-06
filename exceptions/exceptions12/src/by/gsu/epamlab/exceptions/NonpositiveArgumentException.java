package by.gsu.epamlab.exceptions;

public class NonpositiveArgumentException extends IllegalArgumentException {
}

/*
– public NonpositiveArgumentException(String cause) – самый простой и одновременно плохой вариант.
– public NonpositiveArgumentException(int nonpositiveValue, String fieldName) – вариант без контроля поля на этапе трансляции.
– public NonpositiveArgumentException(int nonpositiveValue, NumField field) – вариант с раздельным хранением недопустимого значения и контролем поля.
– public NonpositiveArgumentException(NumField field) – вариант с контролем поля, которое хранит недопустимое значение.

 */