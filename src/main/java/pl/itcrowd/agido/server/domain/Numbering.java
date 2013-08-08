package pl.itcrowd.agido.server.domain;

import java.util.ArrayList;
import java.util.List;

public class Numbering {

    private List<Integer> levelNumbers = new ArrayList<Integer>();

    public Numbering()
    {
        this.levelDown();
    }

    public void levelUp()
    {
        levelNumbers.remove(levelNumbers.size() - 1);
    }

    public void increment()
    {
        final int lastIndex = levelNumbers.size() - 1;
        levelNumbers.set(lastIndex, levelNumbers.get(lastIndex) + 1);
    }

    public void levelDown()
    {
        levelNumbers.add(0);
    }

    public String toString()
    {
        final StringBuilder stringBuilder = new StringBuilder();
        for (Integer number : levelNumbers) {
            stringBuilder.append(number).append(".");
        }
        return stringBuilder.length() > 0 ? stringBuilder.substring(0, stringBuilder.length() - 1) : stringBuilder.toString();
    }

    public Integer getNumber(){
        return levelNumbers.get(levelNumbers.size()-1);
    }

    public void startFromZero(){
        levelNumbers.clear();
        levelNumbers.add(0);
    }
}
