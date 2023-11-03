package com.bmo.practica5.config;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public abstract class Methods {

    public abstract void create(Object object, Connection cc);

    public abstract Object findById(String id, Connection cc);

    public abstract ArrayList<Object> findAll(String id, Connection cc);

    public abstract boolean update(Object object, Connection cc);

    public abstract boolean delete(String id, Connection cc);
}
