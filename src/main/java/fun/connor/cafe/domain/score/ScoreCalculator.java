package fun.connor.cafe.domain.score;

import fun.connor.cafe.domain.CafeVisit;

import java.util.List;

public interface ScoreCalculator {

    Score calculateScore(List<CafeVisit> visits);
    Score single(CafeVisit visit);
}
