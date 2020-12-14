package Utility;
import Models.Project;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataManagement {

    public void writeData(ArrayList<Project> allProjects){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("allProjects.json"),
                    allProjects);

        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch (JsonGenerationException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@Test
    public ArrayList<Project> readProData(ArrayList<Project> projects){

        try {

            ObjectMapper mapper = new ObjectMapper();

            projects = mapper.reader().forType(new TypeReference<ArrayList<Project>>() {})
                    .readValue(new File("allProjects.json"));

            Scan.print(projects.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return projects;
    }
}
