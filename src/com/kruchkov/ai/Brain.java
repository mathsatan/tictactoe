package com.kruchkov.ai;

import com.kruchkov.di.Field;
import com.kruchkov.di.TickPoint;

//import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Brain implements Iterable<Byte[][]> {
    //private final static String BRAIN_FILE_NAME = "C:\\Users\\soulf\\IdeaProjects\\di\\res\\brain.bin";
    private List<Byte[][]> pattern = null;

    public boolean analyze(TickPoint p, Field field) {
        Byte[][] newPattern = {{0, 0}, {0, 0}};
        //pattern.pattern.add(newPattern);
        return false;
    }

    @Override
    public Iterator<Byte[][]> iterator() {
        return pattern.iterator();
    }

    public Brain() {
        pattern = new LinkedList<>();
        pattern.add(new Byte[][]{{2,2,4,2,2}});
        pattern.add(new Byte[][]{
                {2},
                {2},
                {4},
                {2},
                {2}});
        /*pattern.add(new Byte[][]{
                {2,3,3,3,3},
                {3,2,3,3,3},
                {3,3,4,3,3},
                {3,3,3,2,3},
                {3,3,3,3,2}});
        pattern.add(new Byte[][]{
                {3,3,3,3,2},
                {3,3,3,2,3},
                {3,3,4,3,3},
                {3,2,3,3,3},
                {2,3,3,3,3}});
        pattern.add(new Byte[][]{{0,2,2,2,2,4}});
        pattern.add(new Byte[][]{
                {0},
                {2},
                {2},
                {2},
                {2},
                {4}});
        pattern.add(new Byte[][]{{3,3,3,3,3,0},
                {3,3,3,3,2,3},
                {3,3,3,2,3,3},
                {3,3,2,3,3,3},
                {3,2,3,3,3,3},
                {4,3,3,3,3,3}});
        pattern.add(new Byte[][]{
                {0,3,3,3,3,3},
                {3,2,3,3,3,3},
                {3,3,2,3,3,3},
                {3,3,3,2,3,3},
                {3,3,3,3,2,3},
                {3,3,3,3,3,4}});
        pattern.add(new Byte[][]{{2,2,2,2,4}});
        pattern.add(new Byte[][]{
                {2},
                {2},
                {2},
                {2},
                {4}});
        pattern.add(new Byte[][]{
                {2,3,3,3,3},
                {3,2,3,3,3},
                {3,3,2,3,3},
                {3,3,3,2,3},
                {3,3,3,3,4}});
        pattern.add(new Byte[][]{
                {3,3,3,3,2},
                {3,3,3,2,3},
                {3,3,2,3,3},
                {3,2,3,3,3},
                {4,3,3,3,3}});
        pattern.add(new Byte[][]{{4,2,2,2,2}});
        pattern.add(new Byte[][]{
                {4},
                {2},
                {2},
                {2},
                {2}});
        pattern.add(new Byte[][]{
                {4,3,3,3,3},
                {3,2,3,3,3},
                {3,3,2,3,3},
                {3,3,3,2,3},
                {3,3,3,3,2}});
        pattern.add(new Byte[][]{
                {3,3,3,3,4},
                {3,3,3,2,3},
                {3,3,2,3,3},
                {3,2,3,3,3},
                {2,3,3,3,3}});
        pattern.add(new Byte[][]{{2,4,2,2,2,0}});
        pattern.add(new Byte[][]{
                {2},
                {4},
                {2},
                {2},
                {2},
                {0}});
        pattern.add(new Byte[][]{{3,3,3,3,3,2},
                {3,3,3,3,4,3},
                {3,3,3,2,3,3},
                {3,3,2,3,3,3},
                {3,2,3,3,3,3},
                {0,3,3,3,3,3}});
        pattern.add(new Byte[][]{
                {2,3,3,3,3,3},
                {3,4,3,3,3,3},
                {3,3,2,3,3,3},
                {3,3,3,2,3,3},
                {3,3,3,3,2,3},
                {3,3,3,3,3,0}});
        pattern.add(new Byte[][]{{2,2,2,4,2,0}});
        pattern.add(new Byte[][]{
                {2},
                {2},
                {2},
                {4},
                {2},
                {0}});
        pattern.add(new Byte[][]{{3,3,3,3,3,2},
                {3,3,3,3,2,3},
                {3,3,3,2,3,3},
                {3,3,4,3,3,3},
                {3,2,3,3,3,3},
                {0,3,3,3,3,3}});
        pattern.add(new Byte[][]{
                {2,3,3,3,3,3},
                {3,2,3,3,3,3},
                {3,3,2,3,3,3},
                {3,3,3,4,3,3},
                {3,3,3,3,2,3},
                {3,3,3,3,3,0}});
        // 3
        pattern.add(new Byte[][]{{0,2,2,2,4,0}});
        pattern.add(new Byte[][]{
                {0},
                {2},
                {2},
                {2},
                {4},
                {0}});
        pattern.add(new Byte[][]{
                {0,3,3,3,3,3},
                {3,2,3,3,3,3},
                {3,3,2,3,3,3},
                {3,3,3,2,3,3},
                {3,3,3,3,4,3},
                {3,3,3,3,3,0}});
        pattern.add(new Byte[][]{
                {3,3,3,3,3,0},
                {3,3,3,3,2,3},
                {3,3,3,2,3,3},
                {3,3,2,3,3,3},
                {3,4,3,3,3,3},
                {0,3,3,3,3,3}});
        pattern.add(new Byte[][]{{0,4,2,2,2,0}});
        pattern.add(new Byte[][]{
                {0},
                {4},
                {2},
                {2},
                {2},
                {0}});
        pattern.add(new Byte[][]{
                {3,3,3,3,3,0},
                {3,3,3,3,4,3},
                {3,3,3,2,3,3},
                {3,3,2,3,3,3},
                {3,2,3,3,3,3},
                {0,3,3,3,3,3}});
        pattern.add(new Byte[][]{
                {0,3,3,3,3,3},
                {3,4,3,3,3,3},
                {3,3,2,3,3,3},
                {3,3,3,2,3,3},
                {3,3,3,3,2,3},
                {3,3,3,3,3,0}});
        pattern.add(new Byte[][]{{0,2,4,2,2,0}});
        pattern.add(new Byte[][]{
                {0},
                {2},
                {4},
                {2},
                {2},
                {0}});
        pattern.add(new Byte[][]{
                {3,3,3,3,3,0},
                {3,3,3,3,2,3},
                {3,3,3,4,3,3},
                {3,3,2,3,3,3},
                {3,2,3,3,3,3},
                {0,3,3,3,3,3}});
        pattern.add(new Byte[][]{
                {0,3,3,3,3,3},
                {3,2,3,3,3,3},
                {3,3,4,3,3,3},
                {3,3,3,2,3,3},
                {3,3,3,3,2,3},
                {3,3,3,3,3,0}});
        pattern.add(new Byte[][]{{0,2,2,4,2,0}});
        pattern.add(new Byte[][]{
                {0},
                {2},
                {2},
                {4},
                {2},
                {0}});
        pattern.add(new Byte[][]{
                {3,3,3,3,3,0},
                {3,3,3,3,2,3},
                {3,3,3,2,3,3},
                {3,3,4,3,3,3},
                {3,2,3,3,3,3},
                {0,3,3,3,3,3}});
        pattern.add(new Byte[][]{
                {0,3,3,3,3,3},
                {3,2,3,3,3,3},
                {3,3,2,3,3,3},
                {3,3,3,4,3,3},
                {3,3,3,3,2,3},
                {3,3,3,3,3,0}});

        // 2
        pattern.add(new Byte[][]{
                {0,3,3,3},
                {3,2,3,3},
                {3,3,2,3},
                {3,3,3,4},
                {3,3,2,3},
                {3,2,3,3},
                {0,3,3,3}});
        pattern.add(new Byte[][]{{3,3,3,0},
                {3,3,2,3},
                {3,2,3,3},
                {4,3,3,3},
                {3,2,3,3},
                {3,3,2,3},
                {3,3,3,0}});
        pattern.add(new Byte[][]{
                {3,3,3,4,3,3,3},
                {3,3,2,3,2,3,3},
                {3,2,3,3,3,2,3},
                {0,3,3,3,3,3,0}});
        pattern.add(new Byte[][]{
                {0,3,3,3,3,3,0},
                {3,2,3,3,3,2,3},
                {3,3,2,3,2,3,3},
                {3,3,3,4,3,3,3}});
        pattern.add(new Byte[][]{
                {0,3,3,3},
                {2,3,3,3},
                {2,3,3,3},
                {4,2,2,0}}
        );
        pattern.add(new Byte[][]{
                {3,3,3,0},
                {3,3,3,2},
                {3,3,3,2},
                {0,2,2,4}}
        );
        pattern.add(new Byte[][]{
                {4,2,2,0},
                {2,3,3,3},
                {2,3,3,3},
                {0,3,3,3}}
        );
        pattern.add(new Byte[][]{
                {0,2,2,4},
                {3,3,3,2},
                {3,3,3,2},
                {3,3,3,0}});*/
    }

    /*protected void loadBrain() {
        try (
                FileInputStream fis = new FileInputStream(BRAIN_FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            pattern = (LinkedList) ois.readObject();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c){
            System.out.println("Class not found");
            c.printStackTrace();
        }
    }

    protected boolean saveBrain() {
        if (pattern == null) {
            return false;
        }
        try (
                FileOutputStream fos = new FileOutputStream(BRAIN_FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(pattern);
        } catch (IOException ioe) {
            ioe.getStackTrace();
            return false;
        }
        return true;
    }*/

    /*private List<Byte[][]> pattern = new LinkedList<Byte[][]>(){{
        pattern.add(new Byte[][]{{2,2,4,2,2}});
        pattern.add(new Byte[][]{
                {2},
                {2},
                {4},
                {2},
                {2}});
        pattern.add(new Byte[][]{
                {2,3,3,3,3},
                {3,2,3,3,3},
                {3,3,4,3,3},
                {3,3,3,2,3},
                {3,3,3,3,2}});
        pattern.add(new Byte[][]{
                {3,3,3,3,2},
                {3,3,3,2,3},
                {3,3,4,3,3},
                {3,2,3,3,3},
                {2,3,3,3,3}});
        pattern.add(new Byte[][]{{0,2,2,2,2,4}});
        pattern.add(new Byte[][]{
                {0},
                {2},
                {2},
                {2},
                {2},
                {4}});
        pattern.add(new Byte[][]{{3,3,3,3,3,0},
                {3,3,3,3,2,3},
                {3,3,3,2,3,3},
                {3,3,2,3,3,3},
                {3,2,3,3,3,3},
                {4,3,3,3,3,3}});
        pattern.add(new Byte[][]{
                {0,3,3,3,3,3},
                {3,2,3,3,3,3},
                {3,3,2,3,3,3},
                {3,3,3,2,3,3},
                {3,3,3,3,2,3},
                {3,3,3,3,3,4}});
        pattern.add(new Byte[][]{{2,2,2,2,4}});
        pattern.add(new Byte[][]{
                {2},
                {2},
                {2},
                {2},
                {4}});
        pattern.add(new Byte[][]{
                {2,3,3,3,3},
                {3,2,3,3,3},
                {3,3,2,3,3},
                {3,3,3,2,3},
                {3,3,3,3,4}});
        pattern.add(new Byte[][]{
                {3,3,3,3,2},
                {3,3,3,2,3},
                {3,3,2,3,3},
                {3,2,3,3,3},
                {4,3,3,3,3}});
        pattern.add(new Byte[][]{{4,2,2,2,2}});
        pattern.add(new Byte[][]{
                {4},
                {2},
                {2},
                {2},
                {2}});
        pattern.add(new Byte[][]{
                {4,3,3,3,3},
                {3,2,3,3,3},
                {3,3,2,3,3},
                {3,3,3,2,3},
                {3,3,3,3,2}});
        pattern.add(new Byte[][]{
                {3,3,3,3,4},
                {3,3,3,2,3},
                {3,3,2,3,3},
                {3,2,3,3,3},
                {2,3,3,3,3}});
        pattern.add(new Byte[][]{{2,4,2,2,2,0}});
        pattern.add(new Byte[][]{
                {2},
                {4},
                {2},
                {2},
                {2},
                {0}});
        pattern.add(new Byte[][]{{3,3,3,3,3,2},
                {3,3,3,3,4,3},
                {3,3,3,2,3,3},
                {3,3,2,3,3,3},
                {3,2,3,3,3,3},
                {0,3,3,3,3,3}});
        pattern.add(new Byte[][]{
                {2,3,3,3,3,3},
                {3,4,3,3,3,3},
                {3,3,2,3,3,3},
                {3,3,3,2,3,3},
                {3,3,3,3,2,3},
                {3,3,3,3,3,0}});
        pattern.add(new Byte[][]{{2,2,2,4,2,0}});
        pattern.add(new Byte[][]{
                {2},
                {2},
                {2},
                {4},
                {2},
                {0}});
        pattern.add(new Byte[][]{{3,3,3,3,3,2},
                {3,3,3,3,2,3},
                {3,3,3,2,3,3},
                {3,3,4,3,3,3},
                {3,2,3,3,3,3},
                {0,3,3,3,3,3}});
        pattern.add(new Byte[][]{
                {2,3,3,3,3,3},
                {3,2,3,3,3,3},
                {3,3,2,3,3,3},
                {3,3,3,4,3,3},
                {3,3,3,3,2,3},
                {3,3,3,3,3,0}});
        // 3
        pattern.add(new Byte[][]{{0,2,2,2,4,0}});
        pattern.add(new Byte[][]{
                {0},
                {2},
                {2},
                {2},
                {4},
                {0}});
        pattern.add(new Byte[][]{
                {0,3,3,3,3,3},
                {3,2,3,3,3,3},
                {3,3,2,3,3,3},
                {3,3,3,2,3,3},
                {3,3,3,3,4,3},
                {3,3,3,3,3,0}});
        pattern.add(new Byte[][]{
                {3,3,3,3,3,0},
                {3,3,3,3,2,3},
                {3,3,3,2,3,3},
                {3,3,2,3,3,3},
                {3,4,3,3,3,3},
                {0,3,3,3,3,3}});
        pattern.add(new Byte[][]{{0,4,2,2,2,0}});
        pattern.add(new Byte[][]{
                {0},
                {4},
                {2},
                {2},
                {2},
                {0}});
        pattern.add(new Byte[][]{
                {3,3,3,3,3,0},
                {3,3,3,3,4,3},
                {3,3,3,2,3,3},
                {3,3,2,3,3,3},
                {3,2,3,3,3,3},
                {0,3,3,3,3,3}});
        pattern.add(new Byte[][]{
                {0,3,3,3,3,3},
                {3,4,3,3,3,3},
                {3,3,2,3,3,3},
                {3,3,3,2,3,3},
                {3,3,3,3,2,3},
                {3,3,3,3,3,0}});
        pattern.add(new Byte[][]{{0,2,4,2,2,0}});
        pattern.add(new Byte[][]{
                {0},
                {2},
                {4},
                {2},
                {2},
                {0}});
        pattern.add(new Byte[][]{
                {3,3,3,3,3,0},
                {3,3,3,3,2,3},
                {3,3,3,4,3,3},
                {3,3,2,3,3,3},
                {3,2,3,3,3,3},
                {0,3,3,3,3,3}});
        pattern.add(new Byte[][]{
                {0,3,3,3,3,3},
                {3,2,3,3,3,3},
                {3,3,4,3,3,3},
                {3,3,3,2,3,3},
                {3,3,3,3,2,3},
                {3,3,3,3,3,0}});
        pattern.add(new Byte[][]{{0,2,2,4,2,0}});
        pattern.add(new Byte[][]{
                {0},
                {2},
                {2},
                {4},
                {2},
                {0}});
        pattern.add(new Byte[][]{
                {3,3,3,3,3,0},
                {3,3,3,3,2,3},
                {3,3,3,2,3,3},
                {3,3,4,3,3,3},
                {3,2,3,3,3,3},
                {0,3,3,3,3,3}});
        pattern.add(new Byte[][]{
                {0,3,3,3,3,3},
                {3,2,3,3,3,3},
                {3,3,2,3,3,3},
                {3,3,3,4,3,3},
                {3,3,3,3,2,3},
                {3,3,3,3,3,0}});

        // 2
        pattern.add(new Byte[][]{
                {0,3,3,3},
                {3,2,3,3},
                {3,3,2,3},
                {3,3,3,4},
                {3,3,2,3},
                {3,2,3,3},
                {0,3,3,3}});
        pattern.add(new Byte[][]{{3,3,3,0},
                {3,3,2,3},
                {3,2,3,3},
                {4,3,3,3},
                {3,2,3,3},
                {3,3,2,3},
                {3,3,3,0}});
        pattern.add(new Byte[][]{
                {3,3,3,4,3,3,3},
                {3,3,2,3,2,3,3},
                {3,2,3,3,3,2,3},
                {0,3,3,3,3,3,0}});
        pattern.add(new Byte[][]{
                {0,3,3,3,3,3,0},
                {3,2,3,3,3,2,3},
                {3,3,2,3,2,3,3},
                {3,3,3,4,3,3,3}});
        pattern.add(new Byte[][]{
                {0,3,3,3},
                {2,3,3,3},
                {2,3,3,3},
                {4,2,2,0}}
        );
        pattern.add(new Byte[][]{
                {3,3,3,0},
                {3,3,3,2},
                {3,3,3,2},
                {0,2,2,4}}
        );
        pattern.add(new Byte[][]{
                {4,2,2,0},
                {2,3,3,3},
                {2,3,3,3},
                {0,3,3,3}}
        );
        pattern.add(new Byte[][]{
                {0,2,2,4},
                {3,3,3,2},
                {3,3,3,2},
                {3,3,3,0}});
    }};*/
}
