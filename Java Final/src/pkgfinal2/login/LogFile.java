package pkgfinal2.login;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class LogFile {
    
    public static void write(String username,LogEvents events){
        StringBuilder sb = new StringBuilder();
            sb.append(events + "\t");
            sb.append(username + "\t");
            sb.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd yyy\th:mma")));
            sb.append("\n");
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/src/pkgfinal2/log.txt",true))){
            bw.write(sb.toString());
            bw.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
