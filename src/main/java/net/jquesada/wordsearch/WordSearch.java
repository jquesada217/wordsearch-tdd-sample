package net.jquesada.wordsearch;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class WordSearch {

    private char[][] characters;

    private static final List<Point> SEARCH_DIRECTIONS = Arrays.asList(
            new Point(0, 1),    // east
            new Point(1, 1),    // southeast
            new Point(1, 0),    // south
            new Point(1, -1),   // southwest
            new Point(0, -1),   // west
            new Point(-1, -1),  // northwest
            new Point(-1, 0),   // north
            new Point (-1, 1)   // northeast
    );

    public WordSearch(List<String> lines) {
        characters = new char[lines.size()][];

        for (int line = 0, numLines = lines.size(); line < numLines; line++) {
            String lineText = lines.get(line).replaceAll(",", "");
            characters[line] = lineText.toCharArray();
        }
    }

    public List<Point> find(CharSequence word) {

        List<Point> searchPoints = getSearchPointCandidates(word);
        for (Point point : searchPoints) {
            for (Point directionVector : SEARCH_DIRECTIONS) {
                List<Point> coordinates = getCoordinateSequence(point, word.length(), directionVector);
                if (testMatch(word, coordinates)) {
                    String coordStr = coordinates.stream()
                            .map(pt -> String.format("(%d, %d)", pt.x, pt.y))
                            .collect(Collectors.joining(", "));
                    System.out.println(word + ": " + coordStr);

                    return coordinates;
                }
            }
        }

        System.out.println(word + ": Not Found");
        return Collections.emptyList();
    }

    public List<Point> getCoordinateSequence(Point startingPoint, int length, Point direction) {
        List<Point> coordinates = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            coordinates.add(new Point(startingPoint.x + i * direction.x, startingPoint.y + i * direction.y));
        }

        return coordinates;
    }

    public boolean testMatch(CharSequence word, List<Point> coordinates) {
        try {
            for (int i = 0, j = word.length(); i < j; i++) {
                Point p = coordinates.get(i);

                if (Character.toUpperCase(characters[p.x][p.y]) != Character.toUpperCase(word.charAt(i))) {
                    return false;
                }
            }

            return true;
        } catch (ArrayIndexOutOfBoundsException ex) {
            return false;
        }
    }

    public List<Point> getSearchPointCandidates(CharSequence word) {

        char firstLetter = word.toString().toUpperCase().charAt(0);
        List<Point> candidates = new LinkedList<>();

        for (int line = 0, numLines = characters.length; line < numLines; line++) {
            for (int col = 0, numCols = characters[line].length; col < numCols; col++) {
                if (Character.toUpperCase(characters[line][col]) == firstLetter) {
                    candidates.add(new Point(line, col));
                }
            }
        }

        return candidates;
    }
}
