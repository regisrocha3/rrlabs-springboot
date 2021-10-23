package dev.rrlabs.hellorrlabs.lombok;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class Util {

    public String XPTOP = "adasdas";

    public LocalDate today() {
        return LocalDate.now();
    }

}
