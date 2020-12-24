package Utility;
import Models.Project;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataManagement {

    public void exportProData(ArrayList<Project> allProjects){
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

    public ArrayList<Project> importProData(ArrayList<Project> projects){

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

    public static LocalDate stringToLocalDate(String date){
        String[] values = date.split("-");
        int year = Integer.parseInt(values[0]);
        int month = Integer.parseInt(values[1]);
        int day = Integer.parseInt(values[2]);
        LocalDate returnDate = LocalDate.of(year, month, day);

        return returnDate;
    }
}
