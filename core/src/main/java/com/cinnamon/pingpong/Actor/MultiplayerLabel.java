package com.cinnamon.pingpong.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * MultiplayerLabel
 */
public class MultiplayerLabel extends Table {

    public MultiplayerLabel() {

        super();
        this.setFillParent(true);
        this.setVisible(false);
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Label label = new Label("Waiting for connection", skin, "big");
        label.setColor(skin.getColor("white"));
        this.add(label);
        this.row();
        label = new Label("IP: 127.0.0.1", skin, "big");
        label.setColor(skin.getColor("white"));
        this.add(label);
    }

}
