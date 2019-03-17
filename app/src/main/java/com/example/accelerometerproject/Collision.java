package com.example.accelerometerproject;

public class Collision {

    private CollisionListener listener;

    public Collision() {
        // set null or default listener or accept as argument to constructor
        this.listener = null;
    }

    public void collide() {
        listener.onCollision();
    }

    // Assign the listener implementing events interface that will receive the events
    public void setCollisionListener(CollisionListener listener) {
        this.listener = listener;
    }

    public interface CollisionListener {
        void onCollision();
    }


}
