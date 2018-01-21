package com.yang.hashcode;

class Child extends Person {
    private String play;
    public Child(String name, int age, String play) {
        super(name, age);
        this.play = play;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }
}
