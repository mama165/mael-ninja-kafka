package fr.takima.cdb.model;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum Orientation {
    ASCENDING("ASC"),
    DESCENDING("DESC");

    private final String symbol;

    Orientation(String symbol) {
        this.symbol = symbol;
    }


    /**
     * The map with key = orientation contraction and
     * its specified value is the enum type itself
     */
    private static final Map<String, Orientation> stringToEnum =
            Stream.of(values()).collect(toMap(Orientation::getSymbol, e -> e));


    /** Get the orientation enum type from its contraction
     *
     * @param symbol orientation contraction
     * @return the orientation enum type
     */
    public static Orientation fromString(String symbol) {
        return stringToEnum.get(symbol);
    }


    // ACCESSOR METHODS

    /**
     * @return the orientation's contraction
     */
    public String getSymbol() {
        return symbol;
    }
}
