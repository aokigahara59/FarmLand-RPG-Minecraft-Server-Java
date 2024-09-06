package me.aokigahara.farmlandrpg.infrastructure.storages;

import me.aokigahara.farmlandrpg.application.resource.abstractions.IResourceStorage;
import me.aokigahara.farmlandrpg.application.resource.abstractions.Resource;

import java.util.ArrayList;
import java.util.List;

public class InMemoryResourceStorage implements IResourceStorage {

    private List<Resource> data = new ArrayList<>();


    @Override
    public void add(Resource item) {
        data.add(item);
    }

    @Override
    public void delete(Resource item) {
        data.remove(item);
    }

    @Override
    public List<Resource> getAll() {
        return data;
    }

    @Override
    public boolean contains(Resource item) {
        return data.contains(item);
    }
}
