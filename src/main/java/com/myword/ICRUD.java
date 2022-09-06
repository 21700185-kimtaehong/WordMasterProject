package com.myword;

import java.io.IOException;

public interface ICRUD {
    public Object add() throws IOException;
    public int update(Object obj);
    public int delete(Object obj);
    public void selectOne(int id);
}
