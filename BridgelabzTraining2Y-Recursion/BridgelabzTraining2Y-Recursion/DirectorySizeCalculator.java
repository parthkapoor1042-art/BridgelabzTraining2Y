import java.util.*;

/**
 * Assignment 1: Recursively calculate the total size of a directory.
 * This example models a directory tree in memory, so it runs on any computer.
 */
public class DirectorySizeCalculator {
    static class Entry {
        String name;
        long sizeKB;                 // Used only for files
        List<Entry> children;        // Non-empty for directories

        private Entry(String name, long sizeKB, List<Entry> children) {
            this.name = name;
            this.sizeKB = sizeKB;
            this.children = children;
        }

        static Entry file(String name, long sizeKB) {
            return new Entry(name, sizeKB, null);
        }

        static Entry directory(String name, Entry... entries) {
            return new Entry(name, 0, Arrays.asList(entries));
        }

        boolean isFile() {
            return children == null;
        }
    }

    static long totalSizeKB(Entry entry) {
        // Base case: a file is a leaf, so return its size directly.
        if (entry.isFile()) {
            return entry.sizeKB;
        }

        // Recursive case: sum the sizes of every child in the directory.
        long total = 0;
        for (Entry child : entry.children) {
            total += totalSizeKB(child);
        }
        return total;
    }

    public static void main(String[] args) {
        Entry project = Entry.directory("project",
            Entry.directory("src",
                Entry.file("main.java", 100),
                Entry.file("utils.java", 50)
            ),
            Entry.directory("docs",
                Entry.file("readme.txt", 10),
                Entry.directory("guides",
                    Entry.file("setup.pdf", 200)
                )
            ),
            Entry.file("config.xml", 20)
        );

        System.out.println("Total project size: " + totalSizeKB(project) + " KB");
        // Expected output: 380 KB
    }
}
