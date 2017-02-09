package engine2d.behaviour;
/**
 * Created by faisal1 on 08/02/2017
 */

import java.util.Arrays;
import java.util.Random;

public class LevelGenerator
{

    Random rand;
    private int[] usedRooms = new int[7];
    private int roomsGenerated = 1;
    //TODO: UPDATE THIS ACCORDINGLY
    public static final int NUM_ROOMS = 3;
    //Subject to change
    //TODO: SET NUMBER OF BOSSROOMS
    public static final int NUM_BOSSROOMS = 1;
    public static final int BOSS = 95;
    public static final int START_ROOM = 0;
    private int numRooms;

    public LevelGenerator(int numRooms)
    {
        rand = new Random();
        this.numRooms = numRooms;
    }

    //Returns a 2Dimensional array
    //First int is the number of the room to be added, second number represents the direction of the NEXT room
    public int[] genLevel()
    {
        for(int r : usedRooms)
        {
            r = 0;
        }
        int[] rooms = new int[numRooms];
        rooms[0] = START_ROOM;
        for (int x = 1; x < numRooms - 1; x++)
        {
            rooms[x] = getRoomNumber();
        }
        rooms[numRooms - 1] = getBossRoomNumber();
        return rooms;
    }

    private int getRoomNumber()
    {
        int rInt = rand.nextInt(NUM_ROOMS);
        while (Arrays.asList(usedRooms).contains(rInt))
        {
            rInt = rand.nextInt(NUM_ROOMS);
        }
        usedRooms[roomsGenerated] = rInt;
        roomsGenerated++;
        return rInt;
    }

    private int getBossRoomNumber(){

        int rInt = rand.nextInt(NUM_BOSSROOMS);
        while (Arrays.asList(usedRooms).contains(rInt))
        {
            rInt = rand.nextInt(NUM_ROOMS);
        }
        usedRooms[roomsGenerated] = rInt;
        roomsGenerated++;
        return rInt+95;

    }

    private int getRoomDirection()
    {
        return rand.nextInt(4);
    }

}
