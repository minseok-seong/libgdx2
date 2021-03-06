package com.pokemon.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.pokemon.util.AnimationSet;

import static com.pokemon.game.Settings.SCALE;
import static com.pokemon.game.Settings.TILE_SIZE;

public class Player extends Rectangle {
    private AnimationSet<TextureRegion> animations;
    private DIRECTION facing;
    private PLAYER_STATE state;
    private final float playerSizeX = TILE_SIZE;
    private final float playerSizeY = TILE_SIZE*1.5f;

    public float getPlayerSizeX() {
        return playerSizeX * SCALE;
    }

    public float getPlayerSizeY() {
        return playerSizeY * SCALE;
    }

    private float ANIM_TIME = 0.6f;
    private float animTimer;

    public Player(int x, int y, AnimationSet<TextureRegion> animations) {
        this.x = x;
        this.y = y;
        this.animations = animations;
        this.state = PLAYER_STATE.STANDING;
        this.facing = DIRECTION.SOUTH;
    }

    public void update(float delta) {
        if (state == PLAYER_STATE.WALKING) {
            animTimer += delta;
            if (animTimer > ANIM_TIME) {
                animTimer = 0f;
            }
        }
    }

    public enum PLAYER_STATE {
        WALKING,
        STANDING
    }

    public TextureRegion getSprites() {
        if (state == PLAYER_STATE.WALKING) {
            return animations.getWalking(facing).getKeyFrame(animTimer);
        }
        return animations.getStanding(facing);
    }

    public void finishMove() {
        state = PLAYER_STATE.STANDING;
    };

    public void setFacing(DIRECTION facing) {
        this.facing = facing;
    }

    public void setState(PLAYER_STATE state) {
        this.state = state;
    }
}
