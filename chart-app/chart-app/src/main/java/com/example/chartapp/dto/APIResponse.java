package com.example.chartapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {
    int recordCount;
    T response;

    public int getRecordCount() {
        return recordCount;
    }

    public T getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "APIResponse{" +
                "recordCount=" + recordCount +
                ", response=" + response +
                '}';
    }
}
