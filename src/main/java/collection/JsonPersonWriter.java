package collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import person.Person;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class JsonPersonWriter implements PersonWriter {

    private String fileName;

    public JsonPersonWriter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void writePersons(Map<String, Person> persons) {
        try {
            FileOutputStream writer = new FileOutputStream(fileName, false);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(persons);
            writer.write(json.getBytes());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
