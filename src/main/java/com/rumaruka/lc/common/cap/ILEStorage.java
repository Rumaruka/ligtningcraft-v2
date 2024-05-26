package com.rumaruka.lc.common.cap;

public interface ILEStorage {


    int getMaxLE();
    int getLE();
    int additionLE(int amount);
    int setLE(int amount);
    int usingLE(int amount);
    boolean hasLE();
}
