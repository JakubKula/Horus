package org.java;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class FileCabinet2 implements Cabinet {
    private List<Folder> folders;

    public Optional<Folder> findFolderByName(String name) {
        return folders.stream().filter(f -> f.getName().equals(name)).findAny();
    }

    public List<Folder> findFoldersBySize(String size) {
        return folders.stream().filter(f -> f.getSize().equals(size)).collect(Collectors.toList());
    }

    public int count() {
        int count = 0;
        for (Folder folder : folders) {
            if (folder.getClass().equals(MultiFolder.class)) {
                MultiFolder mf = (MultiFolder) folder;
                count += mf.getFolders().size();
            } else {
                count++;
            }
        }
        return count;
    }
}
