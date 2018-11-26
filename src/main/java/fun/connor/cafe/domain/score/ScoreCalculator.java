package fun.connor.cafe.domain.score;

import fun.connor.cafe.domain.CafeVisit;

import java.util.List;


/**
 * Interface for visit score calculation. Since score calculation
 * need not be persisted, this interface provides methods for calculating
 * the score of a sequence of {@link CafeVisit}s and a single {@link CafeVisit}.
 *
 * Some scoring strategies may score visits differently in sequence than individually.
 */
public interface ScoreCalculator {

    /**
     * Calculate the score for the given sequence of visits
     * @param visits the visits to score
     * @return the score for the visits
     */
    Score calculateScore(List<CafeVisit> visits);

    /**
     * Calculate the score for the given visit in a vacuum
     * @param visit the visit to score
     * @return the score
     */
    Score calculateSingleScore(CafeVisit visit);
}
