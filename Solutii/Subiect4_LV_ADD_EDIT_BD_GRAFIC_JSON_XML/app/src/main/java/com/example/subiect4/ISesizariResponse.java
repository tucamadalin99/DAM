package com.example.subiect4;

import java.util.ArrayList;

public interface ISesizariResponse {
    void onSucces(ArrayList<Sesizare> sesizariRetea);
    void onSuccesXML(ArrayList<Sesizare> sesizariReteaXML);
    void onFailure(int errCode, Throwable err);
}
