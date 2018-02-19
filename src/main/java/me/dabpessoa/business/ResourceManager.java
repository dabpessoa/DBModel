package me.dabpessoa.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by diego.pessoa on 12/01/2017.
 */
public class ResourceManager {

    public static StringBuilder readLinesFromTextFile(String path) {

        StringBuilder conteudo = new StringBuilder();
        File file = new File(path);

        if (!file.exists())
            return null;

        try {
            FileReader fr;
            fr = new FileReader(file);

            BufferedReader br = new BufferedReader(fr);
            String linha = br.readLine();
            while (linha != null) {
                conteudo.append(linha).append('\n');
                linha = br.readLine();
            }
            br.close();fr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conteudo;
    }

}
