package fun.connor.cafe.domain.score;

import fun.connor.cafe.domain.CafeVisit;

import java.util.List;

public class SimpleScoreCalculator implements ScoreCalculator {
    @Override
    public Score calculateScore(List<CafeVisit> visits) {
        return new Score(visits.size());
    }

    @Override
    public Score single(CafeVisit visit) {
        return new Score(1);
    }
}
