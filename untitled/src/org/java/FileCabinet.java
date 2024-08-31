package org.java;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class FileCabinet implements Cabinet {
    private List<Folder> folders;

    public Optional<Folder> findFolderByName(String name) {
        Optional<Folder> optionalFolder = null;
        List<Folder> folderList = null;
        for (Folder folder : folders) {
            if (folder.getClass().equals(MultiFolder.class)) {
                MultiFolder mf = (MultiFolder) folder;
                optionalFolder = (mf.getFolders().stream().filter(f2 -> f2.getName().equals(name)).findAny());
                if (!optionalFolder.isEmpty()) return optionalFolder;
            } else {
                folderList.add(folder);
            }
        }
        return folderList.stream().filter(f -> f.getName().equals(name)).findAny();
    }

    public List<Folder> findFoldersBySize(String size) {
        List<Folder> folderList = null;
        for (Folder folder : folders) {
            if (folder.getClass().equals(MultiFolder.class)) {
                MultiFolder mf = (MultiFolder) folder;
                folderList.addAll(mf.getFolders().stream().filter(f2 -> f2.getSize().equals(size)).collect(Collectors.toList()));
            } else {
                if (folder.getSize().equals(size)) folderList.add(folder);
            }
        }
        return folderList;
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
