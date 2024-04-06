package ro.mpp2024;

import repository.AgencyRepo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Properties props = new Properties();
        //dbUtils=new jdbcUtils(props)
        try{
            props.load(new FileReader("C:/Users/Ioana/IdeaProjects/mpp-java-lab/db.config")); // Update the path accordingly

        }catch(IOException e){
            e.printStackTrace();
        }
        AgencyRepo agr = new AgencyRepo(props);
//        agr.add(ag);
//        agr.add(ag1);
        if (agr.getName("1") == "nexus" & agr.getName("2") == "elude");
        {
            System.out.println("Test Agency passed");
        }
    }
}