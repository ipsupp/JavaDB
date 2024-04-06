import domain.Agency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.AgencyRepo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestRepository {
    @Test
    @DisplayName("Agency, TCompany, Employee Test.")
    public void testATE() throws IOException {
//        Agency ag = new Agency("1","nexus");
//        Agency ag1 = new Agency("2","elude");
        Properties props = new Properties();
        //dbUtils=new jdbcUtils(props)
        try{
            System.out.println("aaaa");
            props.load(new FileInputStream("/Users/Ioana/IdeaProjects/mpp-java-lab/db.config")); // Update the path accordingly

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
