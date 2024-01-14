package com.example.SpringDemo.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
	dangSuDung,
    conTrong,
    daThanhToan;
    
    @JsonCreator
    public static Status fromString(String key) {
        return key == null
            ? null
            : Status.valueOf(key);
    }

    @Override
    public String toString() {
        return name();
    }
}
