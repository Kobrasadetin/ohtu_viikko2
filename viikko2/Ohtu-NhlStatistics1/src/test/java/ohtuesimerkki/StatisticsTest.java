/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mkarjanm
 */
public class StatisticsTest {

    Statistics instance;

    Reader readerStub = new Reader() {

        @Override
        public List<Player> getPlayers() {
            ArrayList players = new ArrayList();
            players.add(new Player("Name1", "Team1", 12, 15));
            players.add(new Player("Name2", "Team1", 5, 10));
            players.add(new Player("Name3", "Team2", 3, 3));
            return players;
        }

    };
        
    static boolean isEqual(List<Player> a, List<Player> b) {
        if (a.size() != b.size()) {
            return false;
        }
        int index = 0;
        for (Player playerA : a) {
            if (playerA.getName() != b.get(index).getName() || playerA.getTeam() != b.get(index).getTeam()) {
                if (playerA.getGoals() != b.get(index).getGoals() || playerA.getPoints() != b.get(index).getPoints()) {
                    return false;
                }
            }
            index++;
        }
        return true;
    }

    public StatisticsTest() {
    }

    @Before
    public void setUp() {
        instance = new Statistics(readerStub);
    }

    @Test
    public void testSearch() {
        System.out.println("search");
        String name = "Name1";
        Player result = instance.search(name);
        assertEquals("Name1", result.getName());
        name = "Name2";
        result = instance.search(name);
        assertEquals("Name2", result.getName());
        assertEquals(5, result.getGoals());
        name = "Kekkonen";
        result = instance.search(name);
        assertEquals(null, result);
    }

    @Test
    public void testTeam() {
        System.out.println("team");
        String teamName = "Team1";
        List expected = new ArrayList();
        expected.add(new Player("Name1", "Team1", 12, 15));
        expected.add(new Player("Name2", "Team1", 5, 10));
        List<Player> expResult = expected;
        List<Player> result = instance.team(teamName);
        assertTrue(isEqual(expResult, result));
    }

    @Test
    public void testTopScorers() {
        System.out.println("topScorers");
        int howMany = 1;
        List expected = new ArrayList();
        expected.add(new Player("Name1", "Team1", 12, 15));
        expected.add(new Player("Name2", "Team1", 5, 10));
        List<Player> expResult = expected;
        List<Player> result = instance.topScorers(howMany);
        assertTrue(isEqual(expResult, result));
    }

}
