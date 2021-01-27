package com.example.jsonpreluare;

import java.util.ArrayList;

public interface IBookResponse {
    void onSuccess(ArrayList<Book> success);
    void onSuccessLib(Library lib);
    void onFailure(int errCode, Throwable err);
}
