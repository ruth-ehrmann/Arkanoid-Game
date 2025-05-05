// 323013276 Ruth Ehrmann

package sprites;

import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * The sprites.SpriteCollection class manages a collection of sprites.Sprite objects.
 * It provides methods to add sprites, retrieve a sprite by index,
 * notify all sprites that time has passed, and draw all sprites on a given surface.
 */
public class SpriteCollection {
    // List to hold the collection of sprites
    private final List<Sprite> spriteCollections;
    /**
     * Constructs an empty sprites.SpriteCollection.
     */
    public SpriteCollection() {
        this.spriteCollections = new ArrayList<>();
    }
    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprites.Sprite to add
     */
    public void addSprite(Sprite s) {
        this.spriteCollections.add(s);
    }
    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprites.Sprite object to be removed
     */
    public void removeSprite(Sprite s) {
        this.spriteCollections.remove(s);
    }
    /**
     * Returns the list of sprites currently in the collection.
     *
     * @return a List containing all sprites in the collection
     */
    public List<Sprite> getSpriteCollections() {
        return this.spriteCollections;
    }
    /**
     * Calls the timePassed() method on all sprites in the collection.
     * This method is used to notify all sprites that time has passed.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> spritesCopy = new ArrayList<>(this.spriteCollections);
        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }
    /**
     * Calls the drawOn(DrawSurface d) method on all sprites in the collection.
     * This method is used to draw all sprites on the provided DrawSurface.
     *
     * @param d the DrawSurface on which to draw all sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.spriteCollections) {
            sprite.drawOn(d);
        }
    }
}
