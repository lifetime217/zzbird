package com.luoran.zzbird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.luoran.zzbird","com.luoran.wechat"})
public class ZzbirdApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZzbirdApplication.class, args);
	}

}
