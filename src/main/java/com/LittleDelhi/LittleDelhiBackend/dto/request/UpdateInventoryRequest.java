package com.LittleDelhi.LittleDelhiBackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInventoryRequest {
    private int quantity;
    private int threshold;
}
