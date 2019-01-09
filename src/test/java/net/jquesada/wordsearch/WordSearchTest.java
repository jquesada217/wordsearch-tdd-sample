package net.jquesada.wordsearch;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WordSearchTest {

    private WordSearch grid;

    @Before
    public void setUp() {
        grid = new WordSearch(Arrays.asList(
                "fridayar",
                "orecluwe",
                "oredrotw",
                "tuollafo",
                "brelaiet",
                "atipltri",
                "lpieoatr",
                "lostdrle"
        ));
    }

    @Test
    public void find_locatesRightWord() {
        // Act
        List<Point> result = grid.find("friday");

        // Assert
        assertThat(result, hasSize(6));
    }

    @Test
    public void find_locatesLeftWord() {
        // Act
        List<Point> result = grid.find("fallout");

        // Assert
        assertThat(result, hasSize(7));
    }

    @Test
    public void find_locatesDownWord() {
        // Act
        List<Point> result = grid.find("football");

        // Assert
        assertThat(result, hasSize(8));
    }

    @Test
    public void find_locatesUpWord() {
        // Act
        List<Point> result = grid.find("tower");

        // Assert
        assertThat(result, hasSize(5));
    }

    @Test
    public void find_locatesDownRightWord() {
        // Act
        List<Point> result = grid.find("loft");

        // Assert
        assertThat(result, hasSize(4));
    }

    @Test
    public void find_locatesUpRightWord() {
        // Act
        List<Point> result = grid.find("pillow");

        // Assert
        assertThat(result, hasSize(6));
    }

    @Test
    public void find_locatesDownLeftWord() {
        // Act
        List<Point> result = grid.find("files");

        // Assert
        assertThat(result, hasSize(5));
    }

    @Test
    public void find_locatesUpLeftWord() {
        // Act
        List<Point> result = grid.find("fold");

        // Assert
        assertThat(result, hasSize(4));
    }

    @Test
    public void find_failsToLocateWord() {
        // Act
        List<Point> result = grid.find("queen");

        // Assert
        assertThat(result, hasSize(0));
    }

    @Test
    public void testMatch_verifiesRightMatch() {
        // Arrange
        List<Point> coordinates = Arrays.asList(
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 2),
                new Point(0, 3),
                new Point(0, 4),
                new Point(0, 5)
        );

        // Act
        boolean result = grid.testMatch("friday", coordinates);

        // Assert
        assertThat(result, is(true));
    }

    @Test
    public void testMatch_verifiesLeftMatch() {
        // Arrange
        List<Point> coordinates = Arrays.asList(
                new Point(3, 6),
                new Point(3, 5),
                new Point(3, 4),
                new Point(3, 3),
                new Point(3, 2),
                new Point(3, 1),
                new Point(3, 0)
        );

        // Act
        boolean result = grid.testMatch("fallout", coordinates);

        // Assert
        assertThat(result, is(true));
    }

    @Test
    public void testMatch_verifiesDownMatch() {
        // Arrange
        List<Point> coordinates = Arrays.asList(
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0),
                new Point(5, 0),
                new Point(6, 0),
                new Point(7, 0)
        );

        // Act
        boolean result = grid.testMatch("football", coordinates);

        // Assert
        assertThat(result, is(true));
    }

    @Test
    public void testMatch_verifiesUpMatch() {

    }

    @Test
    public void testMatch_verifiesDownRightMatch() {
        // Arrange
        List<Point> coordinates = Arrays.asList(
                new Point(1, 4),
                new Point(2, 5),
                new Point(3, 6),
                new Point(4, 7)
        );

        // Act
        boolean result = grid.testMatch("loft", coordinates);

        // Assert
        assertThat(result, is(true));
    }

    @Test
    public void testMatch_verifiesUpRightMatch() {
        // Arrange
        List<Point> coordinates = Arrays.asList(
                new Point(6, 1),
                new Point(5, 2),
                new Point(4, 3),
                new Point(3, 4),
                new Point(2, 5),
                new Point(1, 6)
        );

        // Act
        boolean result = grid.testMatch("pillow", coordinates);

        // Assert
        assertThat(result, is(true));
    }

    @Test
    public void testMatch_verifiesDownLeftMatch() {
        // Arrange
        List<Point> coordinates = Arrays.asList(
                new Point(3, 6),
                new Point(4, 5),
                new Point(5, 4),
                new Point(6, 3),
                new Point(7, 2),
                new Point(0, 1)
        );

        // Act
        boolean result = grid.testMatch("files", coordinates);

        // Assert
        assertThat(result, is(true));
    }

    @Test
    public void testMatch_verifiesUpLeftMatch() {
        // Arrange
        List<Point> coordinates = Arrays.asList(
                new Point(3, 6),
                new Point(2, 5),
                new Point(1, 4),
                new Point(0, 3)
        );

        // Act
        boolean result = grid.testMatch("fold", coordinates);

        // Assert
        assertThat(result, is(true));
    }

    @Test
    public void testMatch_outOfBoundsIsNonMatch() {
        // Arrange
        List<Point> coordinates = Arrays.asList(
                new Point(1, 6),
                new Point(1, 7),
                new Point(1, 8),
                new Point(1, 9),
                new Point(1, 10),
                new Point(1, 11),
                new Point(1, 12)
        );

        // Act
        boolean result = grid.testMatch("welding", coordinates);

        // Assert
        assertThat(result, is(false));
    }

    @Test
    public void getSearchPointCandidates_findsTwoResults() {
        List<Point> searchCandidates = grid.getSearchPointCandidates("weird");
        List<Point> expectedPoints = Arrays.asList(
                new Point(1, 6),
                new Point(2, 7)
        );

        assertThat(searchCandidates, is(expectedPoints));

    }
}