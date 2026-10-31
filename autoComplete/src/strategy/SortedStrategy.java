package strategy;

import java.util.List;
import java.util.stream.Collectors;

public class SortedStrategy implements strategy{
    @Override
    public List<String> prefixStrategy(List<String> words) {
        if (words == null) {
            return List.of();
        }
        return words.stream()
                    .sorted()
                    .collect(Collectors.toList());
    }
}