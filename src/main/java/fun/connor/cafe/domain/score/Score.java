package fun.connor.cafe.domain.score;

/**
 * A comparable score, measured in points
 */
public class Score implements Comparable<Score> {
    private final int score;

    /**
     * Construct a new score with the given value
     * @param score the score in points
     */
    public Score(int score) {
        this.score = score;
    }

    /**
     * @return the score in points
     */
    public int getScore() {
        return score;
    }


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Score)) return false;
        return compareTo((Score) other) == 0;
    }

    @Override
    public int hashCode() {
        return score;
    }

    @Override
    public int compareTo(Score o) {
        return score - o.score;
    }
}
