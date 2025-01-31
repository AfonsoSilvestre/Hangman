/**
 * To de-clutter your code it is best advised to create interfaces.
 * We can think of these as a set of rules another class must obey. In
 * This case we simply create an interface that stores words, and after implementing it in
 * a class, we'll access them remotely.
 * Since this is meant to be a simple raw java project we didnt use a word generator.
 * If you wish to take your project to other heights, its best advised to install said package.
 */

public interface Words {
    String[] words = {
            "apple", "banana", "chair", "table", "mouse",
            "pizza", "orange", "flower", "music", "cookie",
            "pyramid", "cactus", "dolphin", "galaxy", "thunder",
            "balloon", "journey", "shadow", "magnet", "blanket",
            "labyrinth", "zephyr", "phoenix", "eccentric", "oxygen",
            "mystique", "whimsical", "paradox", "cryptic", "tranquility",
            "elephant", "kangaroo", "crocodile", "penguin", "butterfly",
            "spaghetti", "cinnamon", "chocolate", "lasagna", "avocado",
            "mountain", "volcano", "desert", "iceberg", "archipelago",
            "algorithm", "database", "keyboard", "processor", "cybersecurity"
    };
}
