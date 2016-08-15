package com.myapp;

import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by JohnC on 2016-08-11.
 */
public class ExtractClass
{
    public static void main(String[] args) throws IOException
    {
        Console console = System.console();
        String path = console.readLine("请输入解压缩目录：");
        if (path != null)
        {
            Files.walk(Paths.get(path)).forEach(filePath ->
            {
                if(filePath.toString().endsWith(".tar.gz"))
                {
                    File tarFile = new File(filePath.toString());
                    File destFile = new File(filePath.toString().replace(".tar.gz",""));
                    Archiver archiver = ArchiverFactory.createArchiver("tar","gz");
                    try
                    {
                        archiver.extract(tarFile,destFile);
                        System.out.println(filePath.toString().replace(".tar.gz",""));
                        tarFile.delete();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
        
        
        
    }
}
