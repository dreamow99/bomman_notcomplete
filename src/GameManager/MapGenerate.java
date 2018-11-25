package GameManager;

import Objects.*;
import Objects.Character.Enemy1;
import Objects.Character.Player;
import Objects.Object;
import Screens.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapGenerate {
    private int level;
    private int row;
    private int collumn;

    public MapGenerate(Map map, Board board){
        File inFile = new File("./assets/map/map.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(inFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        level = sc.nextInt();
        row = sc.nextInt();
        collumn = sc.nextInt();
        map.objectList = new ArrayList<Object>();
        for (int i = 1; i <= row+1; i++){
            String s = sc.nextLine();
            if(s.length() < collumn) continue;
            for(int j = 0; j < collumn; j++){
                char c = s.charAt(j);
                //System.out.println(i + " " + j);
                map.add(new Ground(j*36, (i-2)*36));
                if (c == '#')  map.add(new FixedWall(j*36, (i-2)*36));
                else if (c == '*') map.add(new Crate(j*36, (i-2)*36));
                else if (c == 'x') {map.add(new Portal(j*36, (i-2)*36)); map.add(new Crate(j*36, (i-2)*36));}
                else if (c == 'p') map.add(new Player(j*36, (i-2)*36, board));
                else if (c == '1') map.add(new Enemy1(j*36 , (i-2)*36, board));

            }
        }
    }



    public int getRow(){ return this.row; }
    public int getCollumn() { return this.collumn; }
}

