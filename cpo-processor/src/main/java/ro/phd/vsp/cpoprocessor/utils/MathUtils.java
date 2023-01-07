package ro.phd.vsp.cpoprocessor.utils;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MathUtils {

    public static double computeStandardDeviation(List<Double> values) {
    double stddev = values.stream()
        .map(d -> Math.pow(d - computeMean(values), 2))
        .mapToDouble(Double::doubleValue)
        .sum();

    return Math.sqrt(stddev / values.size());
  }

  public static double computeMean(List<Double> values) {
    return values.stream().mapToDouble(Double::doubleValue).sum() / values.size();
  }

}
