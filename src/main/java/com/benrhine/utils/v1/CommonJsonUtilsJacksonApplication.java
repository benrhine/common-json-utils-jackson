package com.benrhine.utils.v1;

import javax.annotation.processing.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/** --------------------------------------------------------------------------------------------------------------------
 * com.benrhine.utils.v1.CommonJsonUtilsJackson: TODO fill me in.
 * ------------------------------------------------------------------------------------------------------------------ */
@SpringBootApplication
public class CommonJsonUtilsJacksonApplication {
    private static ApplicationContext applicationContext;

    /**
     * The @Generated annotation is added here to exclude the main method from JaCoCo coverage as it is
     * not possible to unit test the main method.
     *
     * @param args String[]
     */
    @Generated({})
    public static void main(String[] args) {
        applicationContext = SpringApplication.run(CommonJsonUtilsJacksonApplication.class, args);
    }
}
