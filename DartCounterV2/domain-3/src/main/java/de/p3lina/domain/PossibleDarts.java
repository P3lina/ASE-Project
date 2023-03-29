package de.p3lina.domain;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/** Ensures that no other darts than those described here can be thrown
 * @author P3lina
 * @version 1.0.0-SNAPSHOT
 * @since 1.0.0-SNAPSHOT
 */
@RequiredArgsConstructor
public enum PossibleDarts {



    Zero(0, false),
    S1(1, false),
    S2(2, false),
    S3(3, false),
    S4(4, false),
    S5(5, false),
    S6(6, false),
    S7(7, false),
    S8(8, false),
    S9(9, false),
    S10(10, false),
    S11(11, false),
    S12(12, false),
    S13(13, false),
    S14(14, false),
    S15(15, false),
    S16(16, false),
    S17(17, false),
    S18(18, false),
    S19(19, false),
    S20(20, false),
    D1(2, true),
    D2(4, true),
    D3(6, true),
    D4(8, true),
    D5(10, true),
    D6(12, true),
    D7(14, true),
    D8(16, true),
    D9(18, true),
    D10(20, true),
    D11(22, true),
    D12(24, true),
    D13(26, true),
    D14(28, true),
    D15(30, true),
    D16(32, true),
    D17(34, true),
    D18(36, true),
    D19(38, true),
    D20(40, true),
    T1(3, false),
    T2(6, false),
    T3(9, false),
    T4(12, false),
    T5(15, false),
    T6(18, false),
    T7(21, false),
    T8(24, false),
    T9(27, false),
    T10(30, false),
    T11(33, false),
    T12(36, false),
    T13(39, false),
    T14(42, false),
    T15(45, false),
    T16(48, false),
    T17(51, false),
    T18(54, false),
    T19(57, false),
    T20(60, false),
    SBull(25, false),
    DBull(50, true);


    @NonNull
    public int points;
    @NonNull
    public boolean isDouble;

    public static PossibleDarts getPossibleDartsByName(String name) {
        PossibleDarts possibleDarts = valueOf(name);
        return valueOf(name);
    }

}
