package com.app.Store.Expection;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnauthorizedException extends RuntimeException{
    private final String msg;
}
