package com.gyc.trade.zipkin;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import org.springframework.context.annotation.Bean;

import zipkin.storage.mysql.MySQLStorage;

@SpringBootApplication
@EnableZipkinStreamServer
public class ZipkinApplication {

    public static void main(String[] args) {
    	boolean hasProfile = Arrays.stream(args).anyMatch(arg -> arg.contains("configurl"));
        String[] cArgs=args;
        if(!hasProfile){
            cArgs=new String[args.length+2];
            cArgs[0]="--configurl=localhost";
            cArgs[1]="--profile=local";
            System.arraycopy(args,0,cArgs,2,args.length);
        }
    	new SpringApplicationBuilder(ZipkinApplication.class).web(true).run(args);
    }
    
    @Bean
	public MySQLStorage mySQLStorage(DataSource datasource) {
		return MySQLStorage.builder().datasource(datasource).executor(Runnable::run).build();
	}

}