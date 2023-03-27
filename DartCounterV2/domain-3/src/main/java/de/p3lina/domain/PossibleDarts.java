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

    Zero(0),
    S1(1),
    S2(2),
    S3(3),
    S4(4),
    S5(5),
    S6(6),
    S7(7),
    S8(8),
    S9(9),
    S10(10),
    S11(11),
    S12(12),
    S13(13),
    S14(14),
    S15(15),
    S16(16),
    S17(17),
    S18(18),
    S19(19),
    S20(20),
    D1(2),
    D2(4),
    D3(6),
    D4(8),
    D5(10),
    D6(12),
    D7(14),
    D8(16),
    D9(18),
    D10(20),
    D11(22),
    D12(24),
    D13(26),
    D14(28),
    D15(30),
    D16(32),
    D17(34),
    D18(36),
    D19(38),
    D20(40),
    T1(3),
    T2(6),
    T3(9),
    T4(12),
    T5(15),
    T6(18),
    T7(21),
    T8(24),
    T9(27),
    T10(30),
    T11(33),
    T12(36),
    T13(39),
    T14(42),
    T15(45),
    T16(48),
    T17(51),
    T18(54),
    T19(57),
    T20(60),
    SBull(25),
    DBull(50);


    @NonNull
    public int points;
}
