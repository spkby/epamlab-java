package by.gsu.epamlab.exceptions;

public class NonpositiveArgumentException extends IllegalArgumentException {

    private static String MESSAGE = "Non positive value";

    private final String field;
    private final int value;

    public NonpositiveArgumentException(int nonpositiveValue, String fieldName) {

        value = nonpositiveValue;
        field = fieldName;

        System.err.println(MESSAGE + " " + value + " " + field);
    }

    /*
    public NonpositiveArgumentException(int nonpositiveValue, NumField field) {

    }

    public NonpositiveArgumentException(NumField field) {

    }*/
}

/*
–  – самый простой и одновременно плохой вариант.
–  – вариант без контроля поля на этапе трансляции.
–  – вариант с раздельным хранением недопустимого значения и контролем поля.
–  – вариант с контролем поля, которое хранит недопустимое значение.
 */