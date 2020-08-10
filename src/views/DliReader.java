package views;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DliReader {

    private String filePath;
    private FileReader reader;

    public DliReader(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        reader = new FileReader(this.filePath);
    }

    public String getFileContent() throws IOException {
        BufferedReader bReader = new BufferedReader(reader);
        String fileLine, fileContent = "";
        while((fileLine = bReader.readLine()) != null)
            fileContent += fileLine;
        bReader.close();
        return fileContent;
    }
}
