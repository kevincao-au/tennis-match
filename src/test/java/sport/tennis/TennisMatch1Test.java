package sport.tennis;

import org.junit.jupiter.api.*;
import sport.ScoringService;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TennisMatch1Test {
    static ScoringService tennisGame1;
    static Player player1;
    static Player player2;


    @BeforeAll
    static void setupPlayersAndGame() {
        player1 = new Player("Federer");
        player2 = new Player("Nadal");
        tennisGame1 = new Game(player1, player2);

    }

    @Test
    @Order(1)
    void test40_15() {
        tennisGame1.pointWonBy(player2);
        tennisGame1.pointWonBy(player1);
        tennisGame1.pointWonBy(player1);
        tennisGame1.pointWonBy(player1);
        assertEquals("40 - 15", tennisGame1.displayScore());
    }

    @Test
    @Order(2)
    void testDeuce1() {
        tennisGame1.pointWonBy(player2);
        tennisGame1.pointWonBy(player2);
        tennisGame1.pointWonBy(player1);
        tennisGame1.pointWonBy(player2);
        assertEquals("Deuce", tennisGame1.displayScore());
    }

    @Test
    @Order(3)
    void testDeuce2() {
        tennisGame1.pointWonBy(player1);
        tennisGame1.pointWonBy(player2);
        tennisGame1.pointWonBy(player1);
        tennisGame1.pointWonBy(player2);
        assertEquals("Deuce", tennisGame1.displayScore());
    }

    @Test
    @Order(4)
    void testAdvantage_1() {
        tennisGame1.pointWonBy(player1);
        assertEquals("Advantage Federer", tennisGame1.displayScore());
    }

    @Test
    @Order(5)
    void testOne_Zero() {
        tennisGame1.pointWonBy(player1);
        assertEquals("1 - 0", tennisGame1.displayScore());
    }

    @Test
    @Order(6)
    void testOne_Zero_30_30() {
        tennisGame1.pointWonBy(player1);
        tennisGame1.pointWonBy(player2);
        tennisGame1.pointWonBy(player2);
        tennisGame1.pointWonBy(player1);
        assertEquals("1 - 0, 30 - 30", tennisGame1.displayScore());
    }

    @Test
    @Order(7)
    void testOne_Zero_40_40() {
        tennisGame1.pointWonBy(player1);
        tennisGame1.pointWonBy(player2);
        assertEquals("1 - 0, Deuce", tennisGame1.displayScore());
    }

    @Test
    @Order(7)
    void testOne_Zero_Deuce() {
        tennisGame1.pointWonBy(player2);
        tennisGame1.pointWonBy(player1);
        assertEquals("1 - 0, Deuce", tennisGame1.displayScore());
    }

    @Test
    @Order(7)
    void testOne_Zero_Advantage_2() {
        tennisGame1.pointWonBy(player2);
        assertEquals("1 - 0, Advantage Nadal", tennisGame1.displayScore());
    }

    @Test
    @Order(8)
    void testOne_Zero_Deuce2() {
        tennisGame1.pointWonBy(player1);
        assertEquals("1 - 0, Deuce", tennisGame1.displayScore());
    }

    @Test
    @Order(9)
    void testOne_One() {
        tennisGame1.pointWonBy(player2);
        tennisGame1.pointWonBy(player2);
        assertEquals("1 - 1", tennisGame1.displayScore());
    }

    @Test
    @Order(10)
    void testOne_One_30_40() {
        tennisGame1.pointWonBy(player2);
        tennisGame1.pointWonBy(player2);
        tennisGame1.pointWonBy(player1);
        tennisGame1.pointWonBy(player2);
        tennisGame1.pointWonBy(player1);;
        assertEquals("1 - 1, 30 - 40", tennisGame1.displayScore());
    }

    @Test
    @Order(11)
    void testOne_One_40_40() {
        tennisGame1.pointWonBy(player1);;
        assertEquals("1 - 1, Deuce", tennisGame1.displayScore());
    }

    @Test
    @Order(12)
    void testTwo_One() {
        IntStream.rangeClosed(1, 3).forEach(value -> {
            tennisGame1.pointWonBy(player1);
        });
        assertEquals("2 - 1", tennisGame1.displayScore());
    }

    @Test
    @Order(13)
    void testTwo_One_15_15() {
        tennisGame1.pointWonBy(player1);
        tennisGame1.pointWonBy(player2);
        assertEquals("2 - 1, 15 - 15", tennisGame1.displayScore());
    }

    @Test
    @Order(14)
    void testTwo_One_15_40() {
        IntStream.rangeClosed(1, 2).forEach(value -> {
            tennisGame1.pointWonBy(player2);
        });
        assertEquals("2 - 1, 15 - 40", tennisGame1.displayScore());
    }

    @Test
    @Order(15)
    void testTwo_Two() {
        tennisGame1.pointWonBy(player2);
        assertEquals("2 - 2", tennisGame1.displayScore());
    }

    @Test
    @Order(16)
    void testTwo_Two_40_30() {
        IntStream.rangeClosed(1, 3).forEach(value -> {
            tennisGame1.pointWonBy(player1);
        });

        IntStream.rangeClosed(1, 2).forEach(value -> {
            tennisGame1.pointWonBy(player2);
        });

        assertEquals("2 - 2, 40 - 30", tennisGame1.displayScore());
    }

    @Test
    @Order(17)
    void testTwo_Two_Deuce() {
        tennisGame1.pointWonBy(player2);
        assertEquals("2 - 2, Deuce", tennisGame1.displayScore());
    }

    @Test
    @Order(18)
    void testThree_Two() {
        tennisGame1.pointWonBy(player1);
        tennisGame1.pointWonBy(player1);
        assertEquals("3 - 2", tennisGame1.displayScore());
    }

    @Test
    @Order(19)
    void testThree_Two_40_0() {
        IntStream.rangeClosed(1, 3).forEach(value -> {
            tennisGame1.pointWonBy(player1);
        });
        assertEquals("3 - 2, 40 - 0", tennisGame1.displayScore());
    }

    @Test
    @Order(20)
    void testFour_Two() {
        tennisGame1.pointWonBy(player1);
        assertEquals("4 - 2", tennisGame1.displayScore());
    }

    @Test
    @Order(21)
    void testFour_Two_0_40() {
        IntStream.rangeClosed(1, 3).forEach(value -> {
            tennisGame1.pointWonBy(player2);
        });
        assertEquals("4 - 2, 0 - 40", tennisGame1.displayScore());
    }

    @Test
    @Order(22)
    void testFour_Two_Deuce() {
        IntStream.rangeClosed(1, 3).forEach(value -> {
            tennisGame1.pointWonBy(player1);
        });
        assertEquals("4 - 2, Deuce", tennisGame1.displayScore());
    }

    @Test
    @Order(23)
    void testFour_Three() {
        IntStream.rangeClosed(1, 2).forEach(value -> {
            tennisGame1.pointWonBy(player2);
        });
        assertEquals("4 - 3", tennisGame1.displayScore());
    }

    @Test
    @Order(24)
    void testFour_Three_30_40() {
        IntStream.rangeClosed(1, 2).forEach(value -> {
            tennisGame1.pointWonBy(player1);
        });
        IntStream.rangeClosed(1, 3).forEach(value -> {
            tennisGame1.pointWonBy(player2);
        });

        assertEquals("4 - 3, 30 - 40", tennisGame1.displayScore());
    }

    @Test
    @Order(25)
    void testFour_Four() {
        tennisGame1.pointWonBy(player2);
        assertEquals("4 - 4", tennisGame1.displayScore());
    }

    @Test
    @Order(26)
    void testFour_Four_0_40() {
        IntStream.rangeClosed(1, 3).forEach(value -> {
            tennisGame1.pointWonBy(player2);
        });
        assertEquals("4 - 4, 0 - 40", tennisGame1.displayScore());
    }

    @Test
    @Order(27)
    void testFour_Four_Deuce() {
        IntStream.rangeClosed(1, 3).forEach(value -> {
            tennisGame1.pointWonBy(player1);
        });
        assertEquals("4 - 4, Deuce", tennisGame1.displayScore());
    }

    @Test
    @Order(28)
    void testFour_Five() {
        tennisGame1.pointWonBy(player2);
        tennisGame1.pointWonBy(player2);
        assertEquals("4 - 5", tennisGame1.displayScore());
    }

    @Test
    @Order(29)
    void testFour_Five_15_40() {
        tennisGame1.pointWonBy(player1);

        IntStream.rangeClosed(1, 3).forEach(value -> {
            tennisGame1.pointWonBy(player2);
        });
        assertEquals("4 - 5, 15 - 40", tennisGame1.displayScore());
    }


    @Test
    @Order(30)
    void testFour_Five_Deuce() {
        IntStream.rangeClosed(1, 2).forEach(value -> {
            tennisGame1.pointWonBy(player1);
        });
        assertEquals("4 - 5, Deuce", tennisGame1.displayScore());
    }

    @Test
    @Order(31)
    void testFour_Six_Nadal_Won() {
        tennisGame1.pointWonBy(player2);
        tennisGame1.pointWonBy(player2);
        assertEquals("4 - 6, Nadal wins!", tennisGame1.displayScore());
    }
}