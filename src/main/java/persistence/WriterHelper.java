package persistence;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

/**
 * Helper class for writing to file
 * @param <T>
 */
public class WriterHelper<T> {

    public void WriteToFileSerializer(String file, List<T> list, Gson g){

        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);

            g.toJson(list, writer);
            writer.flush();
            writer.close();
            fw.close();

        }
        catch (java.io.IOException ioe){
            System.out.println(ioe);
        }
    }
}
