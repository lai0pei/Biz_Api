package io.lab.core.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class seeder implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
       System.out.println(Arrays.toString(args));
    }
}
