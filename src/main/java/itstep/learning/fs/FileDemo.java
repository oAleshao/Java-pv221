package itstep.learning.fs;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

public class FileDemo {

    public void run(){
       // Ресурси - файли, що автоматично переносяться у target/classes
        // і доступні при запуску через ClassLoader

    }

    public void addDbIni(String key, String value){
        try {
            String fileName = "/db.ini";
            Path path = new File(this.getClass().getResource(fileName).getFile()).toPath();
            String data = "\n" + key + "=" + value;

            try {
                Files.write(path, data.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

            } catch (IOException e) {
                e.getMessage();
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void showDbIni(){
        Map<String, String> ini = getDbIniData();
        System.out.println(
                String.format("jdbc:%s://%s:%s/%s",
                        ini.get("dbms"),
                        ini.get("host"),
                        ini.get("port"),
                        ini.get("schema"),
                        ini.get("utf")
                )
        );

    }

    public Map<String, String> getDbIniData(){
        String fileName = "db.ini";
        try(InputStream rs =
                    this.getClass().getClassLoader().getResourceAsStream(fileName))
        {
            String content = readStream(rs);
            Map<String, String> ini = new HashMap<>();

            String[] lines = content.split("\n");
            for(String line : lines){
                String[] parts = line.split("=");
                ini.put(parts[0].trim(), parts[1].trim());
            }

            return ini;
        }catch (IOException e){
            return null;
        }
    }

    public void runFile(){
        System.out.println("File System");
        // Основним об'єктом для роботи з файлами є java.io.File
        File file = new File("test.txt");
        // !! створення об'єкту File ніяк не впливає на саму файлову систему
        // !! File-об'єкти відповідають як за файли, так і за директорії

        // Робота з даними у файлах здійснюється через потоки даних (Stream)
        // У Java багаторівнева ієрархія засобів роботи з потоками
        // Основні: InputStream / OutputStream - вища абстракція
        // Імплементації: InputStream / ...


        // ЗАДАЧА - одержати дані з ресурсу у вигляді String
        // Складність: можливість multibyte кодування (UTF-8)
        // Рішення: одержати всі байти з ресурсу і провести декодування
        // Складність: ми не знаємо об'єм даних (потік передає по одному)
        ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len;

        // try() - try-with-resource -- аналог using - блок з автоматичним
        // вивільнення некерованих ресурсів
        try(InputStream is = new FileInputStream(file)) {
           System.out.println(readStream(is));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String readStream(InputStream in, Charset charset) throws  IOException{
        byte[] buffer = new byte[4096];
        try(
                ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();
                BufferedInputStream bis = new BufferedInputStream(in)) {
            int len;
            while((len = bis.read(buffer)) != -1){
                byteBuilder.write(buffer, 0, len);
            }

            return byteBuilder.toString(charset.name());
        }
    }

    private String readStream(InputStream in) throws IOException {
       return readStream(in, StandardCharsets.UTF_8);
    }
}

/*
* Робота з файлами. Ресурси
*
* */