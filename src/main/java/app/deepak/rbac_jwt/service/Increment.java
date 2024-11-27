package app.deepak.rbac_jwt.service;

import org.springframework.stereotype.Service;

@Service
public class Increment {

    public int a = 1;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a+1;
    }
}
