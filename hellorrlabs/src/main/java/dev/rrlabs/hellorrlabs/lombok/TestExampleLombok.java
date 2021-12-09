package dev.rrlabs.hellorrlabs.lombok;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestExampleLombok {

    public static void main(String[] args) {

        LombokExample example2 = new LombokExample("theo", "rocha");
        System.out.println(example2);

        System.out.println(LombokExample.builder().name("Milena").lastname("Rocha").build());

        LombokExample example3 = new LombokExample("Regis");
        System.out.println(example3);

        System.out.println("Data de hoje: " + Util.today());
        System.out.println("XPTO: " + Util.XPTOP);

        log.info("Uso de log....");
    }

}
