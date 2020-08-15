package views;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DliReader {

    private FileReader reader;

    public DliReader(String filePath) throws FileNotFoundException {
        reader = new FileReader(filePath);
    }

    public String getFileContent() throws IOException {
        BufferedReader bReader = new BufferedReader(reader);
        String fileLine;
        StringBuilder fileContent = new StringBuilder();
        while((fileLine = bReader.readLine()) != null)
            fileContent.append(fileLine);
        bReader.close();
        return fileContent.toString();
    }
}
