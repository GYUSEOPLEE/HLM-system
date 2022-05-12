package kr.co.hlm.system.access;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Admin implements Serializable {
    private String id;
    private String password;
}
