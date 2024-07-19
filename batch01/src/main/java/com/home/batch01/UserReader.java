/**
 * 
 */
package com.home.batch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

import jakarta.batch.api.chunk.AbstractItemReader;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;


/**
 * 
 * @author Ahmad Alrefai
 */
@Named
@Dependent
public class UserReader extends AbstractItemReader {

    private BufferedReader br;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        br = new BufferedReader(
                new InputStreamReader(
                        Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("META-INF/user.txt")));
    }

    @Override
    public String readItem() {
        String line = null;

        try {
            line = br.readLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return line;
    }
}
