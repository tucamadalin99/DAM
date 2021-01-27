package com.example.xmlretea;

import java.util.ArrayList;

public interface IMasinaResponse {
    void onSuccess(ArrayList<Masina> masini);
    void onFailure(int errCode, Throwable err);
}
