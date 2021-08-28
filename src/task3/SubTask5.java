package task3;

import java.util.*;

class Player implements Comparable
{
    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Player(String _name, int _score)
    {
        this.name = _name;
        this.score = _score;
    }

    @Override
    public int compareTo(Object o) {
        if(((Player)o).getScore() < score) return -1;
        else if(((Player)o).getScore() > score) return 1;
        else return 0;
    }
}

public class SubTask5
{

    public static void main(String[] args) {
        String [] input =
               new String[] {
                        "Ivan 5", "Petr 3", "Alex 10",
                        "Petr 8", "Ivan 6", "Alex 5",
                        "Ivan 1", "Petr 5", "Alex 1"
                };
        SortedSet<Player> set = new TreeSet<>();

        for (String curPlayer:
             input) {
            set.add(
                    new Player(
                            curPlayer.split(" ")[0],
                            Integer.parseInt(curPlayer.split(" ")[1])
                    )
            );
        }

        set.add(new Player("Iva1", 1));
        set.add(new Player("Iva2", 2));
        set.add(new Player("Iva3", 3));
        set.add(new Player("GriIva3", 3));


        System.out.println("Winner: " + set.first().getName() + "\nMaxScores: " + set.first().getScore());
    }
}
