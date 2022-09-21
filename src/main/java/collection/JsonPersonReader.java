package collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import person.AbstractPerson;
import person.DefaultPerson;
import person.Person;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Specific implementation of the PersonReader interface for Json format
 */
public class JsonPersonReader implements PersonReader{

    private String fileName;

    public JsonPersonReader(String fileName) {
        this.fileName = fileName;
    }


    // НЕ РАБОТАЕТ !!! Ошибка при возврате коллекции
    @Override
    public Map<String, Person> readPersons() {
        String jsonString = getJsonString();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type type = new TypeToken<HashMap<String, Person>>(){}.getType();
        Map<String, Person> persons = gson.fromJson(jsonString, type);
        return persons;
    }

    private String getJsonString() {
        String jsonString = null;
        try {
            InputStream is = new FileInputStream(fileName);
            Reader isr = new InputStreamReader(is);
            StringBuilder sb = new StringBuilder();
            int data = -1;
            while ((data = isr.read()) != -1) {
                sb.append((char) data);
            }
            isr.close();
            jsonString = sb.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (jsonString != null) {
            return jsonString;
        } else {
            throw new NullPointerException();
        }

    }
}
