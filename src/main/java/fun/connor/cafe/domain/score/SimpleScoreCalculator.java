package fun.connor.cafe.domain.score;

import fun.connor.cafe.domain.CafeVisit;

import java.util.List;

/**
 * Basic implementation of {@link ScoreCalculator}. This scoring strategy does not treat
 * sequences differently than individual visits. Every visit is assigned a single point of
 * score.
 *
 * See {@link ScoreCalculator}.
 */
public class SimpleScoreCalculator implements ScoreCalculator {

    @Override
    public Score calculateScore(List<CafeVisit> visits) {
        return new Score(visits.size());
    }

    @Override
    public Score calculateSingleScore(CafeVisit visit) {
        return new Score(1);
    }
}
