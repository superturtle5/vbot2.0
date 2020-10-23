import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {
    private String path;
    private boolean append_to_file = false;

    public WriteFile(String file_path) {
        path = file_path;
    }
    public WriteFile(String file_path, Boolean append_value) {
        path = file_path;
        append_to_file = append_value;
    }
    public void writeToFile(String textLine) throws IOException{
        FileWriter write = new FileWriter( path , append_to_file);
        PrintWriter print_line = new PrintWriter( write );
        print_line.printf( "%s" + "%n" , textLine);
        print_line.close();
    }
    public String read() throws IOException {
        String r = "";
        FileReader fr = new FileReader(path);
        int i;
        while ((i=fr.read()) != -1) {
            r+= (char) i;
        }
        return r;
    }


}
